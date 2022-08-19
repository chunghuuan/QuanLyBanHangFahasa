package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_CTKM;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NXB;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Sach;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TacGia;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_TuyChinhSach extends AppCompatActivity {
    private TextView txtTenSach,txtURLHinhAnh,txtNamXB,txtGiaBanGoc,txtSLTon,txtNoiDungSach;
    private Spinner spTheLoai,spNXB,spTacGia,spCTKM;
    private ImageButton imgBtnBack,imgBtnXoa,imgBtnXacNhan;

    private Data_TheLoai theLoaiData;
    private Data_NXB nxbData;
    private Data_TacGia tacGiaData;
    private Data_CTKM ctkmData;
    private Data_Sach sachData;
    private ArrayList<_Sach> sachList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_tuychinhsach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgbtnBack();
        nhanGT();
        setSpinner();
        setNoiDung(getMaSach());
        setImgBtnXoa();
        setImgBtnXacNhan();
    }

    private int maSach;

    public int getMaSach() {
        return maSach;
    }

    private void nhanGT(){
        Intent i = getIntent();
        String maSach;
        maSach = i.getStringExtra("maSach");
        this.maSach = Integer.parseInt(""+maSach);
    }

    private void setImgBtnXoa(){
        imgBtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongBaoXoa("Xóa sách","Bạn thực sự muốn xóa Sách "+txtTenSach.getText().toString(),"Có","Không");
            }
        });
    }

    private void setImgBtnXacNhan(){
        imgBtnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = txtTenSach.getText().toString();
                String urlHinhAnh = txtURLHinhAnh.getText().toString();
                String namXB = txtNamXB.getText().toString();
                int giaBanGoc = Integer.parseInt(""+txtGiaBanGoc.getText().toString());
                int slTon = Integer.parseInt(""+txtSLTon.getText().toString());
                String noiDungSach = txtNoiDungSach.getText().toString();
                int maSach = getMaSach();
                _Sach sach = new _Sach(maSach,tenSach,noiDungSach,urlHinhAnh,namXB,giaBanGoc,slTon,posTheLoai,posNXB,posTacGia,posCTKM);
                thongBaoSua("Sửa sách","Bạn thực sự muốn sửa Sách "+txtTenSach.getText().toString(),"Có","Không",sach);
            }
        });
    }

    private int viTri=-1;
    private void setNoiDung(int maSach) {
        sachData = new Data_Sach(Activity_TuyChinhSach.this);
        sachList = new ArrayList<>(sachData.getDLSach());
        for (int i = 0;i<sachList.size();i++){
            if (sachList.get(i).getMaSach() == maSach){
                viTri = i;
                break;
            }
        }

        txtTenSach.setText(sachList.get(viTri).getTenSach());
        txtURLHinhAnh.setText(sachList.get(viTri).getuRLHinhAnh());
        txtNamXB.setText(sachList.get(viTri).getNamXB());
        txtGiaBanGoc.setText(""+sachList.get(viTri).getGiaBanGoc());
        txtSLTon.setText(""+sachList.get(viTri).getsLTon());
        txtNoiDungSach.setText(sachList.get(viTri).getMoTaSach());
        posTheLoai = sachList.get(viTri).getMaTheLoai();
        posNXB = sachList.get(viTri).getMaNXB();
        posTacGia = sachList.get(viTri).getMaTacGia();
        posCTKM = sachList.get(viTri).getMaCTKM();
        posTheLoai--;
        posNXB--;
        posTacGia--;
        posCTKM--;
        spTheLoai.setSelection(posTheLoai);
        spNXB.setSelection(posNXB);
        spTacGia.setSelection(posTacGia);
        spCTKM.setSelection(posCTKM);
    }

    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_TuyChinhSach.this, Activity_Sach.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnXacNhan= findViewById(R.id.imgBtnXacNhan);
        imgBtnXoa = findViewById(R.id.imgBtnXoa);
        txtTenSach =findViewById(R.id.txtTenSach);
        txtNamXB =findViewById(R.id.txtNamXB);
        txtGiaBanGoc =findViewById(R.id.txtGiaBanGoc);
        txtSLTon =findViewById(R.id.txtSLTon);
        txtNoiDungSach =findViewById(R.id.txtNoiDungSach);
        txtURLHinhAnh=findViewById(R.id.txtURLHinhAnh);
        spTheLoai =findViewById(R.id.spTheLoai);
        spNXB =findViewById(R.id.spNXB);
        spTacGia =findViewById(R.id.spTacGia);
        spCTKM =findViewById(R.id.spCTKM);
    }

    private void setSpinner(){
        setSpTheLoai();
        setSpNXB();
        setSpTacGia();
        setSpCTKM();
    }

    private int posTheLoai = 1,posNXB = 1,posTacGia = 1,posCTKM = 1;



    public void setSpTheLoai() {
        theLoaiData = new Data_TheLoai(Activity_TuyChinhSach.this);
        int dodai = theLoaiData.layDLTheLoai().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTenTheLoai = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlTenTheLoai.add(theLoaiData.layDLTheLoai().get(i).getTenTheLoai());
        }
        arr = dlTenTheLoai.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Activity_TuyChinhSach.this, android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTheLoai.setAdapter(adapter);
        spTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosTheLoai(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpNXB() {
        nxbData = new Data_NXB(Activity_TuyChinhSach.this);
        int dodai = nxbData.layDLNhaXuatBan().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlNhaXuatBan = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlNhaXuatBan.add(nxbData.layDLNhaXuatBan().get(i).getTenNXB());
        }
        arr = dlNhaXuatBan.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Activity_TuyChinhSach.this, android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNXB.setAdapter(adapter);
        spNXB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosNXB(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpTacGia() {
        tacGiaData = new Data_TacGia(Activity_TuyChinhSach.this);
        int dodai = tacGiaData.layDLTacGia().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTacGia = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlTacGia.add(tacGiaData.layDLTacGia().get(i).getTenTacGia());
        }
        arr = dlTacGia.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Activity_TuyChinhSach.this, android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTacGia.setAdapter(adapter);
        spTacGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosTacGia(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpCTKM() {
        ctkmData = new Data_CTKM(Activity_TuyChinhSach.this);
        int dodai = ctkmData.layDlCTKM().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlCTKM = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlCTKM.add(ctkmData.layDlCTKM().get(i).getTenCTKM());
        }
        arr = dlCTKM.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Activity_TuyChinhSach.this, android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCTKM.setAdapter(adapter);
        spCTKM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosCTKM(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public int getPosTheLoai() {
        return posTheLoai;
    }

    public void setPosTheLoai(int posTheLoai) {
        this.posTheLoai = posTheLoai;
    }

    public int getPosNXB() {
        return posNXB;
    }

    public void setPosNXB(int posNXB) {
        this.posNXB = posNXB;
    }

    public int getPosTacGia() {
        return posTacGia;
    }

    public void setPosTacGia(int posTacGia) {
        this.posTacGia = posTacGia;
    }



    public int getPosCTKM() {
        return posCTKM;
    }

    public void setPosCTKM(int posCTKM) {
        this.posCTKM = posCTKM;
    }
    private void thongBaoXoa(String tilte,String Message,String ButtonYes,String ButtonNo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_TuyChinhSach.this);
        alertDialog.setTitle(tilte);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage(Message);
        alertDialog.setPositiveButton(ButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachData = new Data_Sach(Activity_TuyChinhSach.this);
                if (sachData.xoaSach(getMaSach())){
                    Toast.makeText(Activity_TuyChinhSach.this,"Đã xóa !",Toast.LENGTH_SHORT).show();
                    chuyenActivity();
                }
                else
                    Toast.makeText(Activity_TuyChinhSach.this,"Xóa thất bại !",Toast.LENGTH_SHORT).show();
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

    private void thongBaoSua(String tilte, String Message, String ButtonYes, String ButtonNo, _Sach sach){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_TuyChinhSach.this);
        alertDialog.setTitle(tilte);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage(Message);
        alertDialog.setPositiveButton(ButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachData = new Data_Sach(Activity_TuyChinhSach.this);
                if (sachData.suaSach(sach)){
                    Toast.makeText(Activity_TuyChinhSach.this,"Đã sửa !",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Activity_TuyChinhSach.this,"Sửa thất bại !",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton(ButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
    private void chuyenActivity(){
        Intent i = new Intent(Activity_TuyChinhSach.this, Activity_Sach.class);
        startActivity(i);
        finish();
    }

}
