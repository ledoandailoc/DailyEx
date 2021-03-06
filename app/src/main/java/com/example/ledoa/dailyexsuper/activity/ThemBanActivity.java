package com.example.ledoa.dailyexsuper.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.DistanceAndDuration.DirectionsJSONParser;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ThemBanAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListUserRequest;
import com.example.ledoa.dailyexsuper.connection.request.LoginRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListUserResponse;
import com.example.ledoa.dailyexsuper.connection.response.LoginResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThemBanActivity extends FragmentActivity {

	private ArrayList<User> mUserList = new ArrayList<>();
	ThemBanAdapter mThemBanAdapter;
	GetListUserRequest mGetListUserRequest;
	ListView mLvThemBan;
	Socket mSocket;

	private GoogleMap mMap;
	//My location
	private double dLatitude,dLongitude;
	String distance = "";
	String duration = "";
	Location myLocation;
	Handler handler;
	Runnable mHandlerTask,  mHandlerTask_Check;
	Boolean bool = false, success = false, stop = false;
	Button btConnect, btXoaVitri;
	TextView mTvConnect, tvProgressBar, title;
	ImageView mIvConnect, mIvMenuLocation;;
	RelativeLayout rlErrorConnect, loadingPanel;
	UserPref mUserPref;
	LoginRequest mLoginRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_ban);
		setUpMapIfNeeded();
		statusCheck();
		title  = (TextView)findViewById(R.id.actionbar_tvTitile);
		title.setText("Thêm bạn");
		mUserPref = new UserPref();

		mIvConnect = (ImageView)findViewById(R.id.ivConnect);
		btConnect = (Button)findViewById(R.id.btConnect);
		mTvConnect = (TextView)findViewById(R.id.tvConnect);
		tvProgressBar = (TextView)findViewById(R.id.tvProgressBar);
		rlErrorConnect = (RelativeLayout)findViewById(R.id.rlErrorConnect);
		loadingPanel = (RelativeLayout)findViewById(R.id.loadingPanel);
		mIvMenuLocation = (ImageView)findViewById(R.id.iv_menu_location);
		btXoaVitri = (Button)findViewById(R.id.bt_xoa_vitri);

		rlErrorConnect.setVisibility(View.GONE);

		btXoaVitri.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btXoaVitri.setVisibility(View.GONE);
				mIvMenuLocation.setVisibility(View.VISIBLE);
				buildAlertMessageDeleteLocation();
			}
		});


		mIvMenuLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mIvMenuLocation.setVisibility(View.GONE);
				btXoaVitri.setVisibility(View.VISIBLE);
			}
		});

		btConnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopRepeatingTask();
				rlErrorConnect.setVisibility(View.GONE);
				startRepeatingTask();
			}
		});

		handler = new Handler();
		handler.postDelayed(mHandlerTask = new Runnable() {

			@Override
			public void run() {
				CreateLocation();
				handler = new Handler();
				handler.postDelayed(this, 2000);
			}
		}, 2000);
		handler.postDelayed(mHandlerTask_Check = new Runnable() {

			@Override
			public void run() {
				if(bool) {
					loadingPanel.setVisibility(View.GONE);
					handler.removeCallbacks(mHandlerTask);
					rlErrorConnect.setVisibility(View.VISIBLE);
					bool = false;
				} else if (success) {
					handler.removeCallbacks(mHandlerTask);
					loadingPanel.setVisibility(View.GONE);
				} else if (stop) {
					loadingPanel.setVisibility(View.GONE);
				}
				handler = new Handler();
				handler.postDelayed(this, 2000);
			}
		}, 2000);

	}
	@Override
	protected void onPause() {
		stopRepeatingTask();
		super.onPause();
	}

	void startRepeatingTask()
	{
		mHandlerTask.run();
		mHandlerTask_Check.run();
	}

	void stopRepeatingTask()
	{
		handler.removeCallbacks(mHandlerTask);
		handler.removeCallbacks(mHandlerTask_Check);
	}

	public void statusCheck()
	{
		final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			buildAlertMessageNoGps();
		} else {
			CreateLocation();
		}
	}
	private void buildAlertMessageDeleteLocation() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Bạn có muốn xóa vị trí để khỏi bị làm phiền.")
				.setCancelable(false)
				.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,  final int id) {
						addLocation(1);
						Toast.makeText(getApplicationContext(), "da xoa vi tri", Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}
	private void buildAlertMessageNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Vui lòng bật chức năng xác định vị trí trong phần cài đặt để tìm bạn quanh đây.")
				.setCancelable(false)
				.setPositiveButton("Cài đặt", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,  final int id) {
						startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					}
				})
				.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}

	private void getListUsers() {
		loadingPanel.setVisibility(View.GONE);
		tvProgressBar.setText("Đang tìm kiếm người dùng gần bạn...");
		loadingPanel.setVisibility(View.VISIBLE);
		mLvThemBan = (ListView)findViewById(R.id.lvThemBan);

		mGetListUserRequest = new GetListUserRequest(Method.GET, ApiLink.getAllUserLink(), null, null) {
			@Override
			protected void onStart() {
			}
			@Override
			protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
				mUserList.clear();
				//mUserList.addAll(entity.data);
				for(int i=0; i< entity.data.size(); i++){
					if(entity.data.get(i).latitude != null && !entity.data.get(i).latitude.equals("null")
							&& !entity.data.get(i).latitude.equals("1")){
						//CheckDistance(entity.data.get(i).latitude, entity.data.get(i).longitude);
						double lat=Double.parseDouble(entity.data.get(i).latitude);
						double lng=Double.parseDouble(entity.data.get(i).longitude);
						double dX = lat - dLatitude;
						double dY = lng - dLongitude;
						int dis = (int)(Math.sqrt( ( dX*dX ) + ( dY*dY ) )*100000);
						String min = String.valueOf((dis / 200)+1) + "phút";
						if(dis < 950){
							distance = String.valueOf(
									dis - dis%50 + 50
							) + "m";
						} else {
							distance = String.valueOf(
									((double)(dis/100))/10
							) + "km";
						}
						entity.data.get(i).latitude = distance;
						entity.data.get(i).longitude = min;
						mUserList.add(entity.data.get(i));
						success = true;
					}
				}
				if(!distance.equals("")) {
					mThemBanAdapter = new ThemBanAdapter(getApplicationContext(), mUserList);
					mThemBanAdapter.notifyDataSetChanged();

					handler.removeCallbacks(mHandlerTask);
					stop = true;
					addLocation(0);
					mLvThemBan.setAdapter(mThemBanAdapter);
				}
			}
			@Override
			protected void onError(int statusCode, String message) {
				bool = true;
				Toast.makeText(getApplicationContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
			}
		};
		mGetListUserRequest.execute();
	}


	public void CreateLocation(){
		myLocation = mMap.getMyLocation();
		try {
			if (myLocation != null) {
				dLatitude = myLocation.getLatitude();
				dLongitude = myLocation.getLongitude();
				getListUsers();
			} else {

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
				Toast.makeText(ThemBanActivity.this, "Dang tim kiem xung quanh.", Toast.LENGTH_SHORT).show();
			}
		}
		mMap.setMyLocationEnabled(true);

	}

	private void addLocation(int xoavitri) {

		HashMap<String, String> params = new HashMap<>();
		if(xoavitri == 0){
			params.put("longitude", String.valueOf(dLongitude) );
			params.put("latitude", String.valueOf(dLatitude));
		} else {
			params.put("longitude", "1" );
			params.put("latitude", "1");
		}
		mLoginRequest = new LoginRequest(Method.PUT, ApiLink.addLocation()+ "/" + mUserPref.getUser()._id, null, params) {

			@Override
			protected void onStart() {

			}

			@Override
			protected void onSuccess(LoginResponse entity, int statusCode, String message) {
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		};
		mLoginRequest.execute();
	}
}
