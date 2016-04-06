package com.example.jennifer.mygooglemap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;

import android.location.Location;
import android.location.LocationManager;
import android.content.Context;
import android.location.LocationListener;
import android.location.Criteria;
import android.util.Log;
import android.view.WindowManager;
import android.widget.*;
import android.media.MediaPlayer;//導入MediaPlayer類別
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import java.io.*;
import android.content.Intent;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

//------------------
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class FirstMap extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager mLocationManager; //宣告定位管理控制
    double CurLong, CurLati,CurLong0, CurLati0,CurLong1=0, CurLati1=0;
    float myDirection=0;
    int Speed,CurDir;
    //private Marker marker = null;
    private static final String TAG = "William's: ";    //設定LOG的標題
    EditText MyeditText51;
    MediaPlayer mMediaPlayer;
    View.OnClickListener listener51 = null;
    View.OnClickListener listener21 = null;
    View.OnClickListener listener22 = null;
    Button Mybutton21;
    Button Mybutton22;
    private List<LatLng> latlngs = new ArrayList<LatLng>();
    Marker marker0=null;
    Marker marker1=null;
    int isNavigate=0; //0代表目前沒有導航，1代表目前導航中
    int isAlarm=0, AlarmNo; //0代表目前沒有警報，1代表目前警報中
    int isAlarmz=0; //0代表目前沒有警報，1代表目前警報中
    int AlarmID=9999; //代表目前警戒中的相機編號
    LinearLayout Mybackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_map);

        //mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.alarm);//初始化MediaPlayer，音效檔backsound.mid

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //不關閉螢幕
        MyeditText51 = (EditText) findViewById(R.id.editText51);
        Mybackground = (LinearLayout)findViewById(R.id.back);

        //locationServiceInitial();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = mLocationManager.getBestProvider(criteria, true);
        mLocationManager.requestLocationUpdates(provider, 1000, 8, locationChangeListener);  //位置改變時的Listener
        Location mLocation = mLocationManager.getLastKnownLocation(provider);            // Get Current Location
        //要加這兩行，不然一直讀不到目前的位置，一直讀回先前用Google MAP的最後位置
        mLocation = mLocationManager.getLastKnownLocation("gps");
        mLocation = mLocationManager.getLastKnownLocation("network");
        //Location mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);	//使用GPS定位座標

        //MyeditText51.setText("我的座標 - 經度 : " + mLocation.getLongitude() + "  , 緯度 : " + mLocation.getLatitude());
        CurLong = mLocation.getLongitude();
        CurLati = mLocation.getLatitude();
        setUpMapIfNeeded();

        //-----------------------------------------------------------------------------------------------------------------------------------------
        // 新增一個測速相機的位置
        //-----------------------------------------------------------------------------------------------------------------------------------------
        listener21 = new View.OnClickListener() {                       //設定一個OnClick事件處理程
            public void onClick(View v) {                               //按下後顯示Main1Activity.class頁面
                try{
                    FileOutputStream out = openFileOutput("name", MODE_APPEND); //開一個檔案供寫入資料，檔名"name"
                    String oo="("+CurLong+", "+CurLati+") \n";
                    out.write(oo.getBytes());                                    //把MyeditText51內容存進檔案
                    out.close();                                                 //關檔
                    Toast.makeText(v.getContext(), "新測速相機位置增加成功!.", Toast.LENGTH_LONG).show();
                }
                catch(FileNotFoundException e){
                }
                catch (IOException e) {
                }
            }
        };
        Mybutton21=(Button)findViewById(R.id.button21);                 //設定R.id.button在程式中為物件Mybutton
        Mybutton21.setOnClickListener(listener21);                       //設定物件Mybutton的OnClick事件處理程式為listener0，也就是OnClickListener()

        listener22 = new View.OnClickListener() {                       //設定一個OnClick事件處理程
            public void onClick(View v) {                               //按下後顯示Main1Activity.class頁面
                if(CurLati1!=0 && CurLong1!=0) {
                    LatLng origin = new LatLng(CurLati, CurLong);  //起點座標
                    LatLng dest = new LatLng(CurLati1, CurLong1);  //終點座標
                    if(isNavigate==1){  //如果目前正在導航中，先清除原本的路線(但是要把Marker畫回去)
                        mMap.clear();
                        marker0=mMap.addMarker(new MarkerOptions().position(origin).title("我的座標 - 經度 : " + CurLong + "  , 緯度 : " + CurLati));
                        marker1=mMap.addMarker(new MarkerOptions().position(dest).title("我的座標 - 經度 : " + CurLong1 + "  , 緯度 : " + CurLati1));
                    }
                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);
                    DownloadTask downloadTask = new DownloadTask();
                    // Start downloading json data from Google Directions, API
                    downloadTask.execute(url);
                    isNavigate=1;
                }
            }
        };
        Mybutton22=(Button)findViewById(R.id.button22);                 //設定R.id.button在程式中為物件Mybutton
        Mybutton22.setOnClickListener(listener22);                       //設定物件Mybutton的OnClick事件處理程式為listener0，也就是OnClickListener()

        listener51 = new View.OnClickListener() {                       //設定一個OnClick事件處理程
            public void onClick(View v) {                               //按下後顯示Main1Activity.class頁面
                Intent intent0 = new Intent(FirstMap.this, MainActivity2.class);
                startActivity(intent0);
            }
        };
        MyeditText51=(EditText)findViewById(R.id.editText51);            //設定R.id.button在程式中為物件Mybutton
        MyeditText51.setOnClickListener(listener51);                      //設定物件Mybutton的OnClick事件處理程式為listener0，也就是OnClickListener()

        CheckSpeed dt;           //
        dt = new CheckSpeed();  //
        dt.start();             //
    }

    LocationListener locationChangeListener = new LocationListener() {
        public void onLocationChanged(Location mLocation) {
            if (mLocation != null) {
                CurLong=mLocation.getLongitude();
                CurLati=mLocation.getLatitude();
                //double spd=mLocation.getSpeed();
                //double trans=3.6;
                Speed=(int)(mLocation.getSpeed()*3.6F); //把m/s 轉換成 Km/H
                setUpMap();
                //CheckCamera();
                //MyeditText51.setText("我的座標 - 經度 : " + mLocation.getLongitude() + "  , 緯度 : " + mLocation.getLatitude());
            }
        }

        public void onProviderEnabled(String p) {
        }

        public void onProviderDisabled(String p) {
        }

        public void onStatusChanged(String p, int status, Bundle extras) {
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setOnMapLongClickListener(onMapLongClickListener);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //顯示路況
        mMap.setTrafficEnabled(true);

        // 用經緯度建立位置的座標物件
        LatLng place = new LatLng(CurLati, CurLong);
        Log.d(TAG, "我的座標 - 經度 : " + CurLong + "  , 緯度 : " + CurLati + "  , 速度 : " + Speed);    //debug

        // 移動地圖
        moveMap(place);

        if(marker0!=null) marker0.remove();  //清除指定的Marker
        //marker0.setIcon();
        double dy=CurLati-CurLati0;
        double dx=CurLong-CurLong0;
        double dir;
        if((dx!=0 || dy!=0) && Speed>1) {  //車速太慢時不計算方向，不然會一直打轉
            dir=Math.atan2(dy,dx)*180/Math.PI; //從X軸算起正負180度
            if(dir<0) dir=dir+360; //從X軸逆時針算起0~360度
            myDirection=(float)dir; //保存目前的方向，從X軸逆時針算起0~360度
            dir=dir+22.5; //本程式採用８向方式，每一個方向包含４５度，將圓柱座標軸逆時針旋轉22.5度
            if(dir>=360) dir=dir-360; //若超過360度則減360
            CurDir=(int)dir/45;  //得到目前行進方向，從X軸逆時針0~7
        }
        //marker0.
        marker0=mMap.addMarker(new MarkerOptions().position(place).title("我的座標 - 經度 : " + CurLong + "  , 緯度 : " + CurLati));
        marker0.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon));
        //myDirection=315;
        marker0.setRotation(270f-myDirection);  //圖標０度時箭頭向下，座標系統０度向右，所以要修正２７０度
        CurLati0=CurLati;
        CurLong0=CurLong;

