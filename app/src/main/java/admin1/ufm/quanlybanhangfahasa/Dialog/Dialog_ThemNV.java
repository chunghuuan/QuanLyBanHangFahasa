package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._NhanVien;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_ThemNV extends AppCompatDialogFragment {
    private View view;
    private EditText txtSDTNV, txtEmailNV, txtMatKhauNV, txtTenNV, txtNamSinh, txtNgayVaoLam;
    private Spinner spGioiTinh, spQueQuan;
    private Data_TaiKhoan taiKhoanData;
    private Data_NhanVien nhanVienData;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_themnv, null);
        anhXa();
        setSpQueQuan();
        setSpGioiTinh();
        builder.setView(view).setTitle("Thêm nhân viên").setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!kiemTraRong()) {
                    return;
                }
                String gioiTinh = spGioiTinh.getSelectedItem().toString();
                String queQuan = spQueQuan.getSelectedItem().toString();
                String ngayVaoLam = txtNgayVaoLam.getText().toString();
                taiKhoanData = new Data_TaiKhoan(getActivity());
                _TaiKhoan taiKhoan = new _TaiKhoan();
                sDTNV = txtSDTNV.getText().toString();
                emailNV = txtEmailNV.getText().toString();
                matKhauNV = txtMatKhauNV.getText().toString();
                tenNV = txtTenNV.getText().toString();
                namSinh = txtNamSinh.getText().toString();

                taiKhoan.setMatkhau(matKhauNV);
                taiKhoan.setNgayDangKy(ngayVaoLam);
                taiKhoan.setSdt(sDTNV);
                taiKhoan.setEmail(emailNV);
                taiKhoan.setMaCV(2);
                if (taiKhoanData.themTK(taiKhoan)) {
                    nhanVienData = new Data_NhanVien(getActivity());
                    _NhanVien nhanVien = new _NhanVien();
                    nhanVien.setTenNV(tenNV);
                    nhanVien.setGioiTinhNV(gioiTinh);
                    nhanVien.setNamSinh(namSinh);
                    nhanVien.setQueQuanNV(queQuan);
                    nhanVien.setNgayVaoLam(ngayVaoLam);
                    if (nhanVienData.themNV(nhanVien)) {
                        Toast.makeText(getActivity(),"Thêm nhân viên "+tenNV+" thành công !",Toast.LENGTH_SHORT).show();
                        listener.setLVNV();
                    }
                }
            }
        });
        return builder.create();
    }

    //---------------------------------- Implement
    private ExampleDialogListener listener;

    public interface ExampleDialogListener{
        void setLVNV(); // QUAN TRONG
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener)context;
        }catch (ClassCastException e){
        }
    }
    //---------------------------------- Implement

    private String sDTNV, emailNV, matKhauNV, tenNV, namSinh;

    private boolean kiemTraRong() {
        sDTNV = txtSDTNV.getText().toString();
        emailNV = txtEmailNV.getText().toString();
        matKhauNV = txtMatKhauNV.getText().toString();
        tenNV = txtTenNV.getText().toString();
        namSinh = txtNamSinh.getText().toString();
        if (sDTNV.equals("") || emailNV.equals("") || matKhauNV.equals("") || tenNV.equals("") || namSinh.equals("")) {
            Toast.makeText(getActivity(), "Không được để trống nội dung !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setSpGioiTinh() {
        String[] gioiTinh = {"Nam", "Nữ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, gioiTinh);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGioiTinh.setAdapter(adapter);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tinhThanh);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQueQuan.setAdapter(adapter);
    }

    private void anhXa() {
        txtSDTNV = view.findViewById(R.id.txtSDTNV);
        txtEmailNV = view.findViewById(R.id.txtEmailNV);
        txtMatKhauNV = view.findViewById(R.id.txtMatKhauNV);
        txtTenNV = view.findViewById(R.id.txtTenNV);
        txtNamSinh = view.findViewById(R.id.txtNamSinh);
        txtNgayVaoLam = view.findViewById(R.id.txtNgayVaoLam);
        spGioiTinh = view.findViewById(R.id.spGioiTinh);
        spQueQuan = view.findViewById(R.id.spQueQuan);

        txtNgayVaoLam.setText(layNgayHT());

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
}
