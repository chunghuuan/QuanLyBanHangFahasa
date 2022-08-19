package admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_KhachHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NhanVien;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activiti_ThongTinCaNhan;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_DangNhap;
import admin1.ufm.quanlybanhangfahasa.R;

public class Fragment_TuyChon extends Fragment {
    private View view;
    private Button btnDangXuat;
    private Button btnThongTinCaNhan,btnVeChungToi,btnGopY,btnChiaSe;
    private TextView lbTenNSD;
    private String maTK;
    private int maCV = -1;
    private Data_NhanVien nhanVienData;
    private Data_TaiKhoan taiKhoanData;
    private Data_KhachHang khachHangData;

    private void anhXa(){
        lbTenNSD = view.findViewById(R.id.lbTenTK);
        btnVeChungToi=(Button)view.findViewById(R.id.btnVeChungToi);
        btnThongTinCaNhan=(Button)view.findViewById(R.id.btnThongTinCaNhan);

        btnGopY=(Button)view.findViewById(R.id.btnGopY);
        btnChiaSe=(Button)view.findViewById(R.id.btnChiaSe);
        btnDangXuat=(Button)view.findViewById(R.id.btnDangXuat);
    }

    private void nhanGT(){
        String maTK = getArguments().getString("maTK");
        Data_TaiKhoan taiKhoanData = new Data_TaiKhoan(getActivity());
        this.maCV = taiKhoanData.getMaCV2(Integer.parseInt(maTK));
        this.maTK = maTK;
    }
    public void datTenNSD(){
        nhanGT();
        taiKhoanData = new Data_TaiKhoan(getActivity());
        if (maCV==2){
            nhanVienData = new Data_NhanVien(getActivity());
            lbTenNSD.setText(nhanVienData.layTenNV(this.maTK));
        }
        else if(maCV==3){
            khachHangData = new Data_KhachHang(getActivity());
            lbTenNSD.setText(khachHangData.layTenKH1(Integer.parseInt(this.maTK)));
        }


    }

    private void setBtnThongTinCaNhan(){
        btnThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activiti_ThongTinCaNhan.class); // Activity Cha = getActivity = TrangChu
                i.putExtra("maTK",maTK); // Gửi dữ liệu sang Form ViTien
                i.putExtra("maCV", maCV);
                startActivity(i);
            }
        });
    }

    private void setBtnDangXuat(){
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_DangNhap.class); // Activity Cha = GetActivity() = TrangChu
                getActivity().finish();
                startActivity(i);
            }
        });
    }

    private void setBtnVeChungToi(){
        btnVeChungToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://vi-vn.facebook.com/fahasa/"));
                startActivity(intent);
            }
        });
    }

    private void setBtnGopY(){
        btnGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://docs.google.com/spreadsheets/d/1Xqqtee1S9JLn2xa99SjbFZ15cyO87r0nH5IEtB1agSk/edit?usp=sharin" +
                        "g&fbclid=IwAR0ZpA00DDMlG6q2-rh0vQMmCZSHecxw_uD9iYLylXBVbnGzlGgXJn8bsfM"));
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fahasa_tuychon,container,false);
        anhXa();
        datTenNSD();
        setBtnDangXuat();
        setBtnGopY();
        setBtnVeChungToi();
        setBtnThongTinCaNhan();
        return view;
    }
}
//}
