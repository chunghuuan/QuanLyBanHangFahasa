package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Voucher;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_Voucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_ThemVoucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_Voucher extends AppCompatActivity implements Dialog_ThemVoucher.ExampleDialogListener {
    ImageButton imgBtnBack,btnThemVoucher;
    ListView lvVoucher;
    ArrayList<_Voucher> voucherList;
    Data_Voucher voucherData;
    A_Voucher voucherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_voucher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgbtnBack();
        setBtnThemVoucher();
        setLvVoucher();
        clickLVVoucher();
    }

    private void setLvVoucher(){
        voucherData = new Data_Voucher(Activity_Voucher.this);
        voucherList = voucherData.getDLVoucher();
        voucherAdapter = new A_Voucher(Activity_Voucher.this,R.layout.dong_voucher,voucherList);
        lvVoucher.setAdapter(voucherAdapter);
    }

    private void clickLVVoucher(){
        lvVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Activity_Voucher.this, Activity_TuyChinhVoucher.class);
                i.putExtra("maVoucher",voucherList.get(position).getMaVoucher());
                i.putExtra("noiDung",voucherList.get(position).getNoiDung());
                i.putExtra("tyLeGiam",""+voucherList.get(position).getTyLeGiam());
                finish();
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lvVoucher = findViewById(R.id.lvVoucher);
        btnThemVoucher = findViewById(R.id.btnThemVoucher);
    }
    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setBtnThemVoucher(){
        btnThemVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemVoucher dialog_themVoucher = new Dialog_ThemVoucher();
                dialog_themVoucher.show(getSupportFragmentManager(),"ChangePassword");
            }
        });
    }

    @Override
    public void CapNhatListView() {
        setLvVoucher();// Lệnh cập nhật lại bảng từ Dialog - THÀNH CÔNG
    }
}
