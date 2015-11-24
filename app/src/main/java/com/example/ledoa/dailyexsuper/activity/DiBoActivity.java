package com.example.ledoa.dailyexsuper.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

public class DiBoActivity extends FragmentActivity implements SensorEventListener {

	ProgressBar progresss_bar;

    SensorManager sensorManager;
    Sensor accelerometer;
    
    TextView v;
    EditText editText_sobuoc;
    ImageView finish_icon;
    ImageButton btn_stop;
    ImageButton btn_pause;
    ImageButton btn_start;
    Chronometer choChronometer;
    TextView test;
    TextView status;
    TextView tocdo;
    TextView tvQuangDuong;
    private GoogleMap mMap;
    Location myLocation;
    ArrayList<String> dLatitude, dLongitude;
    double kinhdo = 0, vido = 0, SumDistance = 0;

    Handler handler;
    Runnable mHandlerTask;

    boolean finish = false;
    boolean click = false;
    boolean check_finish = false;
    long thoiGianTruocKhiLac;
    int SoLanLac = 0;
    int SoLanChay = 0;
    int MucTieu = 100;
    int MucTieuThoiGian;
    String IdBaiTap ;
    int IdChuongTrinhGiamCan, intDemNguocStart = 0;
    boolean mucTieuTG = false;
    long time = 0;
    long TongThoiGian = 0;
    String ButtonVuaNhan = "aaa";

