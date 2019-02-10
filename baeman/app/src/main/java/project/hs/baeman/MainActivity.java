package project.hs.baeman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.MessageDigest;
import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import project.hs.baeman.Adpater.AutoScrollAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AutoScrollViewPager autoViewPager;
    LinearLayout location;
    NavigationView navigationView;
    TextView tv_locaion;
    ImageButton koreanfood;
    ImageButton boonsik;
    ImageButton donggas;
    ImageButton chicken;
    ImageButton pizza;
    ImageButton jjajang;
    ImageButton zokbal;
    ImageButton nightfood;
    ImageButton zzim;

    ImageButton ib_bucket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        koreanfood = (ImageButton)findViewById(R.id.koreanfood);
        boonsik = (ImageButton)findViewById(R.id.boonsik);
        donggas = (ImageButton)findViewById(R.id.donggas);
        chicken = (ImageButton)findViewById(R.id.chicken);
        pizza = (ImageButton)findViewById(R.id.pizza);
        jjajang = (ImageButton)findViewById(R.id.jjajang);
        zokbal = (ImageButton)findViewById(R.id.zokbal);
        nightfood = (ImageButton)findViewById(R.id.nightfood);
        zzim = (ImageButton)findViewById(R.id.zzim);



        ImageButton.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch(v.getId()) {
                    case R.id.koreanfood:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 0);
                        startActivity(intent);
                        break;
                    case R.id.boonsik:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 1);
                        startActivity(intent);
                        break;
                    case R.id.donggas:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 2);
                        startActivity(intent);
                        break;
                    case R.id.chicken:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 3);
                        startActivity(intent);
                        break;
                    case R.id.pizza:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 4);
                        startActivity(intent);
                        break;
                    case R.id.jjajang:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 5);
                        startActivity(intent);
                        break;
                    case R.id.zokbal:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 6);
                        startActivity(intent);
                        break;
                    case R.id.nightfood:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 7);
                        startActivity(intent);
                        break;
                    case R.id.zzim:
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menu", 8);
                        startActivity(intent);
                        break;
                }
            }
        };
        koreanfood.setOnClickListener(btnClickListener);
        boonsik.setOnClickListener(btnClickListener);
        donggas.setOnClickListener(btnClickListener);
        chicken.setOnClickListener(btnClickListener);
        pizza.setOnClickListener(btnClickListener);
        jjajang.setOnClickListener(btnClickListener);
        zokbal.setOnClickListener(btnClickListener);
        nightfood.setOnClickListener(btnClickListener);
        zzim.setOnClickListener(btnClickListener);


        getHashKey(getApplicationContext());//키 해시 구하는 함수

        ArrayList<String> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add("http://bucoco.kr/images/banner_image/1.png");
        data.add("http://bucoco.kr/images/banner_image/2.png");
        data.add("http://bucoco.kr/images/banner_image/3.png");
        data.add("http://bucoco.kr/images/banner_image/4.png");
        data.add("http://bucoco.kr/images/banner_image/5.png");
        data.add("http://bucoco.kr/images/banner_image/6.png");
        data.add("http://bucoco.kr/images/banner_image/7.png");

        autoViewPager = (AutoScrollViewPager)findViewById(R.id.autoViewPager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //set
        //((BaemanApplication)this.getApplication()).setData("foo");
        // get


//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) not_login_layout.getLayoutParams();
//        params.weight = 0;
//        not_login_layout.setLayoutParams(params);

        navigationView.setNavigationItemSelectedListener(this);
        //https://s3.ap-northeast-2.amazonaws.com/project-hs/default_profile.png

        //위치 클릭했을때
        location = (LinearLayout)findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecentLocation.class);
                startActivity(intent);
            }
        });

        ib_bucket = (ImageButton)(navigationView.getHeaderView(0).findViewById(R.id.bucket));
        ib_bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BucketActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.notice) {
            // Handle the camera action
        } else if (id == R.id.event) {

        } else if (id == R.id.question) {

        } else if (id == R.id.setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //키 해시값 구하는 함수
    @Nullable
    public static String getHashKey(Context context) {
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
        if (keyHash != null) {
            return keyHash;
        } else {
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String userNickname =  ((BaemanApplication)this.getApplication()).getUserNickname();
        LinearLayout not_login_layout = (LinearLayout)navigationView.getHeaderView(0).findViewById(R.id.not_login_layout);
        LinearLayout login_layout = (LinearLayout)navigationView.getHeaderView(0).findViewById(R.id.login_layout);

        if(userNickname != null){
            TextView user_nickname = (TextView)(navigationView.getHeaderView(0).findViewById(R.id.user_nickname));
            user_nickname.setText(userNickname);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) not_login_layout.getLayoutParams();
            params.height = 0;
            not_login_layout.setLayoutParams(params);

            Button btn_logout= (Button)(navigationView.getHeaderView(0).findViewById(R.id.btn_logout));
            btn_logout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    //토큰값 버리기
                    SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("token");
                    editor.commit();
                    //앱 변수 버리기
                    ((BaemanApplication)getApplication()).setUserEmailId(null);
                    ((BaemanApplication)getApplication()).setUserNickname(null);
                    ((BaemanApplication)getApplication()).setJwt(null);
                }
            });
        }else{
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) login_layout.getLayoutParams();
            params.height = 0;
            login_layout.setLayoutParams(params);

            Button btn_login = (Button)(navigationView.getHeaderView(0).findViewById(R.id.btn_login));
            btn_login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            });
        }

        tv_locaion = (TextView)findViewById(R.id.location_name);
        SharedPreferences pref = getSharedPreferences("address", MODE_PRIVATE);
        String address = pref.getString("data", "현재 위치 등록 해주세요");
        String []splitStr2 = address.split(" ");
        String address2 = "";
        for(int i = 2; i >= 0; i--){
            if(i != 0) address2 += splitStr2[splitStr2.length - 2 - i] + " ";
            else address2 += splitStr2[splitStr2.length - 2 - i];
        }
        tv_locaion.setText(address2);
    }
}
