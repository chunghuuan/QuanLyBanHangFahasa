package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_SoHuu;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_SoHuuVoucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._SoHuu;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_ThemVoucherKH;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_SoHuuVoucher extends AppCompatActivity implements Dialog_ThemVoucherKH.ExampleDialogListener{
    private TextView lb_MaVoucher, lb_TyLeGiam, lb_soLuong, lbChuDe;
    private ListView lvSohuuVoucher;
    private Data_SoHuu soHuuData;
    private A_SoHuuVoucher soHuuVoucherAdapter;
    private ArrayList<_SoHuu> soHuuList;
    private ImageButton imgBtnBack, imgBtnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_sohuuvoucher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        nhanGT();
        setLvSohuuVoucher();
        setImgBtnBack();
        setImgBtnThem();
    }

    private void setImgBtnThem(){
        imgBtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemVoucherKH dialog_themVoucherKH = new Dialog_ThemVoucherKH();
                Bundle bundle = new Bundle();
                bundle.putInt("maTK", maTK);
                dialog_themVoucherKH.setArguments(bundle);
                dialog_themVoucherKH.show(getSupportFragmentManager(),"ChangePassword");
            }
        });
    }

    private void setLvSohuuVoucher() {
        soHuuData = new Data_SoHuu(this);
        soHuuList = soHuuData.getDLSoHuu(this.maTK);
        soHuuVoucherAdapter = new A_SoHuuVoucher(Activity_SoHuuVoucher.this, R.layout.dong_vouchersohuu, soHuuList);
        if (soHuuVoucherAdapter.getCount() == 0) {
            Toast.makeText(this, "Bạn không có Voucher nào !", Toast.LENGTH_SHORT).show();
            return;
        }
        lvSohuuVoucher.setAdapter(soHuuVoucherAdapter);
    }

    private void anhXa() {
        lb_MaVoucher = findViewById(R.id.lb_MaVoucher);
        lb_TyLeGiam = findViewById(R.id.lb_TyLeGiam);
        lb_soLuong = findViewById(R.id.lb_soLuong);
        lvSohuuVoucher = findViewById(R.id.lvSohuuVoucher);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnThem = findViewById(R.id.imgBtnThem);
        lbChuDe = findViewById(R.id.lbChuDe);
    }

    private void setImgBtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int maTK;
    private int key = -1;
    private void nhanGT() {
        Intent i = getIntent();
        try {
            this.maTK = i.getIntExtra("maTK",-1);// Nhận giá trị từ Activity Đăng nhập
            key = i.getIntExtra("key",-1);
        } catch (NullPointerException e) {
        }
        if (key == 1) {
            imgBtnThem.setVisibility(View.VISIBLE);
            lbChuDe.setText("Voucher của khách hàng");
        }
    }

    @Override
    public void CapNhatListView() {
        setLvSohuuVoucher();
    }
}