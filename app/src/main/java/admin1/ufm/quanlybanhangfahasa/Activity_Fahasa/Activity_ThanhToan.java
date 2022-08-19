package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_ThanhToan;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_GioHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_HTTT;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_SoHuu;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Voucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._SoHuu;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Gmail;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_ThanhToan extends AppCompatActivity {
    private ListView lvGioHang;
    private TextView lbTamTinh, lbTenKH, lbSDT, lbGmail, lbThanhTienSauVch, lbQuyDinh, lbTyLeGiam, lbThanhTien;
    private Spinner spHTTT, spVoucher;
    private EditText txtMaVoucher, txtNoiNhanHang, txtGhiChu;
    private CheckBox chkXacNhan;
    private Button btnXacNhanDH;
    private ImageButton imgBtnBack;
    private A_ThanhToan thanhToanAdapter;
    private Data_KhachHang khachHangData;
    private Data_TaiKhoan taiKhoanData;
    private Data_HTTT htttData;
    private Data_Voucher voucher;
    private Data_SoHuu soHuuData;
    private Data_GioHang gioHangData;
    private Data_DonHang donHangData;
    private Data_ChiTietDH chiTietDHData;
    private LinearLayout lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_thanhtoan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        nhanGT();
        Intent i = new Intent(Activity_ThanhToan.this, Activity_QuyDinh.class);
        startActivity(i);
        ;
        lv1.setVisibility(View.GONE);
        setLvGioHang();
        setThongTinKH();
        setThongTinTT();
        setLvGioHang();
        setLbQuyDinh();
        setImgBtnBack();
        setBtnXacNhanDH();


    }

    private void setBtnXacNhanDH() {
        btnXacNhanDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String yeCauKH = txtGhiChu.getText().toString();
                String noiNhanHang = txtNoiNhanHang.getText().toString();
                if (noiNhanHang.equals("")) {// r
                    Toast.makeText(Activity_ThanhToan.this, " Bạn chưa điền nơi nhận !", Toast.LENGTH_SHORT).show();
                    return; // CANCEL
                }
                String ngayLap, yeuCauKH, diaChiGiaoHang;
                int maHTTT, maVoucher, maKH, maNV = 101;
                donHangData = new Data_DonHang(Activity_ThanhToan.this);
                _DonHang donHang = new _DonHang();
                ngayLap = layNgayHT();
                donHang.setNgayLap(ngayLap);
                donHang.setYeuCauKH(yeCauKH);
                donHang.setMaHT(getPosHTTT());
                donHang.setMaVoucher(getMaVoucher());
                maKH = khachHangData.layMaKH(maTK);
                donHang.setMaKH(maKH);
                donHang.setMaNV(101);
                donHang.setDiaChiGiaoHang(noiNhanHang);
                if (sThanhTien.equals("")) {
                    donHang.setTongTien(tongTien);
                } else {
                    donHang.setTongTien(Float.parseFloat(sThanhTien));
                }
                donHangData = new Data_DonHang(Activity_ThanhToan.this);
                chiTietDHData = new Data_ChiTietDH(Activity_ThanhToan.this);
                if (!donHangData.themDH(donHang, maTK)) { // Thêm đơn hàng
                    Toast.makeText(Activity_ThanhToan.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (chiTietDHData.themMH(gioHangList)) { // Thêm từng mặt hàng
                    int maDH = donHangData.getMaDHCuoi();
                    Gmail gmail = new Gmail(Activity_ThanhToan.this);
                    if (!gmail.sendDaTaoDH(donHang, gioHangList, maTK,lbTenKH.getText().toString(),maDH)) {
                        Toast.makeText(Activity_ThanhToan.this, "Gửi thông báo thất bại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    gioHangData.xoaGioHang(maTK);
                    Intent i = new Intent(Activity_ThanhToan.this, Activity_TrangChu3.class);
                    i.putExtra("maTK", String.valueOf(maTK));
                    startActivity(i);
                    finish();
                }

            }
        });
    }


    private void setThongTinTT() {
        setSpHTTT();
        setSpSoHuuVoucher();
    }

    private ArrayList<_SoHuu> soHuuList;

    private boolean setSpSoHuuVoucher() {
        soHuuList = new ArrayList<>();
        soHuuData = new Data_SoHuu(this);
        soHuuList = soHuuData.getDLSoHuu(maTK);
        for (int i = 0; i < soHuuList.size(); i++) {
            if (soHuuList.get(i).getSoLuong() == 0) {
                soHuuList.remove(i);
            }
        }
        int dodai = soHuuList.size();
        ArrayList<String> dlVoucher = new ArrayList<>();
        dlVoucher.add("Không sử dụng");
        int count = 0;
        if (dodai < 1) {
            setMaVoucher("0");
        } else {
            for (int i = 0; i < dodai; i++) {
                if (soHuuList.get(i).getSoLuong() > 0) {
                    dlVoucher.add(soHuuList.get(i).getMaVoucher() + "(SL:" + soHuuList.get(i).getSoLuong() + ")");
                    count++;
                }
            }
        }
        String[] arr = new String[count + 1];
        arr = dlVoucher.toArray(arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVoucher.setAdapter(adapter);


        spVoucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setMaVoucher("0");
                    lbTyLeGiam.setText("0%");
                } else {
                    setMaVoucher(soHuuList.get(position - 1).getMaVoucher());
                    setLbTyLeGiam(position - 1);
                }

                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setGravity(Gravity.LEFT);
                if (spVoucher.getSelectedItem().toString().length() > 13) {
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                } else {
                    ((TextView) parent.getChildAt(0)).setTextSize(16);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return true;
    }

    String sThanhTien = "";

    private void setLbTyLeGiam(int position) {
        float tyLeGiam = soHuuList.get(position).getTyLeGiam2();
        int kq = (int) (tyLeGiam * 100);
        lbTyLeGiam.setText(kq + "%");
        lv1.setVisibility(View.VISIBLE);
        float fTongTien = Float.parseFloat(tongTien + "");
        sThanhTien = String.valueOf((1 - tyLeGiam) * fTongTien);
        String sThanhTien2 = sThanhTien.substring(0, sThanhTien.length() - 2) + " đ";
        lbThanhTienSauVch.setText(sThanhTien2);
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


    private int posHTTT = 1;

    public void setPosHTTT(int posCTKM) {
        this.posHTTT = posCTKM;
    }

    private String maVoucher = "0";

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getPosHTTT() {
        return posHTTT;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setSpHTTT() {
        htttData = new Data_HTTT(this);
        int dodai = htttData.getDLHTTT().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlHTTT = new ArrayList<>();
        for (int i = 0; i < dodai; i++) {
            dlHTTT.add(htttData.getDLHTTT().get(i).getTenHT());
        }
        arr = dlHTTT.toArray(arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHTTT.setAdapter(adapter);
        spHTTT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosHTTT(position + 1); // ?? Tại sao cần set như v trong khi có thể lấy được mã của nó ở trên ?
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpVoucher() {

    }

    int tongTien = 0;

    private void setThongTinKH() {
        for (int i = 0; i < gioHangList.size(); i++) {
            if (gioHangList.get(i).getGiaKM() != 0) {
                tongTien = tongTien + gioHangList.get(i).getGiaKM() * gioHangList.get(i).getSlMua();
            } else {
                tongTien = tongTien + gioHangList.get(i).getGiaGoc() * gioHangList.get(i).getSlMua();
            }
        }
        lbTamTinh.setText(tongTien + " đ");
        khachHangData = new Data_KhachHang(this);
        String tenKH = khachHangData.layTenKH1(this.maTK);
        String email = khachHangData.layEmail(this.maTK);
        lbTenKH.setText("" + tenKH);
        lbGmail.setText("" + email);
        lbSDT.setText("0941716706");
    }

    private void setLbQuyDinh() {
        lbQuyDinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_ThanhToan.this, Activity_QuyDinh.class);
                startActivity(i);
            }
        });

    }

    private void anhXa() {
        lbQuyDinh = findViewById(R.id.lbQuyDinh);
        lvGioHang = findViewById(R.id.lvGioHang);
        lbTamTinh = findViewById(R.id.lbTamTinh);
        lbTenKH = findViewById(R.id.lbTenKH);
        lbSDT = findViewById(R.id.lbSDT);
        lbGmail = findViewById(R.id.lbGmail);
        lbThanhTienSauVch = findViewById(R.id.lbThanhTienSauVch);
        spHTTT = findViewById(R.id.spHTTT);
        spVoucher = findViewById(R.id.spVoucher);
        txtMaVoucher = findViewById(R.id.txtMaVoucher);
        txtNoiNhanHang = findViewById(R.id.txtNoiNhanHang);
        txtGhiChu = findViewById(R.id.txtGhiChu);
        chkXacNhan = findViewById(R.id.chkXacNhan);
        btnXacNhanDH = findViewById(R.id.btnXacNhanDH);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lbTyLeGiam = findViewById(R.id.lbTyLeGiam);
        lbThanhTien = findViewById(R.id.lbThanhTien);
        lv1 = findViewById(R.id.lv1);
    }

    private void setImgBtnBack() {
        gioHangData = new Data_GioHang(this);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_ThanhToan.this, Activity_TrangChu3.class);
                i.putExtra("maTK", String.valueOf(maTK));
                startActivity(i);
                finish();
            }
        });
    }

    private ArrayList<_Sach> sachGioHangList;
    private int maTK = -1;

    private void nhanGT() {
        Intent i = getIntent();
        this.maTK = i.getIntExtra("maTK", -1);
    }

    private ArrayList<_GioHang> gioHangList;

    private void setLvGioHang() {
        gioHangData = new Data_GioHang(this);
        gioHangList = new ArrayList<>();
        gioHangList = gioHangData.getDLGioHang(this.maTK);

        gioHangData.getDLGioHang(this.maTK);
        thanhToanAdapter = new A_ThanhToan(this, R.layout.dong_giohang, gioHangList);
        lvGioHang.setAdapter(thanhToanAdapter);
//        ViewGroup.LayoutParams params = lvGioHang.getLayoutParams();
//        params.height = 70*(gioHangList.size());
//        lvGioHang.setLayoutParams(params);
//        lvGioHang.requestLayout();
    }
}