package project.hs.baeman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import project.hs.baeman.Adpater.BucketRecyclerAdapter;

public class BucketActivity extends AppCompatActivity {

    Button btn_order;
    ImageView iv_empty;
    private RecyclerView rv_bucket;
    private RecyclerView.Adapter adapter;
    private ArrayList<project.hs.baeman.Item.MenuItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        btn_order = (Button)findViewById(R.id.btn_order);
        iv_empty = (ImageView)findViewById(R.id.iv_empty);
        rv_bucket = (RecyclerView)findViewById(R.id.rv_bucket);
        SharedPreferences pref = getSharedPreferences("bucket", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);
        if(list_size != 0) iv_empty.setVisibility(View.INVISIBLE);

        for(int i = list_size - 1; i >= 0; i--){
            mItems.add(new project.hs.baeman.Item.MenuItem(i, pref.getString("list_" + i + "_name", "메뉴이름"), pref.getInt("list_" + i + "_price", 0), "https://hanamsport.or.kr/www/images/contents/thum_detail.jpg"));
        }
        rv_bucket.setHasFixedSize(true);
        adapter = new BucketRecyclerAdapter(getApplicationContext(), mItems);
        rv_bucket.setLayoutManager(new LinearLayoutManager(getApplication()));
        rv_bucket.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
