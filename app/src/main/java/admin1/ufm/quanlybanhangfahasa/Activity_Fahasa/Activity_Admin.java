package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_Admin extends AppCompatActivity {
    private Button btnTXSNhanVien, btnTXSTaiKhoan, btnTaoBaoCao, btnThongKe,btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_admin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        nhanGT();
        setBtnThongKe();
        setBtnTXSNhanVien();
        setBtnDangXuat();
    }

    private int maTK = -1;

    private void setBtnDangXuat(){
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Admin.this,Activity_DangNhap.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void nhanGT() {
        Intent i = getIntent();
        this.maTK = i.getIntExtra("maTK", -1);// Nhận giá trị từ Activity Đăng nhập
    }

    private void setBtnThongKe() {
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Admin.this, Activity_ThongKeBanHang.class);
                startActivity(i);
            }
        });
    }

    private void setBtnTXSNhanVien() {
        btnTXSNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Admin.this, Activity_NhanVien.class);
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        btnTXSNhanVien = findViewById(R.id.btnTXSNhanVien);
        btnTaoBaoCao = findViewById(R.id.btnTaoBaoCao);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnDangXuat=findViewById(R.id.btnDangXuat);
    }
}