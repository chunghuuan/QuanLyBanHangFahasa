package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import admin1.ufm.quanlybanhangfahasa.MucKhac.ViewPagerAdapterFahasa;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_TrangChu extends AppCompatActivity  {
    ViewPager2 viewPager2fahasa;
    BottomNavigationView bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_trangchu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhXa();
        nhanGT();

        setUpViewPager();
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_fahasa_capnhat:
                        viewPager2fahasa.setCurrentItem(0);break;
                    case R.id.nav_fahasa_donhang:
                        viewPager2fahasa.setCurrentItem(1);break;
                    case R.id.nav_fahasa_tuychon:
                        viewPager2fahasa.setCurrentItem(2);break;
                }
                return true;
            }
        });
    }

    private void anhXa() {
        bn = findViewById(R.id.bottom_fahasa_nav);
        viewPager2fahasa = findViewById(R.id.viewPagerFahasa);
    }

    private String maTK;
    public void nhanGT(){
        Intent i = getIntent();
        this.maTK = i.getStringExtra("maTK");// Nhận giá trị từ Activity Đăng nhập
    }

    private void setUpViewPager() {
        ViewPagerAdapterFahasa vPAdapter = new ViewPagerAdapterFahasa(this,maTK);
        viewPager2fahasa.setAdapter(vPAdapter);
        viewPager2fahasa.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        viewPager2fahasa.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: bn.getMenu().findItem(R.id.nav_fahasa_capnhat).setChecked(true); break;
                    case 1: bn.getMenu().findItem(R.id.nav_fahasa_donhang).setChecked(true); break;
                    case 2: bn.getMenu().findItem(R.id.nav_fahasa_tuychon).setChecked(true); break;
                }
            }
        });
    }
}
