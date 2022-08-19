package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_DangNhap extends AppCompatActivity {
    TextInputEditText txtTaiKhoan, txtMatKhau;
    Button btnDangNhap, btnDangKy;
    Data_TaiKhoan data;

    private final void anhXa() {
        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKyMoi);
    }

    private boolean kiemTraRong() {
        String taikhoan = txtTaiKhoan.getText().toString().trim();
        String matkhau = txtMatKhau.getText().toString().trim();
        if (taikhoan.equals("") || matkhau.equals("")) {
            Toast.makeText(Activity_DangNhap.this, "Bạn chưa nhập tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            this.taiKhoan = taikhoan;
            this.matKhau = matkhau;
            return true;
        }
    }

    private void setBtnDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_DangNhap.this, Activity_DangKy.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String tenTaiKhoan = data.getStringExtra("SDT");
                String matKhau = data.getStringExtra("matKhau");
                txtTaiKhoan.setText(tenTaiKhoan);
                txtMatKhau.setText(matKhau);
            }
        }
    }

    private String taiKhoan, matKhau;

    private void chuyenActivity(int maCV) {
        if (maCV == 2) {
            Data_NhanVien nhanVienData = new Data_NhanVien(this);
            boolean kq = nhanVienData.kiemTraHieuLuc(Integer.parseInt(data.getMaTK()));
            if (kq){
                Toast.makeText(Activity_DangNhap.this, "Nhân viên hết hiệu lực đăng nhập !", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent i = new Intent(Activity_DangNhap.this, Activity_TrangChu.class);
            i.putExtra("maTK", data.getMaTK()); // Gửi dữ liệu sang Activity Trang chủ
            i.putExtra("maCV", String.valueOf(maCV));
            finish();
            startActivity(i);
            Toast.makeText(Activity_DangNhap.this, "Nhân viên đăng nhập thành công !", Toast.LENGTH_SHORT).show();
        } else if (maCV == 3) {
            Intent i = new Intent(Activity_DangNhap.this, Activity_TrangChu3.class);
            i.putExtra("maTK", data.getMaTK());// Gửi dữ liệu sang Activity Trang chủ
            i.putExtra("maCV", String.valueOf(maCV));
            finish();
            startActivity(i);
            Toast.makeText(Activity_DangNhap.this, "Khách hàng đăng nhập thành công !", Toast.LENGTH_SHORT).show();
        } else if (maCV == 1) {
            Intent i = new Intent(Activity_DangNhap.this, Activity_Admin.class);
            i.putExtra("maTK", data.getMaTK());// Gửi dữ liệu sang Activity Trang chủ
            finish();
            startActivity(i);
            Toast.makeText(Activity_DangNhap.this, "Quản trị viên đăng nhập thành công !", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(Activity_DangNhap.this, "Sai tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_dangnhap);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setBtnDangKy();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraRong()) {
                    data = new Data_TaiKhoan(Activity_DangNhap.this);
                    int maCV = data.kiemTraTK(taiKhoan, matKhau);
                    chuyenActivity(maCV);
                }
            }
        });
    }
}
