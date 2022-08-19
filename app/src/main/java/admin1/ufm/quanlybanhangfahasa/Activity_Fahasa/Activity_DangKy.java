package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.DinhDang_Fahasa.DinhDangNgay;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._KhachHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Gmail;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_DangKy extends AppCompatActivity {
    private EditText txtHoTen, txtSDT, txtEmail, txtNgaySinh, txtMatKhau, txtReMatKhauMoi, txtMaOTP;
    private Spinner spQueQuan;
    private Button btnDangKy, btnReset;
    private Data_TaiKhoan taiKhoanData;
    private Data_KhachHang khachHangData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_dangky);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mauVangChuDao)));
        actionBar.setTitle("Đăng ký tài khoản");
        actionBar.setDisplayHomeAsUpEnabled(true);
        anhXa();
        setBtnReset();
        setSpQueQuan();
        setBtnDangKy();
    }

    private void setBtnReset() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaDL();
            }
        });
    }

    private void setBtnDangKy() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kiemTra()) {
                    return;
                }
                taiKhoanData = new Data_TaiKhoan(Activity_DangKy.this);
                khachHangData = new Data_KhachHang(Activity_DangKy.this);
                _TaiKhoan taiKhoan = new _TaiKhoan();
                taiKhoan.setMatkhau(matKhauMoi);
                taiKhoan.setSdt(sdt);
                taiKhoan.setEmail(email);
                taiKhoan.setMaCV(3);
                taiKhoan.setNgayDangKy(layNgayHT());
                int code = (int) Math.floor(((Math.random() * 899999) + 10000)); // RANDOM 6 số
                Gmail gmail = new Gmail(Activity_DangKy.this);
                boolean kq = gmail.sendMail("Mã xác nhận OTP", "Mã xác nhận OTP của bạn là: " + code, email);
                AlertDialog.Builder myDialog = new AlertDialog.Builder(Activity_DangKy.this);
                myDialog.setTitle("Đã gửi OTP đến Gmail của bạn ! \nHãy nhập vào:");
                EditText maOTP = new EditText(Activity_DangKy.this);
                maOTP.setInputType(InputType.TYPE_CLASS_NUMBER);
                myDialog.setView(maOTP);
                int count = 0;
                myDialog.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Hủy bỏ
                    }
                });
                myDialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!kq) {
                            Toast.makeText(Activity_DangKy.this, "Gửi OTP thất bại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int iMaOTP = Integer.parseInt(maOTP.getText().toString());
                        if (iMaOTP != code) {
                            Toast.makeText(Activity_DangKy.this, "SAI MÃ OTP !", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (taiKhoanData.themTK(taiKhoan)) {
                            _KhachHang khachHang = new _KhachHang();
                            khachHang.setTenKH(hoTen);
                            khachHang.setNgaySinh(ngaySinh);
                            khachHang.setQueQuan(spQueQuan.getSelectedItem().toString());
                            if (khachHangData.themKH(khachHang)) {
                                Toast.makeText(Activity_DangKy.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent();
                                i.putExtra("SDT", sdt);
                                i.putExtra("matKhau", matKhauMoi);
                                setResult(RESULT_OK, i);
                                finish();
                            }
                        }
                    }
                });
                myDialog.show();
            }
        });
    }

    private String layNgayHT() {
        Calendar cld = Calendar.getInstance();
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH) + 1;
        int day = cld.get(Calendar.DAY_OF_MONTH);
        String Nday = "" + day;
        String Nmonth = "" + month;
        if (day < 10 && day > 0) {
            Nday = "0" + day;
        }
        if (month < 10 && month > 0) {
            Nmonth = "0" + month;
        }
        String ngayHienTai = Nday + "/" + Nmonth + "/" + year;
        return ngayHienTai;
    }

    private void setSpQueQuan() {
        String[] tinhThanh = {"An Giang", "Bà Rịa-Vũng Tàu", "Bạc Liêu", "Bắc Kạn", "Bắc Giang", "Bắc Ninh",
                "Bến Tre", "Bình Dương", "Bình Định", "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng", "Cần Thơ (TP)",
                "Đà Nẵng (TP)", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang",
                "Hà Nam", "Hà Nội (TP)", "Hà Tây", "Hà Tĩnh", "Hải Dương", "Hải Phòng (TP)", "Hòa Bình", "Hồ Chí Minh (TP)",
                "Hậu Giang", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lào Cai", "Lạng Sơn",
                "Lâm Đồng", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên",
                "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La",
                "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên – Huế", "Tiền Giang",
                "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tinhThanh);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQueQuan.setAdapter(adapter);
    }


    private void anhXa() {
        txtSDT = findViewById(R.id.txtSDT);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        txtReMatKhauMoi = findViewById(R.id.txtReMatKhauMoi);
        txtEmail = findViewById(R.id.txtEmail);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        btnDangKy = findViewById(R.id.btnDangKy);
        spQueQuan = findViewById(R.id.spQueQuan);
        btnReset = findViewById(R.id.btnReset);
        TextWatcher textWatcherNgay = new DinhDangNgay(txtNgaySinh);
        txtNgaySinh.addTextChangedListener(textWatcherNgay);
    }

    private void xoaDL() {
        txtSDT.setText("");
        txtHoTen.setText("");
        txtMatKhau.setText("");
        txtReMatKhauMoi.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
    }

    private String sdt, email, hoTen, ngaySinh, matKhauMoi, reMatKhauMoi;

    private boolean kiemTra() {
        sdt = txtSDT.getText().toString();
        email = txtEmail.getText().toString();
        hoTen = txtHoTen.getText().toString();
        ngaySinh = txtNgaySinh.getText().toString();
        matKhauMoi = txtMatKhau.getText().toString();
        reMatKhauMoi = txtReMatKhauMoi.getText().toString();
        if (sdt.equals("") || hoTen.equals("") || matKhauMoi.equals("") || reMatKhauMoi.equals("") || ngaySinh.equals("") || email.equals("")) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!matKhauMoi.equals(reMatKhauMoi)) {
            Toast.makeText(this, "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
//
//    private boolean taoTK(){
//
//    }
//
//    private void setBtnDangKy(){
//        btnDangKy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (kiemTraRong()){
//                    taiKhoanData = new Data_TaiKhoan(this);
//                    _TaiKhoan taiKhoan = new _TaiKhoan();
//                    taiKhoan.setMatkhau(reMatKhauMoi);
//                    taiKhoan.setSdt(sdt);
//                    taiKhoan.setEmail("");
//                    taiKhoanData
//                }
//            }
//        });
//    }
}