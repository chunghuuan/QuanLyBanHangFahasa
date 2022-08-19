package admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_DonHangDaXL;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_Sach;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_ThongKeBanHang;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_Voucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Fragment_CapNhat extends Fragment {
    private View view;
    Button btnTXSSach,btnTXSVoucher,btnTXSKhachHang,btnDHDaXuLy,btnThongKe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fahasa_capnhat,container,false);
        anhXa();
        setBtnTXSVoucher();
        nhanGT();
        setBtnTXSSach();
        setBtnDHDaXuLy();
        setBtnTXSKhachHang();
        setBtnThongKe();
        return view;
    }

    private String maTK;
    private void nhanGT(){
        String maTK = getArguments().getString("maTK");
        this.maTK = maTK;
    }

    private void setBtnTXSKhachHang(){
        btnTXSKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_KhachHang.class);
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        btnTXSSach = view.findViewById(R.id.btnTXSSach);
        btnTXSVoucher = view.findViewById(R.id.btnTXSVoucher);
        btnTXSKhachHang = view.findViewById(R.id.btnTXSKhachHang);
        btnDHDaXuLy = view.findViewById(R.id.btnDHDaXuLy);
        btnThongKe= view.findViewById(R.id.btnThongKe);
    }

    private void setBtnThongKe(){
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_ThongKeBanHang.class);
                startActivity(i);
            }
        });
    }
    public void setBtnDHDaXuLy(){
        btnDHDaXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_DonHangDaXL.class);
                startActivity(i);
            }
        });
    }

    public void setBtnTXSVoucher() {
        btnTXSVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_Voucher.class);
                startActivity(i);
            }
        });
    }
    public void setBtnTXSSach(){
        btnTXSSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_Sach.class);
                startActivity(i);
            }
        });
    }
}
