package project.hs.baeman;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.hs.baeman.Adpater.LocationRecyclerAdapter;

public class RecentLocation extends AppCompatActivity {
    EditText et_search;
    ImageButton btn_search;
    ImageButton cancel;
    Button btn_cur_location;
    Geocoder geocoder;

    private RecyclerView rv_recent;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_location);

        rv_recent = (RecyclerView)findViewById(R.id.rv_recent);
        SharedPreferences pref = getSharedPreferences("recent_location", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);
        for(int i = list_size - 1; i >= 0; i--){
            mItems.add(pref.getString("list_" + i, "최근주소"));
        }
        rv_recent.setHasFixedSize(true);
        adapter = new LocationRecyclerAdapter(getApplicationContext(), mItems);
        rv_recent.setLayoutManager(new LinearLayoutManager(getApplication()));
        rv_recent.setAdapter(adapter);

        //위치 정보 제공 동의
        setPermission();

        et_search = (EditText)findViewById(R.id.et_search);
        btn_search = (ImageButton)findViewById(R.id.btn_search);
        btn_cur_location = (Button)findViewById(R.id.btn_cur_location);
        rv_recent = (RecyclerView)findViewById(R.id.rv_recent);
        cancel = (ImageButton)findViewById(R.id.cancel);

        geocoder = new Geocoder(getApplicationContext());

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_str = et_search.getText().toString();
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            search_str, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);

                // 좌표(위도, 경도) 생성

//                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
//                MarkerOptions mOptions2 = new MarkerOptions();
//                mOptions2.title("search result");
//                mOptions2.snippet(address);
//                mOptions2.position(point);
                // 마커 추가
//                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));

                Intent intent = new Intent(RecentLocation.this, MapsActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
                finish();
            }
        });

        btn_cur_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPermission();
                //동의 했을 경우만 넘어가게 해야된다
                Intent intent = new Intent(RecentLocation.this, MapsActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void setPermission(){
        //동의 안했을 경우
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
    }
}
