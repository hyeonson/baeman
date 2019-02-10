package project.hs.baeman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.hs.baeman.Adpater.DetailPagerAdapter;
import project.hs.baeman.Data.Menu;
import project.hs.baeman.Data.RestaurantDetail;
import project.hs.baeman.Network.ApiService;
import project.hs.baeman.Response.ResDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestDetailActivity extends AppCompatActivity {

    static public int restaurant_detail_number;
    static public String restaurant_name;
    static public int restaurant_minimum;
    static public int restaurant_order_count;
    static public String restaurant_payment;
    static public String restaurant_phone;
    static public String restaurant_business;
    static public String restaurant_dayoff;
    static public String restaurant_del_location;
    static public String restaurant_location;
    static public int restaurant_status;
    static public List<Menu> restaurant_menu_list;
    private TextView tv_rest_name;
    private TextView tv_payment;
    private TextView tv_minimum;
    private Button btn_call;
    private Button btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_detail);

        restaurant_menu_list = new ArrayList<Menu>();

        tv_rest_name = (TextView)findViewById(R.id.tv_rest_name);
        tv_payment = (TextView)findViewById(R.id.tv_payment);
        tv_minimum = (TextView)findViewById(R.id.tv_minimum);
        btn_call = (Button)findViewById(R.id.btn_call);
        btn_order = (Button)findViewById(R.id.btn_order);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = restaurant_phone;
                tel.replaceAll("-", "");
                tel = "tel:" + tel;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestDetailActivity.this, BucketActivity.class);
                startActivity(intent);
            }
        });

        restaurant_detail_number = getIntent().getIntExtra("restaurantNumber", 1);
        showDetail();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDetail()
    {

        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResDetail> res = apiService.showRestDetail(restaurant_detail_number);
        res.enqueue(new Callback<ResDetail>() {
            @Override
            public void onResponse(Call<ResDetail> call, Response<ResDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        RestaurantDetail rest = response.body().getData();
                        restaurant_name = rest.getRestaurantName();
                        restaurant_minimum = rest.getMinimum();
                        restaurant_order_count = rest.getOrderCount();
                        restaurant_payment = rest.getPayment();
                        restaurant_phone = rest.getPhoneNumber();
                        restaurant_business = rest.getBusinessTime();
                        restaurant_dayoff = rest.getDayOff();
                        restaurant_del_location = rest.getDeliveryLocation();
                        restaurant_location = rest.getRestaurantLocation();
                        restaurant_status = rest.getStatus();
                        restaurant_menu_list.clear();
                        restaurant_menu_list = rest.getMenu();
                        tv_rest_name.setText(restaurant_name);
                        tv_payment.setText(restaurant_payment);
                        tv_minimum.setText(restaurant_minimum + "원");

                        DetailPagerAdapter detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
                        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
                        viewPager.setAdapter(detailPagerAdapter);
                        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "상세 조회 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }
}