    DatabaseHandle databaseHandle;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dibo);
        v = (TextView) findViewById(R.id.textView);

        dLatitude = new ArrayList<String>();
        dLongitude = new ArrayList<String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            MucTieu = bundle.getInt("soBuoc");
            MucTieuThoiGian = bundle.getInt("soThoiGian");
            IdBaiTap = bundle.getString("IdBaiTap");
            IdChuongTrinhGiamCan = bundle.getInt("IdChuongTrinhGiamCan");
        }
        if (MucTieuThoiGian > 0){
            MucTieu = MucTieuThoiGian;
            mucTieuTG = true;
        }
        else {
            v.setText(SoLanLac + "/" + MucTieu + " bước");
        }


	        btn_start = (ImageButton) findViewById(R.id.btn_start);
	        btn_pause = (ImageButton) findViewById(R.id.btn_pause);
	        btn_stop = (ImageButton) findViewById(R.id.btn_stop);
	        editText_sobuoc = (EditText) findViewById(R.id.editText_sobuoc);
	        test = (TextView) findViewById(R.id.tv_buoc);
	        status = (TextView) findViewById(R.id.TrangThai);
	        tocdo = (TextView) findViewById(R.id.TocDo);
            tvQuangDuong = (TextView) findViewById(R.id.tv_km);
	        
	        finish_icon = (ImageView) findViewById(R.id.finishIcon);
	        progresss_bar = (ProgressBar) findViewById(R.id.progressBar);
	        choChronometer = (Chronometer) findViewById(R.id.chronometer);

	        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	        accelerometer= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
            ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IdChuongTrinhGiamCan > 0)
                {
                    Intent intent = new Intent(DiBoActivity.this, BaiTapGiamCanActivity.class);
                    startActivity(intent);
                }
                else
                finish();
            }
        });

        databaseHandle = new DatabaseHandle(this);

        LayDoanDuongDiDuoc();
        stopRepeatingTask();


        if (mucTieuTG == true){
            test.setVisibility(View.VISIBLE);
            choChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    time = choChronometer.getBase() - SystemClock.elapsedRealtime();
                    TongThoiGian = -time / 1000;
                    v.setText(TongThoiGian + "/" + MucTieu + "s");
                    progresss_bar.setProgress(Integer.parseInt(String.valueOf(TongThoiGian)));
                    if (TongThoiGian == MucTieu) {
                        status.setText("Finish");
                        finish_icon.setVisibility(View.VISIBLE);
                        tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));

                        choChronometer.stop();
                        finish = true;

                        /*if (IdBaiTap != null) {*/
                            databaseHandle.updateBaiTap(IdBaiTap);
                        /*}*/
                        if(IdChuongTrinhGiamCan >= 0){

                            databaseHandle.updateChuongTrinhGiamCan(IdChuongTrinhGiamCan);
                        }
                    }

                }
            });
            v.setText(SoLanLac + "/" + MucTieu + "s");
            progresss_bar.setProgress(SoLanLac);
        }

	}

	
	
	@Override
    public void onSensorChanged(SensorEvent event) {
        float luc = TinhLuc(event);

        if (luc >= 1.2 && click == true && finish != true) {
            long thoiGianSauKhiLac = event.timestamp;

            if (((thoiGianSauKhiLac - thoiGianTruocKhiLac) / 1000000) < 800) return;
            test.setText(String.valueOf((thoiGianSauKhiLac - thoiGianTruocKhiLac) / 1000000));

                if (SoLanLac >= MucTieu) return;
                SoLanLac++;
                test.setText(String.valueOf(SoLanLac));
                if (SoLanLac == MucTieu) {
                    stopRepeatingTask();
                    status.setText("Finish");
                    finish_icon.setVisibility(View.VISIBLE);
                    time = choChronometer.getBase() - SystemClock.elapsedRealtime();
                    TongThoiGian = -time / 1000;
                    tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
                    choChronometer.stop();
                    finish = true;
/*
                    if (!IdBaiTap.equals("")) {
*/
                        databaseHandle.updateBaiTap(IdBaiTap);
                    /*}*/
                }
            v.setText(SoLanLac + "/" + MucTieu);
            test.setText(String.valueOf(SoLanLac) + " bước");
            progresss_bar.setProgress(SoLanLac);


            thoiGianTruocKhiLac = thoiGianSauKhiLac;
        }
        else return;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float TinhLuc(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float luc = (x*x + y*y + z*z )/(SensorManager.GRAVITY_EARTH* SensorManager.GRAVITY_EARTH);
        return luc;
    }

    public void btn_start(View v1) {
        click = true;
        statusCheck();
        if(intDemNguocStart == 1){
            stopRepeatingTask();
            startRepeatingTask();
            status.setText("Walking...");
            if (ButtonVuaNhan.equals("start")) return;
            choChronometer.start();
            thoiGianTruocKhiLac = System.currentTimeMillis();

            if (ButtonVuaNhan.equals("pause")) {
                choChronometer.setBase(SystemClock.elapsedRealtime() +  time);
            } else {
                time = 0;
                TongThoiGian = -time/1000;
                choChronometer.setBase(SystemClock.elapsedRealtime());
                SoLanLac = 0 ;
                tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
            }

            progresss_bar.setMax(MucTieu);

            ButtonVuaNhan = "start";

            intDemNguocStart = 0;
        }
    }

    public void btn_stop(View v1) {
        stopRepeatingTask();
        SumDistance = 0;
        click = false;
        status.setText("Stopped");
        if (ButtonVuaNhan.equals("stop")) return;
        ButtonVuaNhan = "stop";
        if (!ButtonVuaNhan.equals("pause")) {
            time = choChronometer.getBase() - SystemClock.elapsedRealtime();
            TongThoiGian = -time/1000;
            tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
		}

       /* time = 0;
        choChronometer.setBase(SystemClock.elapsedRealtime());*/
        choChronometer.stop();
        SoLanLac = 0;
        v.setText(SoLanLac + "/" + MucTieu);

        progresss_bar.setProgress(SoLanLac);
        finish_icon.setVisibility(View.INVISIBLE);
    }

    public void btn_pause(View v1) {
        stopRepeatingTask();
        click = false;
    	if (ButtonVuaNhan.equals("pause")) return;
    	if (ButtonVuaNhan.equals("stop")) return;
    	status.setText("Pause...");
    	ButtonVuaNhan = "pause";
        time = choChronometer.getBase() - SystemClock.elapsedRealtime();
        TongThoiGian = -time/1000;
        tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
        choChronometer.stop();
    }

    public void CreateLocation(){
        myLocation = mMap.getMyLocation();
        try {
            if (myLocation != null) {
                double latitude = myLocation.getLatitude();
                double longitude = myLocation.getLongitude();

                double s = myLocation.getAccuracy();

                dLatitude.add(String.valueOf(latitude));
                dLongitude.add(String.valueOf(longitude));
                double doanduong = 0.00000000008;
                if(dLatitude.size()>=2){

                    doanduong = (Math.sqrt(
                            Math.pow((latitude - kinhdo), 2)
                            + Math.pow((longitude - vido), 2)
                    ) * 100000);
                }
                if(s < 18 && doanduong < 22){
                    SumDistance += doanduong;
                }
                kinhdo = latitude;
                vido = longitude;
                //if(SumDistance > 20){
                    Toast.makeText(DiBoActivity.this, "Di duoc" + SumDistance + " " + s, Toast.LENGTH_SHORT).show();
                tvQuangDuong.setText(String.valueOf(SumDistance) + " km");
                //}
               } else {
                Toast.makeText(DiBoActivity.this, "Chua lay duoc vi tri", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex)
        {
            Toast.makeText(this, "Hardware Erorr!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                Toast.makeText(DiBoActivity.this, "Dang tim kiem xung quanh.", Toast.LENGTH_SHORT).show();
            }
        }
        mMap.setMyLocationEnabled(true);

    }
    public void statusCheck()
    {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        } else {
            intDemNguocStart = 1;
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Vui lòng bật chức năng xác định vị trí trong phần cài đặt.")
                .setCancelable(false)
                .setPositiveButton("Cài đặt", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog,  final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        intDemNguocStart = 0;
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void LayDoanDuongDiDuoc(){
        handler = new Handler();
        handler.postDelayed(mHandlerTask = new Runnable() {

            @Override
            public void run() {
                CreateLocation();
                handler = new Handler();
                handler.postDelayed(this, 10000);
            }
        }, 10000);
    }

    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    void stopRepeatingTask()
    {
        handler.removeCallbacks(mHandlerTask);
    }
}
