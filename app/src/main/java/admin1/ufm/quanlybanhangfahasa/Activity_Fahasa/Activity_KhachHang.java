package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._KhachHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_KhachHang extends AppCompatActivity {

    private ListView lvKhachHang;
    private EditText txtTenKH, txtQueQuan, txtNgaySinh;
    private TextView lbDHChXL, lbDHXL, lbTongGT;
    private Button btnXemLVDHKH, btnXoaKH, btnSuaKH,btnThemVoucher;
    private ImageButton imgBtnBack;
    private A_KhachHang khachHangAdapter;
    private Data_KhachHang khachHangData;
    private ArrayList<_KhachHang> khachHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_khachhang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setLvKhachHang();
        setClickLVKhachHang();
        setImgBtnBack();
    }

    public String themPhay(String a) {
        int b = Integer.parseInt(a);
        String kq = String.format("%,d%n", b);
        return kq;
    }

    private void setBtnThemVoucher(int position){
        btnThemVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_KhachHang.this,Activity_SoHuuVoucher.class);
                i.putExtra("maTK", Integer.parseInt(khachHangList.get(position).getMaTK()));
                i.putExtra("key", 1);
                startActivity(i);
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

    private void setLvKhachHang() {
        khachHangList = new ArrayList<>();
        khachHangData = new Data_KhachHang(Activity_KhachHang.this);
        khachHangList = khachHangData.getDLKH();
        khachHangAdapter = new A_KhachHang(Activity_KhachHang.this, R.layout.dong_khachhang, khachHangList);
        lvKhachHang.setAdapter(khachHangAdapter);
    }

    private int maKH = -1;

    private void setClickLVKhachHang() {
        lvKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtTenKH.setText(khachHangList.get(position).getTenKH());
                txtNgaySinh.setText(khachHangList.get(position).getNgaySinh());
                txtQueQuan.setText(khachHangList.get(position).getQueQuan());
                maKH = Integer.parseInt(khachHangList.get(position).getMaKH());
                setBtnSuaKH(khachHangList.get(position).getMaKH());
                if (!setThongTin()) {
                    Toast.makeText(Activity_KhachHang.this, "Tài khoản này chưa có đơn hàng nào !", Toast.LENGTH_SHORT).show();
                }
                setBtnThemVoucher(position);
                btnSuaKH.setVisibility(View.VISIBLE);
                btnXoaKH.setVisibility(View.VISIBLE);
                btnXemLVDHKH.setVisibility(View.VISIBLE);
                btnThemVoucher.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setBtnXemLVDHKH(){
        btnXemLVDHKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean setThongTin() {
        Data_DonHang donHangData = new Data_DonHang(this);
        ArrayList<String> thongke = donHangData.getThongKeDHKH(maKH);
        if (thongke.size() < 1) {
            return false;
        }
        lbDHXL.setText(thongke.get(0));
        lbDHChXL.setText(thongke.get(1));
        lbTongGT.setText(themPhay(thongke.get(2)));
        return true;
    }

    private void setBtnXoaKH() {

    }

    private void setBtnSuaKH(String maKH) {
        btnSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraRong()) {
                    _KhachHang khachHang = new _KhachHang();
                    khachHang.setTenKH(tenKH);
                    khachHang.setQueQuan(queQuan);
                    khachHang.setNgaySinh(ngaySinh);
                    khachHang.setMaKH(maKH);
                    if (khachHangData.suaKH(khachHang)) {
                        Toast.makeText(Activity_KhachHang.this, "Sửa thông tin thành công!", Toast.LENGTH_SHORT).show();
                        setLvKhachHang();
                    } else {
                        Toast.makeText(Activity_KhachHang.this, "Sửa thông tin thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private String tenKH, queQuan, ngaySinh;

    private boolean kiemTraRong() {
        tenKH = txtTenKH.getText().toString();
        queQuan = txtQueQuan.getText().toString();
        ngaySinh = txtNgaySinh.getText().toString();
        if (tenKH.equals("") || queQuan.equals("") || ngaySinh.equals("")) {
            Toast.makeText(this, "Không được để trống nội dung", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void anhXa() {
        btnThemVoucher = findViewById(R.id.btnThemVoucher);
        lvKhachHang = findViewById(R.id.lvKhachHang);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtQueQuan = findViewById(R.id.txtQueQuan);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        lbDHChXL = findViewById(R.id.lbDHChXL);
        lbDHXL = findViewById(R.id.lbDHXL);
        btnXemLVDHKH = findViewById(R.id.btnXemLVDHKH);
        lbTongGT = findViewById(R.id.lbTongGT);
        btnXoaKH = findViewById(R.id.btnXoaKH);
        btnSuaKH = findViewById(R.id.btnSuaKH);
        btnSuaKH.setVisibility(View.INVISIBLE);
        btnXoaKH.setVisibility(View.INVISIBLE);
        btnXemLVDHKH.setVisibility(View.INVISIBLE);
        btnThemVoucher.setVisibility(View.INVISIBLE);
        imgBtnBack = findViewById(R.id.imgBtnBack);
    }
}