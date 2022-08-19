package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._NhanVien;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_ThemNV;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_NhanVien extends AppCompatActivity implements Dialog_ThemNV.ExampleDialogListener {

    private ListView lvNhanVien;
    private TextView txtTenNV, txtNamSinh, txtQueQuan, txtNgayVaoLam;
    private Button btnSuaNV, btnNghiViecNV, btThemNV, btnQuayLaiNV;
    private ImageButton imgBtnBack, imgBtnThemNV;
    private TextView lbDHDaXL, lbTongGT;
    private Spinner spTinhTrang;

    private LinearLayout ln1, ln2;
    private ArrayList<_NhanVien> nhanVienList;
    private Data_NhanVien nhanVienData;
    private A_NhanVien nhanVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_nhanvien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgBtnBack();
        setSpTinhTrang();
        setOnClickLVNV();
        setImgBtnThemNV();
    }

    private void setImgBtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setLvNhanVien() {
        nhanVienList = new ArrayList<>();
        nhanVienData = new Data_NhanVien(this);
        nhanVienList = nhanVienData.getDLNV(iPosition);
        nhanVienAdapter = new A_NhanVien(this, R.layout.dong_nhanvien, nhanVienList);
        lvNhanVien.setAdapter(nhanVienAdapter);
    }

    private void setBoolean(boolean yeucau) {
        btnSuaNV.setVisibility(View.VISIBLE); // Disable Button
        if (!yeucau) {
            btnSuaNV.setVisibility(View.GONE); // Disable Button
        }
        txtTenNV.setEnabled(yeucau);
        txtNamSinh.setEnabled(yeucau);
        txtQueQuan.setEnabled(yeucau);
    }

    private void reset() {
        txtTenNV.setText("");
        txtQueQuan.setText("");
        txtNgayVaoLam.setText("");
        txtNamSinh.setText("");
        ln1.setVisibility(View.GONE);
    }

    private void setImgBtnThemNV(){
        imgBtnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_ThemNV dialog_themNV = new Dialog_ThemNV();
                dialog_themNV.show(getSupportFragmentManager(),"ThemNV");
            }
        });
    }

    private void setOnClickLVNV() {
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtTenNV.setText(nhanVienList.get(position).getTenNV());
                txtNamSinh.setText(nhanVienList.get(position).getNamSinh());
                txtQueQuan.setText(nhanVienList.get(position).getQueQuanNV());
                txtNgayVaoLam.setText(nhanVienList.get(position).getNgayVaoLam());
                ln1.setVisibility(View.VISIBLE);
                if (nhanVienList.get(position).isLamViec()) {
                    btnNghiViecNV.setVisibility(View.VISIBLE);
                    btnQuayLaiNV.setVisibility(View.GONE);
                    setBoolean(true);
                }
                if (!nhanVienList.get(position).isLamViec()) {
                    btnNghiViecNV.setVisibility(View.GONE);
                    btnQuayLaiNV.setVisibility(View.VISIBLE);
                    btnSuaNV.setVisibility(View.GONE); // Disable Button
                    setBoolean(false);
                }
                setBtnSuaNV(position);
                setBtnQuayLaiNV(position);
                setBtnNghiViecNV(position);
                setLn2(position);
            }
        });
    }

    private void setLn2(int position){
        ln2.setVisibility(View.VISIBLE);
        Data_DonHang donHangData = new Data_DonHang(this);
        int maNV = Integer.parseInt(nhanVienList.get(position).getMaNV());
        lbDHDaXL.setText(""+donHangData.slDHNVXL(maNV));
        lbTongGT.setText("(VNĐ) "+themPhay(donHangData.tongTienDHNVXL(maNV)+""));
    }
    private String themPhay(String a) {
        int b = Integer.parseInt(a);
        String kq = String.format("%,d%n", b);
        return kq;
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

    private void setBtnNghiViecNV(int position) {
        btnNghiViecNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_NhanVien.this);
                builder.setMessage("Xác nhận cho nghỉ việc ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!nhanVienData.choNghiViec(nhanVienList.get(position).getMaNV(), layNgayHT())) {
                                    Toast.makeText(Activity_NhanVien.this, "Thất bại", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                reset();
                                setLvNhanVien();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void setBtnQuayLaiNV(int position) {
        btnQuayLaiNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_NhanVien.this);
                builder.setMessage("Xác nhận cho quay lại làm việc ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!nhanVienData.quayLaiLam(nhanVienList.get(position).getMaNV())) {
                                    Toast.makeText(Activity_NhanVien.this, "Thất bại", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                reset();
                                setLvNhanVien();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void setBtnSuaNV(int position) {
        btnSuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV = nhanVienList.get(position).getMaNV();
                String tenNV = txtTenNV.getText().toString();
                String namSinh = txtNamSinh.getText().toString();
                String ngayVaoLam = txtNgayVaoLam.getText().toString();
                String queQuan = txtQueQuan.getText().toString();
                _NhanVien nhanVien = new _NhanVien();
                nhanVien.setMaNV(maNV);
                nhanVien.setTenNV(tenNV);
                nhanVien.setNamSinh(namSinh);
                nhanVien.setNgayVaoLam(ngayVaoLam);
                nhanVien.setQueQuanNV(queQuan);
                if (!nhanVienData.suaNV(nhanVien)) {
                    Toast.makeText(Activity_NhanVien.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Activity_NhanVien.this, "Đã thay đổi !", Toast.LENGTH_SHORT).show();
                setLvNhanVien();
                reset();
            }
        });
    }

    private int iPosition = 0;

    private void setSpTinhTrang() {
        String[] arr = {"Tất cả", "Còn đi làm", "Đã nghỉ việc"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTinhTrang.setAdapter(adapter);
        spTinhTrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iPosition = position;
                setLvNhanVien();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setLvNhanVien();
            }
        });
    }

    private void anhXa() {
        lvNhanVien = findViewById(R.id.lvNhanVien);
        txtTenNV = findViewById(R.id.txtTenNV);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        txtQueQuan = findViewById(R.id.txtQueQuan);
        txtNgayVaoLam = findViewById(R.id.txtNgayVaoLam);
        btnSuaNV = findViewById(R.id.btnSuaNV);
        btnNghiViecNV = findViewById(R.id.btnNghiViecNV);
        btnQuayLaiNV = findViewById(R.id.btnQuayLaiNV);
        lbDHDaXL = findViewById(R.id.lbDHDaXL);
        lbTongGT = findViewById(R.id.lbTongGT);
        spTinhTrang = findViewById(R.id.spTinhTrang);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnThemNV = findViewById(R.id.ImgBtnThemNV);
        ln1 = findViewById(R.id.ln1);
        ln2 = findViewById(R.id.ln2);

        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        txtNgayVaoLam.setEnabled(false); // Hủy bỏ type
    }

    @Override
    public void setLVNV() {
        setLvNhanVien();
    }
}