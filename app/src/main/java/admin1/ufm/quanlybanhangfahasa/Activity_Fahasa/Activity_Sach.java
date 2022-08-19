package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_Sach;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Sach;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_ThemSach;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_Sach extends AppCompatActivity implements Dialog_ThemSach.ExampleDialogListener{

    private A_Sach sachAdapter;
    private Data_Sach sachData;
    private ArrayList<_Sach> sachList;
    ListView lvSach;
    ImageView imgBtnBack;
    Button imgBtnThemSach;
    Spinner spSach;
    SearchView searchView;

    Data_TheLoai theLoaiData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgbtnBack();
        setSpSach();
        setSearchView();
        setImgBtnThemSach();
        clickLVSach();
    }

    public void setSpSach() {
        theLoaiData = new Data_TheLoai(Activity_Sach.this);
        int dodai = theLoaiData.layDLTheLoai().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTenTheLoai = new ArrayList<>();
        dlTenTheLoai.add("Tất cả");
        for (int i = 0;i<dodai;i++){
            dlTenTheLoai.add(theLoaiData.layDLTheLoai().get(i).getTenTheLoai());
        }
        arr = dlTenTheLoai.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Activity_Sach.this, android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSach.setAdapter(adapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLvSach(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        lvSach = findViewById(R.id.lvSach);
        spSach = findViewById(R.id.spSach);
        searchView = findViewById(R.id.fhsSearchView);
        imgBtnThemSach= findViewById(R.id.imgBtnThemSach);
    }

    private void setImgbtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setImgBtnThemSach(){
        imgBtnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemSach dialog_themSach = new Dialog_ThemSach();
                dialog_themSach.show(getSupportFragmentManager(),"AddBook");
            }
        });
    }

    private ArrayList<_Sach> sachListDuPhong;
    private void setLvSach(int viTri) {
        String luaChonSP = spSach.getSelectedItem().toString();
        if (luaChonSP.equals("Tất cả")){
            sachData = new Data_Sach(Activity_Sach.this);
            sachList = sachData.getDLSach();
            sachListDuPhong = new ArrayList<>(sachList); // Sao lưu dự phòng
            sachAdapter = new A_Sach(Activity_Sach.this, R.layout.dong_sach, sachList, Activity_Sach.this);
            lvSach.setAdapter(sachAdapter);
            return;
        }
        sachList.clear(); // Xóa dữ liệu List
        sachData = new Data_Sach(Activity_Sach.this);
        sachList = sachData.getDLSachTheoTL(viTri);
        sachAdapter = new A_Sach(Activity_Sach.this, R.layout.dong_sach, sachList, Activity_Sach.this);
        lvSach.setAdapter(sachAdapter);
        Toast.makeText(Activity_Sach.this,"Thay đổi",Toast.LENGTH_SHORT).show();
//        clickLVSach();
    }

    private void clickLVSach() {
        lvSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maSach = -1;
                for (int i = 0;i<sachListDuPhong.size();i++){
                        if (sachListDuPhong.get(i).getTenSach().equals(sachList.get(position).getTenSach())){
                            maSach = sachList.get(position).getMaSach();
                    }
                }
                Intent i = new Intent(Activity_Sach.this, Activity_TuyChinhSach.class);
                i.putExtra("maSach",""+maSach); // Chắc chắn có giá trị
                startActivity(i);
                finish();
            }
        });
    }

    private void setSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sachAdapter.filter(newText);
                return false;
            }
        });
    }

    @Override
    public void CapNhatListView() {
        setSpSach();
    }
}



