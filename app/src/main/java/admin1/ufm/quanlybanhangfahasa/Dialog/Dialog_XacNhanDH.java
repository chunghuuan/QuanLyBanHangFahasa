package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Gmail;
import admin1.ufm.quanlybanhangfahasa.DinhDang_Fahasa.DinhDangNgay;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_XacNhanDH extends AppCompatDialogFragment {
    private TextView lbNVPhuTrach;
    EditText txtNgayXL, txtPhiGiao, txtNoiDung;
    private View view;
    private Data_DonHang donHangData;
    private Data_NhanVien nhanVienData;
    private  int maNV = -1;
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_xacnhandh, null);
        anhXa();
        nhanGT();
        dinhDangNgay();
        nhanVienData = new Data_NhanVien(getActivity());
        String tenNV = nhanVienData.layTenNV(String.valueOf(this.maTK));
        lbNVPhuTrach.setText(tenNV);
        this.maNV = nhanVienData.layMaNV(maTK);

        builder.setView(view).setTitle("Xác nhận đơn hàng "+maDH).setNegativeButton("Quay về", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (xacNhan()){
                    Toast.makeText(getActivity()," Xác nhận đơn hàng thành công !",Toast.LENGTH_SHORT).show();
                    listener.dongActivity();
                    guiGmail();
                }
            }
        });
        return builder.create();
    }

    private void guiGmail() {

    }

    //---------------------------------- Implement
    private ExampleDialogListener listener;
    public interface ExampleDialogListener{
        void dongActivity(); // QUAN TRONG
    }



    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener)context;
        }catch (ClassCastException e){
//            throw  new ClassCastException(context.toString()+"");
        }
    }
    //---------------------------------- Implement

    private void anhXa() {
        txtNgayXL = view.findViewById(R.id.txtNgayXL);
        txtPhiGiao = view.findViewById(R.id.txtPhiGiao);
        txtNoiDung = view.findViewById(R.id.txtNoiDung);
        lbNVPhuTrach = view.findViewById(R.id.lbNVPhuTrach);

    }

    int maDH = -1,maTK = -1;
    private void nhanGT(){
        Bundle bundle = getArguments();
        this.maDH = bundle.getInt("maDH",-1);
        this.maTK = bundle.getInt("maTK",-1);
    }

    private void dinhDangNgay(){
        txtNgayXL.setText(layNgayHT());
        TextWatcher textWatcherNgay = new DinhDangNgay(txtNgayXL);
        txtNgayXL.addTextChangedListener(textWatcherNgay);
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

    private boolean xacNhan(){
        String ngayXL = txtNgayXL.getText().toString();
        String phiGiao = txtPhiGiao.getText().toString();
        String noiDung = txtNoiDung.getText().toString();
        if (ngayXL.equals("")||phiGiao.equals("")||noiDung.equals("")){
            Toast.makeText(getActivity()," Không được để ô trống !",Toast.LENGTH_SHORT).show();
            return false;
        }
        int iPhiGiao = Integer.parseInt(phiGiao);
        if (iPhiGiao<0||iPhiGiao>1000000){
            Toast.makeText(getActivity()," Nhập sai phí giao hàng !",Toast.LENGTH_SHORT).show();
            return false;
        }
        donHangData = new Data_DonHang(getActivity());
        _DonHang donHang = new _DonHang();
        donHang.setMaDH(maDH);
        donHang.setNgayXuLy(ngayXL);
        donHang.setPhiGiaoHang(iPhiGiao);
        donHang.setGhiChu(noiDung);
        if (donHangData.xacNhanDH(donHang,maNV)){
            Gmail gmail = new Gmail(getActivity());
            if (!gmail.sendXacNhanDH(maDH)){
                Toast.makeText(getActivity(),"Thông báo đến khách hàng thất bại !",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else
            return false;
    }
}
