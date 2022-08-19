package admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_DHKH;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_LSDonHang;
import admin1.ufm.quanlybanhangfahasa.Activity_Fahasa.Activity_SoHuuVoucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Fragment_DHCaNhan extends Fragment {
    private Button btnDH, btnLSDH, btnVoucher;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fahasa_dhcanhan, container, false);
        anhXa();
        nhanGT();
        setBtnVoucher();
        setBtnLSDH();
        setBtnDH();
        return view;
    }
    private int maTK;
    private void nhanGT() {
        int maTK = Integer.parseInt(getArguments().getString("maTK")); // return 0 ?
        this.maTK = maTK;
    }

    private void setBtnDH(){
        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_DHKH.class);
                i.putExtra("maTK",maTK);
                startActivity(i);
            }
        });
    }

    private void setBtnLSDH(){
        btnLSDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_LSDonHang.class);
                i.putExtra("maTK",maTK);
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        btnDH = view.findViewById(R.id.btnDH);
        btnLSDH = view.findViewById(R.id.btnLSDH);
        btnVoucher = view.findViewById(R.id.btnVoucher);
    }
    private void setBtnVoucher(){
        btnVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Activity_SoHuuVoucher.class);
                i.putExtra("maTK",maTK);
                startActivity(i);
            }
        });
    }

}