/* --- 以下這段程式會依新的目前位置不停記算新的路線，但是很耗時且耗網路流量
        //移除所有Marker，以及導航路徑
        mMap.clear();
        // 加入地圖標記，點到汽球才會顯示
        mMap.addMarker(new MarkerOptions().position(place).title("我的座標 - 經度 : " + CurLong + "  , 緯度 : " + CurLati));
        if(CurLati1!=0 && CurLong1!=0){
            LatLng place1 = new LatLng(CurLati1, CurLong1);
            mMap.addMarker(new MarkerOptions().position(place1).title("目的地座標 - 經度 : " + CurLong1 + "  , 緯度 : " + CurLati1));
            if(isNavigate==1){  //如果目前正在導航中
                // Getting URL to the Google Directions API
                String url = getDirectionsUrl(place, place1);
                DownloadTask downloadTask = new DownloadTask();
                // Start downloading json data from Google Directions, API
                downloadTask.execute(url);
            }
         }
           */

        //MyeditText51.setText(Speed+" 公里");
        MyeditText51.setText(String.valueOf(Speed));

        CheckCamera();  //檢查附近否有測速相機

        /*var marker = new google.maps.Marker({
                position: myLatLng,
                title: "Hello World!"
        });
        marker.setMap(map);*/
    }

    // 移動地圖到參數指定的位置
    private void moveMap(LatLng place) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(16)
                        .build();

        // 使用動畫的效果移動地圖
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //顯示路況
        mMap.setTrafficEnabled(true);
    }

    private void CheckCamera()  //搜尋附近是否有測速相機
    {
        int flag=0;
        int flagz=0;
        double x0,y0,x1,y1,zx0,zy0,zx1,zy1;
        for(int i=0;i<Camera.CameraLocationNo;i++)
        {
            //Only For Test----------------------------------------------------------------
            //Camera.CameraLocation[i][0]=CurLong+0.0005;
            //Camera.CameraLocation[i][1]=CurLati;
            //Camera.CameraLocation[i][5]=50;
            //CurLong=121.459362;
            //CurLati=25.153196;
            //CurDir=2;
            //Speed=60;
            //isAlarm=1;
            //---------------------------------------------------------------------------------

            //搜尋範圍100公尺
            x0=Camera.CameraLocation[i][0]-0.0015;
            x1=Camera.CameraLocation[i][0]+0.0015;
            y0=Camera.CameraLocation[i][1]-0.0015;
            y1=Camera.CameraLocation[i][1]+0.0015;
            //搜尋範圍50公尺
            zx0=Camera.CameraLocation[i][0]-0.00075;
            zx1=Camera.CameraLocation[i][0]+0.00075;
            zy0=Camera.CameraLocation[i][1]-0.00075;
            zy1=Camera.CameraLocation[i][1]+0.00075;

            int dirMatch=0; //用來記錄行車方向是否和測試照向同方向
            if(Camera.CameraLocation[i][4]==-1){  //相機方向等於-1代表所有方向都要發警報
                dirMatch=1;
            }
            else {
                for (int j = -2; j <= 2; j++) {
                    int dir = (int) Camera.CameraLocation[i][4] + j;
                    if (dir < 0) dir = dir + 8;
                    if (dir >= 8) dir = dir - 8;
                    if (dir == CurDir) dirMatch = 1;
                }
            }

            //
            if(dirMatch==1 && CurLati>y0 && CurLati<y1 && CurLong>x0 && CurLong<x1) {
                flag=1; //標記有找到相機
                //MyeditText51.setTextColor(Color.rgb(255, 0, 0));
                Mybackground.setBackgroundColor(Color.RED);
                MyeditText51.setText(String.valueOf(Speed));

                if(isAlarm==1 && isAlarmz==0) { //目前有警報，搜尋範圍30公尺內是否有測速相機
                    if(dirMatch==1 && CurLati>zy0 && CurLati<zy1 && CurLong>zx0 && CurLong<zx1 && Speed>Camera.CameraLocation[i][5]) {
                        flagz=1;
                        if(!mMediaPlayer.isPlaying()) {
                            //mMediaPlayer = MediaPlayer.create(this, R.raw.slowdown);//初始化MediaPlayer，音效檔backsound.mid
                            mMediaPlayer = MediaPlayer.create(this, R.raw.alarm);//初始化MediaPlayer，音效檔backsound.mid
                            mMediaPlayer.start();//用MediaPlayer播放聲音
                            isAlarmz=1;
                        }
                    }
                }
                //有測速相機，而且目前沒有警報or不是同一枝相機
                if(isAlarm==0 || AlarmID!=i) { //目前沒有警報，搜尋範圍120公尺內是否有測速相機
                    //MyeditText51.setText("199 公里");
                    //AlarmNo=i;
                    //i=CameraLocationNo+1;
                    //mMediaPlayer = new MediaPlayer();
                    if(!mMediaPlayer.isPlaying()) {
                        switch((int)Camera.CameraLocation[i][5]) {
                            case 40: mMediaPlayer = MediaPlayer.create(this, R.raw.limit040);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 50: mMediaPlayer = MediaPlayer.create(this, R.raw.limit050);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 60: mMediaPlayer = MediaPlayer.create(this, R.raw.limit060);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 70: mMediaPlayer = MediaPlayer.create(this, R.raw.limit070);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 80: mMediaPlayer = MediaPlayer.create(this, R.raw.limit080);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 90: mMediaPlayer = MediaPlayer.create(this, R.raw.limit090);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 100: mMediaPlayer = MediaPlayer.create(this, R.raw.limit100);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            case 110: mMediaPlayer = MediaPlayer.create(this, R.raw.limit110);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                            default: mMediaPlayer = MediaPlayer.create(this, R.raw.limit040);//初始化MediaPlayer，音效檔backsound.mid
                                break;
                        }
                        mMediaPlayer.start();//用MediaPlayer播放聲音
                        isAlarm=1;
                        AlarmID=i;
                    }
                }
            }
        }
        //if(flagz==0 && isAlarmz==1) isAlarmz=0;
        if(flag==0 && isAlarm==1) {  //都沒有找到測速相機，卻有警報，則關閉警報
            isAlarm=0;
            AlarmID=9999;
            isAlarmz=0;
            //MyeditText51.setTextColor(Color.rgb(255, 255, 255));
            Mybackground.setBackgroundColor(Color.rgb(0, 0, 255));
            MyeditText51.setText(String.valueOf(Speed));
            //MyeditText51.setText("199 公里");
        }
    }

    private Handler mHandler = new Handler(){
        int i = 0;
        @Override
        public void handleMessage(Message msg) {
            MyeditText51.setText(String.valueOf(Speed));
            //setUpMap();
         }

    };

    public class CheckSpeed extends Thread{
        boolean flag = true;

        //建構式
        public CheckSpeed(){
        }

        //方法：線程的執行方法
        public void run(){
            //int c=0;
            while(flag) {
                //c++;
                //CurLong0=CurLong;
                //CurLati0=CurLati;
                Location mLocation = mLocationManager.getLastKnownLocation("gps");
                //mLocation = mLocationManager.getLastKnownLocation("network");
                Speed = (int) (mLocation.getSpeed() * 3.6F);
                //CurLati=CurLati+0.0001;   //Test
                //CurLong=mLocation.getLongitude();
                //CurLati=mLocation.getLatitude();
                //if(CurLong0==CurLong && CurLati0==CurLati) Speed=0;
                //Speed=c;
                Log.d(TAG, "Thread: 我的座標 - 經度 : " + CurLong + "  , 緯度 : " + CurLati + "  , 速度 : " + Speed);    //debug

                try{
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                    Thread.sleep(2500);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    //常按螢幕時在地圖上放一個地標汽球
    private OnMapLongClickListener onMapLongClickListener = new OnMapLongClickListener() {

        @Override
        public void onMapLongClick(LatLng latlng) {
            Location location = new Location("LongPressLocationProvider");
            location.setLatitude(latlng.latitude);
            location.setLongitude(latlng.longitude);
            //设置精度
            location.setAccuracy(20);
            CurLati1=latlng.latitude;
            CurLong1 = latlng.longitude;
            LatLng place = new LatLng(CurLati1, CurLong1);
            if(marker1!=null) marker1.remove();  //清除先前指定的Marker
            marker1=mMap.addMarker(new MarkerOptions().position(place).title("點擊座標 - 經度 : " + CurLong1 + "  , 緯度 : " + CurLati1));
            //onLocationChangedListener.onLocationChanged(location);
        }
    };

    /** -----------------------------------------------------------------------------------------------------------------------------------------------------------
     * Get the direction-
              -----------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + ","
                + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + parameters;

        return url;
    }

    /**從URL下載JSON資料的方法**/
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            //Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /** 解析JSON格式 **/
    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(15);  //導航路徑寬度
                lineOptions.color(Color.BLUE); //導航路徑顏色

            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }
}

