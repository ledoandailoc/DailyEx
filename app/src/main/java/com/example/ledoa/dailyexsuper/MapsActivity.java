package com.example.ledoa.dailyexsuper;

import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.DistanceAndDuration.DirectionsJSONParser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    private ImageView iVSearch;
    private EditText et_InputTextSearch;
    private Button btNomal;
    private Button btSatellite;
    //Distance and Duration.
    private TextView tv_Distance;
    ArrayList<LatLng> markerPoints;
    //My location
    private double dLatitude,dLongitude;
    private String address;
    private String country;
    private String city;
    private String SubAdminArea1,SubLocality,SubThoroughfare,AdminArea,Thoroughfare;
    private Button btMylocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setup.
        setUpMapIfNeeded();
        //Type map.
        btNomal=(Button)findViewById(R.id.btNomal);
        btSatellite=(Button)findViewById(R.id.btSatellite);
        //Search.
        iVSearch=(ImageView)findViewById(R.id.iVSearch);
        et_InputTextSearch=(EditText)findViewById(R.id.et_InputTextSearch);
        iVSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SearchLocation();
                CheckDistance("10.870566","106.803053");
            }
        });
        AddMarker();
        //ChangeView.
        btNomal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangingView(0);
            }
        });
        btSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangingView(1);
            }
        });
        //Distance and Duration.
        tv_Distance=(TextView)findViewById(R.id.tv_Distance);
        markerPoints=new ArrayList<LatLng>();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    //mMap.clear();
                }
                // Adding new item to the ArrayList
                markerPoints.add(point);
                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();
                // Setting the position of the marker
                options.position(point);
                /**
                 * For the start location, the color of marker is GREEN and
                 * for the end location, the color of marker is RED.
                 */
                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);
                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    //Source.
                    LatLng origin = markerPoints.get(0);
                    //Dest.
                    LatLng dest = markerPoints.get(1);
                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);
                    DownloadTaskDistance downloadTask1 = new DownloadTaskDistance();
                    // Start downloading json data from Google Directions API

                    downloadTask1.execute(url);
                    tv_Distance.setVisibility(View.VISIBLE);
                }
            }
        });
        btMylocation=(Button)findViewById(R.id.btMyLocation);
        btMylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLocation();
            }
        });
    }

    public void CheckDistance(String latitude, String longitude){

        double lat=Double.parseDouble(latitude);
        double lng=Double.parseDouble(longitude);
        LatLng origin = new LatLng(lat, lng);
        //Dest.



        try {
            Location myLocation = mMap.getMyLocation();
            if (myLocation != null) {
                dLatitude = myLocation.getLatitude();
                dLongitude = myLocation.getLongitude();

            LatLng dest  = new LatLng(dLatitude, dLongitude);
            // Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);
            DownloadTaskDistance downloadTask1 = new DownloadTaskDistance();
            // Start downloading json data from Google Directions API

            downloadTask1.execute(url);
            tv_Distance.setVisibility(View.VISIBLE);




            } else {
                Toast.makeText(this, "Can't find my location!", Toast.LENGTH_SHORT).show();
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
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
        //
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
    //ChangeView.
    private void ChangingView(int position){
        switch (position) {
            case 0:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
        }
    }


    //Method download data google API.
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
    private void AddMarker(){
        //Kinh độ và vĩ độ của điểm.
        String coordinates[]={"10.870566","106.803053"};
        double lat=Double.parseDouble(coordinates[0]);
        double lng=Double.parseDouble(coordinates[1]);
        MarkerOptions markerOptions=new MarkerOptions();
        LatLng latLng=new LatLng(lat,lng);
        markerOptions.position(latLng);
        markerOptions.title("Truong Dai hoc Cong nghe thong tin-DHQG-HCM ");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
    }
    /*****************Distance and Duration **************/
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
    private class DownloadTaskDistance extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... url) {
            // For storing data from web service
            String data = "";
            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_SHORT).show();
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
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";
            if(result.size()<1){
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                tv_Distance.setVisibility(View.GONE);
                return;
            }
            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){	// Get distance from the list
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");
                        continue;
                    }
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }
            tv_Distance.setText("Distance:"+distance + ", Duration:"+duration);

            mMap.addPolyline(lineOptions);
        }
    }
    void MyLocation(){
        try {

            Location myLocation = mMap.getMyLocation();
            if (myLocation != null) {
                dLatitude = myLocation.getLatitude();
                dLongitude = myLocation.getLongitude();

                Geocoder geocoder;
                List<android.location.Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());
                try {

                    addresses = geocoder.getFromLocation(dLatitude, dLongitude, 1);
                    android.location.Address ad = addresses.get(0);
                    address = ad.getAddressLine(0);
                    country = ad.getCountryName();
                    // postalcode = addresses.get(0).getPostalCode();
                    city = ad.getLocality();
                    AdminArea = ad.getAdminArea();
                    SubAdminArea1 = ad.getSubAdminArea();
                    SubLocality = ad.getSubLocality();
                    SubThoroughfare = ad.getSubThoroughfare();
                    Thoroughfare = ad.getThoroughfare();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mMap.addMarker(new MarkerOptions().position(
                        new LatLng(dLatitude, dLongitude)).title("My Location is :" + SubThoroughfare + "," + Thoroughfare + "," + SubAdminArea1 + "," + AdminArea));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 20));
            } else {
                Toast.makeText(this, "Can't find my location!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex)
        {
            Toast.makeText(this, "Hardware Erorr!", Toast.LENGTH_SHORT).show();
        }
    }
}