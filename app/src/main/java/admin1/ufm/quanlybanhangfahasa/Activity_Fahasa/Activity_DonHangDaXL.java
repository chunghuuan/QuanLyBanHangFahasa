package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_DonHangDaXL extends AppCompatActivity {
    ImageButton imgBtnBack;
    ListView lvDonHangDXL;
    A_DonHang donHangAdapter;
    ArrayList<_DonHang> donHangList = new ArrayList<>();
    Data_DonHang donHangData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_donhangdaxuly);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgbtnBack();
        setLvDHDaXL();

    }

    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setLvDHDaXL(){
        donHangData = new Data_DonHang(Activity_DonHangDaXL.this);
        donHangList = donHangData.layDLDonHangXLNV();
        donHangAdapter = new A_DonHang(Activity_DonHangDaXL.this,R.layout.dong_donhang,donHangList);
        lvDonHangDXL.setAdapter(donHangAdapter);
    }


    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lvDonHangDXL = findViewById(R.id.lvDonHangDXL);
    }
}
