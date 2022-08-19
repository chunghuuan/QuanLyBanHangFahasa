package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_DoiMatKhau;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activiti_ThongTinCaNhan extends AppCompatActivity {
    private TextView lbThietLapMK,lbNamSinhTK,lbQueQuanTK,lbSDTTK,lbEmailTK,lbChucVu,lbTenTK;
    ImageButton imgBtnBack;
    Data_TaiKhoan taiKhoanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_thongtincanhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgBtnBack();
        nhanGT();
        doimatkhau();
        setThongTin();
    }

    private String maTK;
    private int maCV;
    private void nhanGT(){
        Intent i = getIntent();
        this.maTK = i.getStringExtra("maTK");// Nhận giá trị từ Activity Đăng nhập
        int maCV = i.getIntExtra("maCV",-1);
        this.maCV = maCV;

    }

    private void setThongTin(){
        taiKhoanData = new Data_TaiKhoan(Activiti_ThongTinCaNhan.this);
        ArrayList<String> thongtin = taiKhoanData.layThongTin(maTK);
        lbChucVu.setText(thongtin.get(0));
        lbTenTK.setText(thongtin.get(1));
        lbNamSinhTK.setText(thongtin.get(2));
        lbQueQuanTK.setText(thongtin.get(3));
//        lbSDTTK.setText(thongtin.get(4));
        lbEmailTK.setText(thongtin.get(5));
    }

    private void doimatkhau(){
        lbThietLapMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void setImgBtnBack(){
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lbThietLapMK = findViewById(R.id.lbThietLapMK);
        lbNamSinhTK = findViewById(R.id.lbNamSinhTK);
        lbQueQuanTK = findViewById(R.id.lbQueQuanTK);
        lbSDTTK = findViewById(R.id.lbSDTTK);
        lbEmailTK = findViewById(R.id.lbEmailTK);
        lbChucVu =findViewById(R.id.lbChucVu);
        lbTenTK = findViewById(R.id.lbTenTK);
    }

    private void openDialog(){
        Dialog_DoiMatKhau dialog_doiMatKhau = new Dialog_DoiMatKhau();
        Bundle bundle = new Bundle();
        bundle.putString("maTK",this.maTK);
        dialog_doiMatKhau.setArguments(bundle);
        dialog_doiMatKhau.show(getSupportFragmentManager(),"ChangePassword");
    }
}
