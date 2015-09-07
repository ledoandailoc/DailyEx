package com.example.ledoa.dailyexsuper.activity;


import android.app.Activity;
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

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.util.DoiGioPhutGiay;


public class HitDatActivity extends Activity implements SensorEventListener {

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
    long time = 0;
    long TongThoiGian = 0;
    String ButtonVuaNhan = "aaa";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hitdat);
			
	
			v = (TextView) findViewById(R.id.textView);
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
	        sensorManager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	
	
	@Override
    public void onSensorChanged(SensorEvent event) {
        float luc = TinhLuc(event);

        //long thoiGianSauKhiLac = event.timestamp;

        if (luc >= 1.2 && click == true ) {
         /*   long thoiGian = thoiGianSauKhiLac - thoiGianTruocKhiLac;
            if (thoiGian >= 200) {*/
                if (SoLanLac >= MucTieu ) return;
                SoLanLac ++ ;
                if (SoLanLac == MucTieu){
                	 status.setText("Finish");
                	 finish_icon.setVisibility(View.VISIBLE);
                	 time = choChronometer.getBase() - SystemClock.elapsedRealtime();
                     TongThoiGian = -time/1000;
                     //time = 0;
                     tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
                	 choChronometer.stop();
                	 finish = true;
                }
                v.setText(SoLanLac + "/" + MucTieu);

                progresss_bar.setProgress(SoLanLac);

            }
          /*  else {
                return;
            }
            thoiGianTruocKhiLac = thoiGianSauKhiLac;
        }*/

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
        
        if (ButtonVuaNhan.equals("pause")) {
        	choChronometer.setBase(SystemClock.elapsedRealtime() +  time);
		} else {
			time = 0;
			TongThoiGian = -time/1000;
	        choChronometer.setBase(SystemClock.elapsedRealtime());
	        SoLanLac = 0 ;
	        tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
		}

        
        MucTieu = Integer.parseInt(String.valueOf(editText_sobuoc.getText()));
        v.setText(SoLanLac + "/" + MucTieu);
        progresss_bar.setMax(MucTieu);
        
        ButtonVuaNhan = "start";
    }

    public void btn_stop(View v1) {
        click = false;
        status.setText("Waiting...");
        if (ButtonVuaNhan.equals("stop")) return;
        ButtonVuaNhan = "stop";
        if (!ButtonVuaNhan.equals("pause")) {
            time = choChronometer.getBase() - SystemClock.elapsedRealtime();
            TongThoiGian = -time/1000;
            tocdo.setText(SoLanLac + " Bước / " + DoiGioPhutGiay.GiaySangPhut(TongThoiGian));
		}
        
        test.setText(String.valueOf(TongThoiGian));
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
