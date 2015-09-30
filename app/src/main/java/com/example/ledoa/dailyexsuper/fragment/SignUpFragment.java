package com.example.ledoa.dailyexsuper.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.activity.LoginActivity;
import com.example.ledoa.dailyexsuper.base.BaseFragment;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.SignupRequest;
import com.example.ledoa.dailyexsuper.connection.response.SignupResponse;
import com.example.ledoa.dailyexsuper.listener.LoginListener;
import com.example.ledoa.dailyexsuper.util.UserPref;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends BaseFragment {

    private LoginListener mListener;

    private EditText mEdtUsername, mEdtEmail, mEditPassword, mEdtPhoneNumber, mEdtCountry;
    private RadioGroup mRgGender;
    private RadioButton mRbNone, mRbMale, mRbFemale;
    private Button mBtnSignUp;
    private TextView mTvSignup;;

    private SignupRequest mSignupRequest;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        if (getActivity() instanceof LoginActivity) {
            ((LoginActivity) getActivity()).setIsExistSignUpFragment(true);
        }
        mEdtUsername = (EditText) rootView.findViewById(R.id.edtUsername);
        mEdtEmail = (EditText) rootView.findViewById(R.id.edtEmail);
        mEditPassword = (EditText) rootView.findViewById(R.id.edtPassword);
        mEdtPhoneNumber = (EditText) rootView.findViewById(R.id.edtPhoneNumber);
        mEdtCountry = (EditText) rootView.findViewById(R.id.edtCountry);
        mRgGender = (RadioGroup) rootView.findViewById(R.id.rgGender);
        mRbNone = (RadioButton) rootView.findViewById(R.id.rbNone);
        mRbMale = (RadioButton) rootView.findViewById(R.id.rbMale);
        mRbFemale = (RadioButton) rootView.findViewById(R.id.rbFemale);
        mBtnSignUp = (Button) rootView.findViewById(R.id.btnSignUp);
        mTvSignup = (TextView) rootView.findViewById(R.id.tvSignIn);

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onShowSignupFragment();
            }
        });
    }

    private void signUp() {
        if (mEdtUsername.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), getResources().getString(R.string.signup_username_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtEmail.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Email can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkEmail(mEdtEmail.getText().toString())) {
            Toast.makeText(getActivity(), "Email incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEditPassword.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Password can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkPassword() == false) {
            Toast.makeText(getActivity(), "Password is too short", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtPhoneNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Phone number can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtCountry.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Country can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int gender = 1;
        if (mRgGender.getCheckedRadioButtonId() == mRbNone.getId()) {
            Toast.makeText(getActivity(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        } else if (mRgGender.getCheckedRadioButtonId() == mRbMale.getId()) {
            gender = 1;
        } else if (mRgGender.getCheckedRadioButtonId() == mRbFemale.getId()) {
            gender = 2;
        }

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", mEdtUsername.getText().toString().trim());
        params.put("email", mEdtEmail.getText().toString().trim());
        params.put("password", mEditPassword.getText().toString().trim());
        params.put("gender", String.valueOf(gender));
        params.put("phone", mEdtPhoneNumber.getText().toString().trim());
        params.put("country", mEdtCountry.getText().toString().trim());
        mSignupRequest = new SignupRequest(Method.POST, ApiLink.getSignUpLink(), null, params) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(SignupResponse entity, int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                UserPref userPref = new UserPref();
                userPref.setUser(entity.data);
                mListener.onLogged();
            }

            @Override
            protected void onError(int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        };
        mSignupRequest.execute();
    }

    private boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    private boolean checkPassword() {
        if (mEditPassword.getText().toString().trim().length() <= 4) {
            return false;
        }
        return true;
    }

    @Override
    protected void initActionBarLayout() {

    }

    @Override
    public void onAttach(Activity activity) {
        initActionBarLayout();
        super.onAttach(activity);
        try {
            mListener = (LoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LoginListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof LoginActivity) {
            ((LoginActivity) getActivity()).setIsExistSignUpFragment(false);
        }
        if (mSignupRequest != null) {
            mSignupRequest.cancel();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
