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
						addLocation();
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
					if(entity.data.get(i).latitude != null && entity.data.get(i).latitude != "null"){
						//CheckDistance(entity.data.get(i).latitude, entity.data.get(i).longitude);
						double lat=Double.parseDouble(entity.data.get(i).latitude);
						double lng=Double.parseDouble(entity.data.get(i).longitude);
						double dX = lat - dLatitude;
						double dY = lng - dLongitude;
						int dis = (int)(Math.sqrt( ( dX*dX ) + ( dY*dY ) )*100000);
						String min = String.valueOf(dis / 400) + "phút";
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
						Toast.makeText(ThemBanActivity.this, String.valueOf(dLatitude) + " "+ String.valueOf(dLongitude), Toast.LENGTH_LONG).show();	Toast.makeText(ThemBanActivity.this, String.valueOf(dLatitude) + " "+ String.valueOf(dLongitude), Toast.LENGTH_LONG).show();
						mUserList.add(entity.data.get(i));
						success = true;
					}
				}
				if(!distance.equals("")) {
					mThemBanAdapter = new ThemBanAdapter(getApplicationContext(), mUserList);
					mThemBanAdapter.notifyDataSetChanged();

					handler.removeCallbacks(mHandlerTask);
					stop = true;
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

	public void CheckDistance(String latitude, String longitude){
		double lat=Double.parseDouble(latitude);
		double lng=Double.parseDouble(longitude);
		LatLng origin = new LatLng(lat, lng);
		LatLng dest  = new LatLng(dLatitude, dLongitude);
		String url = getDirectionsUrl(origin, dest);
		DownloadTaskDistance downloadTask1 = new DownloadTaskDistance();
		// Start downloading json data from Google Directions API
		downloadTask1.execute(url);
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

	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try{

			URL url = new URL(strUrl);
			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.connect();

			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
			StringBuffer sb  = new StringBuffer();
			String line = "";
			while( ( line = br.readLine())  != null) {
				sb.append(line);
			}
			data = sb.toString();
			br.close();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_SHORT).show();
		}finally{
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	private String getDirectionsUrl(LatLng origin,LatLng dest){
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;
		// Sensor enabled
		String sensor = "sensor=false";
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
		// Output format
		String output = "json";
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		return url;
	}
	private class DownloadTaskDistance extends AsyncTask<String,Void,String> {
		@Override
		protected String doInBackground(String... url) {
			// For storing data from web service
			String data = "";
			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);

			}catch(Exception e){
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ParserTaskDistance parserTask1 = new ParserTaskDistance();
			// Invokes the thread for parsing the JSON data
			parserTask1.execute(result);
		}
	}
	private class ParserTaskDistance extends AsyncTask<String, Integer, List<List<HashMap<String,String>>>>{
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;
			try{
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();
				// Starts parsing data
				routes = parser.parse(jObject);
			}catch(Exception e){
				e.printStackTrace();
			}
			return routes;
		}
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;

			if(result == null){
				Toast.makeText(ThemBanActivity.this, "No internet", Toast.LENGTH_SHORT).show();
				return;
			}
			// Traversing through all the routes
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();

				List<HashMap<String, String>> path = result.get(i);

				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);

					if(j==0){
						distance = (String)point.get("distance");
						continue;
					}else if(j==1){
						duration = (String)point.get("duration");
						continue;
					}
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);
					points.add(position);
				}
			}

			Toast.makeText(ThemBanActivity.this,"Distance:"+distance + ", Duration:"+duration, Toast.LENGTH_SHORT).show();
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

	private void addLocation() {

		HashMap<String, String> params = new HashMap<>();
		params.put("longitude", String.valueOf(dLongitude) );
		params.put("latitude", String.valueOf(dLatitude));
		mLoginRequest = new LoginRequest(Method.PUT, ApiLink.addLocation()+ "/" + mUserPref.getUser()._id, null, params) {

			@Override
			protected void onStart() {

			}

			@Override
			protected void onSuccess(LoginResponse entity, int statusCode, String message) {
				Toast.makeText(getApplicationContext(), "da xoa vi tri", Toast.LENGTH_SHORT).show();
			}

			@Override
			protected void onError(int statusCode, String message) {
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		};
		mLoginRequest.execute();
	}
}
