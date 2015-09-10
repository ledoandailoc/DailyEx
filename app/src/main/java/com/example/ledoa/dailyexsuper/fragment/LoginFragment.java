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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.activity.LoginActivity;
import com.example.ledoa.dailyexsuper.base.BaseFragment;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.LoginRequest;
import com.example.ledoa.dailyexsuper.connection.response.LoginResponse;
import com.example.ledoa.dailyexsuper.listener.LoginListener;
import com.example.ledoa.dailyexsuper.util.UserPref;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends BaseFragment {

    private LoginListener mListener;

    private EditText mEdtEmail, mEditPassword;
    private Button mBtnLogin;
    private TextView mTvSignup;

    private LoginRequest mLoginRequest;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        if (getActivity() instanceof LoginActivity) {
            ((LoginActivity) getActivity()).setIsExistLoginFragment(true);
        }
        mEdtEmail = (EditText) rootView.findViewById(R.id.edtEmail);
        mEditPassword = (EditText) rootView.findViewById(R.id.edtPassword);
        mBtnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        mTvSignup = (TextView) rootView.findViewById(R.id.tvSignup);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
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

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Signing ...");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", mEdtEmail.getText().toString().trim());
        params.put("password", mEditPassword.getText().toString().trim());
        mLoginRequest = new LoginRequest(Method.POST, ApiLink.getLoginLink(), null, params) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(LoginResponse entity, int statusCode, String message) {
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
        mLoginRequest.execute();
    }

    private boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

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
            ((LoginActivity) getActivity()).setIsExistLoginFragment(false);
        }
        if (mLoginRequest != null) {
            mLoginRequest.cancel();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
