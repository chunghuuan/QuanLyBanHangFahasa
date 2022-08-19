package admin1.ufm.quanlybanhangfahasa.MucKhac;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa.Fragment_DHCaNhan;
import admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa.Fragment_DonHangChuaXL;
import admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa.Fragment_MatHang2;
import admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa.Fragment_TuyChon;

public class ViewPagerAdapterFahasa3 extends FragmentStateAdapter {

    private String maTK;
    public ViewPagerAdapterFahasa3(@NonNull FragmentActivity fragmentActivity, String maTK) {
        super(fragmentActivity);
        this.maTK = maTK;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:{
                Fragment_MatHang2 f_matHang2 = new Fragment_MatHang2();
                Bundle bundle = new Bundle();
                bundle.putString("maTK",this.maTK);
                f_matHang2.setArguments(bundle);
                return f_matHang2;
            }
            case 1:{
                Fragment_DHCaNhan f_gioHang = new Fragment_DHCaNhan();
                Bundle bundle = new Bundle();
                bundle.putString("maTK",this.maTK);
                f_gioHang.setArguments(bundle);
                return f_gioHang;
            }
            case 2: {
                Fragment_TuyChon f_tuychon = new Fragment_TuyChon();
                Bundle bundle = new Bundle();
                bundle.putString("maTK",this.maTK);// Tạo dữ liệu cần chuyển
                f_tuychon.setArguments(bundle); // Gửi dữ liệu sang Fragment
                return f_tuychon;
            }
            default:{
                Fragment_DonHangChuaXL f_donhang = new Fragment_DonHangChuaXL();
                Bundle bundle = new Bundle();
                bundle.putString("maTK",this.maTK);
                f_donhang.setArguments(bundle);
                return f_donhang;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
