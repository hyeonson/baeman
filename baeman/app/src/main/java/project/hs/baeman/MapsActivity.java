package project.hs.baeman;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Geocoder geocoder;
    int type;
    String latitude = null;
    String longitude = null;
    Double d_latitude;
    Double d_longitude;

    EditText et_detail;
    Button btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        geocoder = new Geocoder(getApplicationContext());

        et_detail = (EditText)findViewById(R.id.et_detail);
        btn_finish = (Button)findViewById(R.id.btn_finish);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        Intent intent2 = getIntent();
        type = intent2.getIntExtra("type", 1);
        if (type == 1) {
            latitude = intent2.getStringExtra("latitude");
            longitude = intent2.getStringExtra("longitude");
        } else {

        }

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위도,경도 입력 후 변환 버튼 클릭
                List<Address> addressList = null;
                try {
                    addressList = geocoder.getFromLocation(
                            d_latitude, // 위도
                            d_longitude, // 경도
                            10); // 얻어올 값의 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                String []splitStr = addressList.get(0).toString().split(",");

                if (addressList != null) {
                    if (addressList.size()==0) {

                    } else {
                        String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                        address += " " + et_detail.getText().toString();
                        SharedPreferences pref = getSharedPreferences("address", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("data", address);
                        editor.commit();

                        pref = getSharedPreferences("recent_location", MODE_PRIVATE);
                        int list_size = pref.getInt("list_size", 0);
                        editor = pref.edit();
                        editor.putString("list_" + list_size, address);
                        editor.putInt("list_size", list_size + 1);
                        editor.commit();
                    }
                }
                //서버한테 주소 보내고
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (type == 1) {
            d_latitude = Double.parseDouble(latitude);
            d_longitude = Double.parseDouble(longitude);
            LatLng latLng = new LatLng(d_latitude, d_longitude);
            MarkerOptions mOptions2 = new MarkerOptions();
            mOptions2.title("배달받을곳");
            mOptions2.snippet("위치설정");
            mOptions2.position(latLng);
            mOptions2.draggable(true);
            // 마커 추가
            mMap.addMarker(mOptions2);
            // 해당 좌표로 화면 줌
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        } else {
            final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String provider = location.getProvider();
            d_longitude = location.getLongitude();
            d_latitude = location.getLatitude();

            LatLng latLng = new LatLng(d_latitude, d_longitude);
            MarkerOptions mOptions2 = new MarkerOptions();
            mOptions2.title("배달받을곳");
            mOptions2.snippet("위치설정");
            mOptions2.position(latLng);
            mOptions2.draggable(true);
            // 마커 추가
            mMap.addMarker(mOptions2);
            // 해당 좌표로 화면 줌
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        }
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("배달받는곳").snippet("위치설정").draggable(true));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //우측 상단에 위치버튼
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        //+,- 버튼
        mMap.getUiSettings().setZoomControlsEnabled(true);


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
            }
            @Override
            public void onMarkerDrag(Marker marker) {
            }
            @Override
            public void onMarkerDragEnd(Marker marker) {
                //여기서 경도, 위도 다시 설정하면 될듯??
                d_latitude = marker.getPosition().latitude;
                d_longitude = marker.getPosition().longitude;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
