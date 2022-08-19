package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_GioHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_GioHang extends AppCompatDialogFragment {
    private TextView lbTenKH, lbNgayMua, lbTamTinh;
    private ListView lvGioHang;
    private View view;
    private A_GioHang gioHangAdapter;

    private Data_KhachHang khachHangData;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_giohang, null);
        anhXa();
        nhanGT();
        setThongTin();
        setLvGioHang();

        builder.setView(view).setTitle("Thông tin giỏ hàng ").setNegativeButton("Quay về", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (sachGioHangList.size()<1){
                    Toast.makeText(getActivity(),"Không có sản phẩm trong giỏ hàng !",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),"Vui lòng chọn lại !",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.openThanhToan(maTK,sachGioHangList);
            }
        });
        return builder.create();
    }

    //---------------------------------- Implement
    private ExampleDialogListener listener;

    public interface ExampleDialogListener{
        void openThanhToan(int maTK, ArrayList<_Sach> sachGioHangList); // QUAN TRONG
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


    private void setThongTin() {
        khachHangData = new Data_KhachHang(getActivity());
        lbNgayMua.setText(layNgayHT());
        String tenKH = khachHangData.layTenKH1(this.maTK);
        lbTenKH.setText("" + tenKH);
        lbTamTinh.setText("");
        int tongTien = 0;
        if (sachGioHangList.size() > 0) {
            for (int i = 0; i < sachGioHangList.size(); i++) {
                if (sachGioHangList.get(i).getGiaBanKM() != 0) {
                    tongTien = tongTien + sachGioHangList.get(i).getGiaBanKM()*sachGioHangList.get(i).getsLmua();
                } else {
                    tongTien = tongTien + sachGioHangList.get(i).getGiaBanGoc()*sachGioHangList.get(i).getsLmua();
                }
            }
            lbTamTinh.setText(tongTien + " đ");
        }
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

    private ArrayList<_Sach> sachGioHangList;
    private int maTK = -1;

    private void nhanGT() {
        Bundle bundle = getArguments();
        this.maTK = bundle.getInt("maTK");
        this.sachGioHangList = (ArrayList<_Sach>) getArguments().getSerializable("sachGioHangList");
        for (int i = 0; i < sachGioHangList.size(); i++) {

        }
    }

    private void anhXa() {
        lvGioHang = view.findViewById(R.id.lvGioHang);
        lbTenKH = view.findViewById(R.id.lbTenKH);
        lbNgayMua = view.findViewById(R.id.lbNgayMua);
        lbTamTinh = view.findViewById(R.id.lbTamTinh);
    }

    private void setLvGioHang() {
        gioHangAdapter = new A_GioHang(getActivity(), R.layout.dong_giohang, sachGioHangList);
        lvGioHang.setAdapter(gioHangAdapter);
    }
}
