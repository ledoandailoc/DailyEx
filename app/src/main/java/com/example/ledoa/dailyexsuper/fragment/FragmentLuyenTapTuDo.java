package com.example.ledoa.dailyexsuper.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.util.ScaleImage;
import com.example.ledoa.dailyexsuper.activity.ChayActivity;
import com.example.ledoa.dailyexsuper.activity.DiBoActivity;
import com.example.ledoa.dailyexsuper.activity.HitDatActivity;
import com.example.ledoa.dailyexsuper.activity.XeDapActivity;


public class FragmentLuyenTapTuDo extends Fragment {
    Intent intent;
    ImageView buttonDiBo, buttonChay, buttonHitDat, buttonDapXe;
    EditText txtSoBuoc, txtThoiGian;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyentap, container, false);

    buttonDiBo = (ImageView) view.findViewById(R.id.ImageViewDiBo);
    buttonChay = (ImageView) view.findViewById(R.id.ImageViewChayBo);
    buttonHitDat = (ImageView) view.findViewById(R.id.ImageViewHitDat);
    buttonDapXe = (ImageView) view.findViewById(R.id.ImageViewDapXe);

    buttonDiBo.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {

            ScaleImage.SetImage(getResources(), buttonDiBo, R.drawable.open_door, 150, 150);

            final Dialog dialog= new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_alert);
            Button btn_ok = (Button) dialog.findViewById(R.id.btn_alert_ok);
            txtSoBuoc = (EditText) dialog.findViewById(R.id.txt_sobuoc);
            int soBuoc;
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "DANG TẠO PHÒNG TẬP...!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), DiBoActivity.class);
                    intent.putExtra("soBuoc", Integer.parseInt(String.valueOf(txtSoBuoc.getText())));
                    ThreadX t=new ThreadX("Luồng X");
                    t.start();
                }
            });
            dialog.show();



        }
    });

    buttonChay.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            ScaleImage.SetImage(getResources(), buttonChay, R.drawable.open_door, 150, 150);
            Toast.makeText(getActivity(), "DANG TẠO PHÒNG TẬP...!", Toast.LENGTH_SHORT).show();
            intent = new Intent(getActivity(), ChayActivity.class);

            ThreadX t=new ThreadX("Luồng X");
            t.start();

        }
    });
    buttonDapXe.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            ScaleImage.SetImage(getResources(), buttonDapXe, R.drawable.open_door, 150, 150);
            Toast.makeText(getActivity(), "DANG TẠO PHÒNG TẬP...!", Toast.LENGTH_SHORT).show();
            intent = new Intent(getActivity(), XeDapActivity.class);

            ThreadX t=new ThreadX("Luồng X");
            t.start();

        }
    });
    buttonHitDat.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            ScaleImage.SetImage(getResources(), buttonHitDat, R.drawable.open_door, 150, 150);
            Toast.makeText(getActivity(), "DANG TẠO PHÒNG TẬP...!", Toast.LENGTH_SHORT).show();
            intent = new Intent(getActivity(), HitDatActivity.class);

            ThreadX t=new ThreadX("Luồng X");
            t.start();
        }
    });
    ScaleImage.SetImage(getResources(), buttonDiBo, R.drawable.close_door, 150, 150);
    ScaleImage.SetImage(getResources(), buttonChay, R.drawable.close_door, 150, 150);
    ScaleImage.SetImage(getResources(), buttonHitDat, R.drawable.close_door, 150, 150);
    ScaleImage.SetImage(getResources(), buttonDapXe, R.drawable.close_door, 150, 150);

    return view;
}

public class ThreadX extends Thread {

        public ThreadX(String name)
        {
            super(name);
        }
        public void run()
        {
            System.out.println("Tên luồng:"+getName());

                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                    }
              startActivity(intent);
        }

    }
}
