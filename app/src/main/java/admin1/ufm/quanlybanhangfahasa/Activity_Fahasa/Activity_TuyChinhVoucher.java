package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Voucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_TuyChinhVoucher extends AppCompatActivity {
    ImageButton imgBtnBack,imgBtnXoa,imgBtnXacNhan;
    EditText txtMaVoucher,txtNoiDung,txtTyleGiam;
    Data_Voucher voucherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_tuychinhvoucher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgbtnBack();
        setNoiDung();
        setImgBtnXoa();
        setImgBtnXacNhan();
    }

    private String maVoucher,noiDung,tyLeGiam;

    private void nhanGT(){
        ArrayList<String> giatri = new ArrayList<>();
        Intent i = getIntent();
        this.maVoucher = i.getStringExtra("maVoucher") ;
        this.noiDung = i.getStringExtra("noiDung") ;
        this.tyLeGiam = i.getStringExtra("tyLeGiam");
    }

    private void setNoiDung(){
        nhanGT();
        txtMaVoucher.setText(maVoucher);
        txtNoiDung.setText(noiDung);
        txtTyleGiam.setText(tyLeGiam);
    }

    private void setImgBtnXoa(){
        imgBtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongBaoXoa("Xóa Voucher "+maVoucher,"Bạn có thực sự muốn xóa ?","Có","Không");
            }
        });
    }

    public ImageButton getImgBtnBack() {
        return imgBtnBack;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getTyLeGiam() {
        return tyLeGiam;
    }

    private void setImgBtnXacNhan(){
        imgBtnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maVoucher = txtMaVoucher.getText().toString().trim();
                String noiDung = txtNoiDung.getText().toString().trim();
                Float tyLeGiam = Float.parseFloat(txtTyleGiam.getText().toString().trim());

                _Voucher voucher = new _Voucher(maVoucher,noiDung,tyLeGiam);

                if (maVoucher.equals(getMaVoucher())&&noiDung.equals(getNoiDung())&&tyLeGiam.equals(getTyLeGiam())){
                    chuyenActivity();
                }
                thongBaoSua("Sửa Voucher "+maVoucher,"Bạn có muốn sửa không ?","Có","Không",voucher);
            }
        });

    }

    private void thongBaoXoa(String tilte,String Message,String ButtonYes,String ButtonNo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_TuyChinhVoucher.this);
        alertDialog.setTitle(tilte);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage(Message);
        alertDialog.setPositiveButton(ButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                voucherData = new Data_Voucher(Activity_TuyChinhVoucher.this);
                if (voucherData.xoaVoucher(maVoucher)){
                    Toast.makeText(Activity_TuyChinhVoucher.this,"Đã xóa !",Toast.LENGTH_SHORT).show();
                    chuyenActivity();
                }
                else
                    Toast.makeText(Activity_TuyChinhVoucher.this,"Xóa thất bại !",Toast.LENGTH_SHORT).show();
                chuyenActivity();
            }
        });
        alertDialog.setNegativeButton(ButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void thongBaoSua(String tilte,String Message,String ButtonYes,String ButtonNo,_Voucher voucher){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_TuyChinhVoucher.this);
        alertDialog.setTitle(tilte);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage(Message);
        alertDialog.setPositiveButton(ButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                voucherData = new Data_Voucher(Activity_TuyChinhVoucher.this);
                if (voucherData.suaVoucher(voucher)){
                    Toast.makeText(Activity_TuyChinhVoucher.this,"Đã sửa !",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Activity_TuyChinhVoucher.this,"Sửa thất bại !",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton(ButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void anhXa() {
        txtMaVoucher = findViewById(R.id.txtMaVoucher);
        txtNoiDung = findViewById(R.id.txtNoiDung);
        txtTyleGiam = findViewById(R.id.txtTyleGiam);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnXoa = findViewById(R.id.imgBtnXoa);
        imgBtnXacNhan=findViewById(R.id.imgBtnXacNhan);
    }
    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenActivity();
            }
        });
    }
    private void chuyenActivity(){
        Intent i = new Intent(Activity_TuyChinhVoucher.this, Activity_Voucher.class);
        startActivity(i);
        finish();
    }
}
