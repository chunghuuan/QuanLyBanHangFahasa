package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_GioHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_GioHang;
import admin1.ufm.quanlybanhangfahasa.MucKhac.ViewPagerAdapterFahasa3;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_TrangChu3 extends AppCompatActivity implements Dialog_GioHang.ExampleDialogListener {

    ViewPager2 viewPager2fahasa;
    BottomNavigationView bn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_trangchu3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhXa();
        nhanGT();
        setUpViewPager();
        setUpNav();

    }

    private void setUpNav() {
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_fahasa_mathang:
                        viewPager2fahasa.setCurrentItem(0);
                        break;
                    case R.id.nav_fahasa_giohang:
                        viewPager2fahasa.setCurrentItem(1);
                        break;
                    case R.id.nav_fahasa_tuychon:
                        viewPager2fahasa.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapterFahasa3 vPAdapter = new ViewPagerAdapterFahasa3(this, maTK);
        viewPager2fahasa.setAdapter(vPAdapter);
        viewPager2fahasa.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        viewPager2fahasa.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bn.getMenu().findItem(R.id.nav_fahasa_mathang).setChecked(true);
                        break;
                    case 1:
                        bn.getMenu().findItem(R.id.nav_fahasa_giohang).setChecked(true);
                        break;
                    case 2:
                        bn.getMenu().findItem(R.id.nav_fahasa_tuychon).setChecked(true);
                        break;
                }
            }
        });

    }

    private void anhXa() {
        bn = findViewById(R.id.bottom_fahasa_nav2);
        viewPager2fahasa = findViewById(R.id.viewPagerFahasa2);
    }

    private String maTK;

    public void nhanGT() {
        Intent i = getIntent();
        this.maTK = i.getStringExtra("maTK");// Nhận giá trị từ Activity Đăng nhập
    }

    @Override
    public void openThanhToan(int maTK, ArrayList<_Sach> sachGioHangList) { ;
        Data_GioHang gioHangData = new Data_GioHang(this);
        gioHangData.xoaGioHang(maTK);// Xóa dòng này
        finish();
        if (gioHangData.themGioHang(maTK, sachGioHangList)) {
            Intent i = new Intent(Activity_TrangChu3.this, Activity_ThanhToan.class);
            i.putExtra("maTK", maTK);
            startActivity(i);
        } else
            Toast.makeText(Activity_TrangChu3.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();


    }
}
