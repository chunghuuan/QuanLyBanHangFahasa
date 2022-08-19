package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._KhachHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_XacNhanDH;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_CTDH extends AppCompatActivity implements Dialog_XacNhanDH.ExampleDialogListener{


    private ListView lvCTDH;
    private TextView txtMaDH;
    private Data_ChiTietDH chiTietDHData;
    private Data_DonHang donHangData;
    private Data_KhachHang khachHangData;
    private Data_TaiKhoan taiKhoanData;
    private ArrayList<_ChiTietDH> chiTietDHList;
    private A_ChiTietDH chiTietDHAdapter;
    private ImageButton imgBtnBack,btnXacNhanDH;

    EditText txtNgayXL,txtPhiGiao,txtNoiDung;
    TextView lbTenKH,lbSDT,lbGmail,lbDCGiaoHang,lbGhiChuKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_ctdh);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setLvCTDH();
        setTTKH();
        setImgBtnBack();
        setBtnXacNhanDH();
    }
    private void anhXa() {
        lvCTDH =findViewById(R.id.lvCTDH);
        txtMaDH=findViewById(R.id.txtMaDH);
        imgBtnBack=findViewById(R.id.imgBtnBack);
        btnXacNhanDH=findViewById(R.id.btnXacNhanDH);

        txtNgayXL =findViewById(R.id.txtNgayXL);
        txtPhiGiao =findViewById(R.id.txtPhiGiao);
        txtNoiDung =findViewById(R.id.txtNoiDung);
        lbTenKH =findViewById(R.id.lbTenKH);
        lbSDT =findViewById(R.id.lbSDT);
        lbGmail =findViewById(R.id.lbGmail);
        lbDCGiaoHang =findViewById(R.id.lbDCGiaoHang);
        lbGhiChuKH =findViewById(R.id.lbGhiChuKH);
    }
    private void setImgBtnBack(){
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setBtnXacNhanDH(){
        btnXacNhanDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_XacNhanDH dialogFragment = new Dialog_XacNhanDH();
                Bundle bundle = new Bundle();
                bundle.putInt("maDH",maDH);
                bundle.putInt("maTK",maTK);
                dialogFragment.setArguments(bundle);
                dialogFragment.show((Activity_CTDH.this).getSupportFragmentManager(),"Image Dialog");
            }
        });
    }

    private int maDH;
    private void setLvCTDH(){
        nhanGT();
        chiTietDHData = new Data_ChiTietDH(this);
        System.out.println(maDH); // Mã đơn hàng
        chiTietDHList = chiTietDHData.layDLChiTietDH(maDH);
        System.out.println("size:"+chiTietDHList.size());
        chiTietDHAdapter = new A_ChiTietDH(this,R.layout.dong_ctdh,chiTietDHList);
        lvCTDH.setAdapter(chiTietDHAdapter);
        txtMaDH.setText("Đơn hàng: "+maDH);
    }

    private int maTK;
    private void nhanGT(){
        Intent i = getIntent();
        this.maDH = i.getIntExtra("maDH",-1) ;
        this.maTK = i.getIntExtra("maTK",-1);
    }

    private void setTTKH(){
        donHangData = new Data_DonHang(this);
        khachHangData = new Data_KhachHang(this);
        taiKhoanData = new Data_TaiKhoan(this);
        System.out.println("maDH:"+maDH);
        _DonHang donHang2 = donHangData.dLDH(maDH);

        if (!donHang2.equals("") && (donHang2!=null)){
            lbGhiChuKH.setText("Không có");
        }
        lbGhiChuKH.setText(donHang2.getGhiChu());
        int maKH = donHang2.getMaKH();
        _KhachHang khachHang2 = khachHangData.dLKH(maKH);
        String diaChiGiaoHang = donHang2.getDiaChiGiaoHang();
        lbTenKH.setText(khachHang2.getTenKH());
        lbDCGiaoHang.setText(diaChiGiaoHang);
        int maTK = Integer.parseInt(khachHang2.getMaTK());
        _TaiKhoan taiKhoan = taiKhoanData.getDLTK(maTK);
        lbGmail.setText(taiKhoan.getEmail());
        lbSDT.setText(taiKhoan.getSdt());


    }
    @Override
    public void dongActivity() {
        finish();
    }
}
