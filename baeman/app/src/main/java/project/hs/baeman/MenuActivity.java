package project.hs.baeman;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import project.hs.baeman.Adpater.MenuPagerAdapter;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        MenuPagerAdapter menuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(menuPagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("menu", 1));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
