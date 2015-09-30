package com.example.ledoa.dailyexsuper.activity;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.sqlite.DTO.BaiTap;
import com.example.ledoa.dailyexsuper.sqlite.DatabaseHandle;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;

public class DiBoActivity extends Activity implements SensorEventListener {

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
    
    
    boolean finish = false;
    boolean click = false;
    boolean check_finish = false;
    long thoiGianTruocKhiLac;
    int SoLanLac = 0;
    int SoLanChay = 0;
    int MucTieu = 100;
    int MucTieuThoiGian;
    String IdBaiTap ;
    int IdChuongTrinhGiamCan;
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
	        test = (TextView) findViewById(R.id.test);
	        status = (TextView) findViewById(R.id.TrangThai);
	        tocdo = (TextView) findViewById(R.id.TocDo);
	        
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
                finish();
            }
        });

        databaseHandle = new DatabaseHandle(this);



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

                        if (IdBaiTap != null) {
                            databaseHandle.updateBaiTap(IdBaiTap);
                        }
                        else if(IdChuongTrinhGiamCan >= 0){

                            databaseHandle.updateChuongTrinhGiamCan(IdChuongTrinhGiamCan);
                        }
                    }

                }
            });
            v.setText(SoLanLac + "/" + MucTieu + " s");
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
                    status.setText("Finish");
                    finish_icon.setVisibility(View.VISIBLE);
                    time = choChronometer.getBase() - SystemClock.elapsedRealtime();
                    TongThoiGian = -time / 1000;
                    tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
                    choChronometer.stop();
                    finish = true;
                    if (!IdBaiTap.equals("")) {
                        databaseHandle.updateBaiTap(IdBaiTap);
                    }
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
    }

    public void btn_stop(View v1) {
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
    	
        click = false;
    	if (ButtonVuaNhan.equals("pause")) return;
    	if (ButtonVuaNhan.equals("stop")) return;
    	status.setText("Pause...");
    	ButtonVuaNhan = "pause";
        time = choChronometer.getBase() - SystemClock.elapsedRealtime();
        TongThoiGian = -time/1000;
        tocdo.setText(SoLanLac + " Bước / " +  DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
        choChronometer.stop();
    }
}
