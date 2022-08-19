package admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_CTDH;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_DonHang2;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Fragment_DonHangChuaXL extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private Context context = getContext();
    private View view;
    private ListView lvDHChXL;
    private TextView txtTongSLDHCXL, txtTongGiaTri;
    private Data_DonHang donHangData;
    private ArrayList<_DonHang> donHangList;
    private A_DonHang2 DonHang2Adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fahasa_donhang, container, false);
        anhXa();
        nhanGT();
        setLvDHChXL();
        clicklvDHChXL();
        thongKe();
        setSwipeRefreshLayout();
        return view;
    }

    public String themPhay(String a) {
        int b = Integer.parseInt(a);
        String kq = String.format("%,d%n", b);
        return kq;
    }

    private void thongKe() {
        int slDH = donHangList.size();
        txtTongSLDHCXL.setText("" + slDH);

        float tongTien = 0 ;
        for (int i = 0;i<donHangList.size();i++){
            tongTien = tongTien + donHangList.get(i).getTongTien();
        }
        String sTongTien = String.valueOf(tongTien);
        String eTongTien = sTongTien.substring(0,sTongTien.length()-2);
        eTongTien = themPhay(eTongTien);

        txtTongGiaTri.setText(eTongTien);

    }

    private void anhXa() {
        lvDHChXL = view.findViewById(R.id.lvDHChXL);
        txtTongSLDHCXL = view.findViewById(R.id.txtTongSLDHCXL);
        txtTongGiaTri = view.findViewById(R.id.txtTongGiaTri);
        swipeRefreshLayout =view.findViewById(R.id.SwipeRefreshLayout);
    }

    private void setLvDHChXL() {
        donHangData = new Data_DonHang(getActivity());
        donHangList = donHangData.layDLDonHangChuaXL();
        DonHang2Adapter = new A_DonHang2(getActivity(), R.layout.dong_donhang2, donHangList);
        lvDHChXL.setAdapter(DonHang2Adapter);
        ;
    }

    private void clicklvDHChXL() {
        lvDHChXL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maDH = donHangList.get(position).getMaDH();
                Intent i = new Intent(getActivity(), Activity_CTDH.class);
                i.putExtra("maDH", maDH); // Gửi dữ liệu sang Activity Trang chủ
                i.putExtra("maTK", maTK); // Gửi dữ liệu sang Activity Trang chủ
                startActivity(i);
            }
        });
    }

    private void setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private int maTK;
    private void nhanGT(){
        String maTK = getArguments().getString("maTK");
        this.maTK = Integer.parseInt(maTK);
    }

    @Override
    public void onRefresh() {
        Handler handler = new Handler();
        setLvDHChXL();
        thongKe();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },500); // 0,5 s reset
    }
}
