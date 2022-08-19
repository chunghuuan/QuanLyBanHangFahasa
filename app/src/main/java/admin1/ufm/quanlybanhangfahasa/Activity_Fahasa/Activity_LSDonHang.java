package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_LSDonHang extends AppCompatActivity {
    ImageButton imgBtnBack;
    ListView lvDonHangDXL;
    A_DonHang donHangAdapter;
    ArrayList<_DonHang> donHangList = new ArrayList<>();
    Data_DonHang donHangData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_lsdonhang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        nhanGT();
        setImgbtnBack();
        setLvDHDaXL();
    }

    private int maTK;
    private int maKH;
    private void nhanGT() {
        Intent i = getIntent();
        this.maTK = i.getIntExtra("maTK", -1);// Nhận giá trị từ Activity Đăng nhập
        Data_KhachHang khachHangData = new Data_KhachHang(this);
        this.maKH = khachHangData.layMaKH(this.maTK);
    }

    private void setLvDHDaXL() {
        donHangData = new Data_DonHang(this);
        donHangList = donHangData.layDLDonHangXL(this.maKH);
        donHangAdapter = new A_DonHang(this, R.layout.dong_donhang, donHangList);
        lvDonHangDXL.setAdapter(donHangAdapter);
        if (lvDonHangDXL.getCount()<1){
            Toast.makeText(this," Bạn không có đơn hàng nào! ",Toast.LENGTH_SHORT).show();
        }
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lvDonHangDXL = findViewById(R.id.lvDonHangDXL);
    }

    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}