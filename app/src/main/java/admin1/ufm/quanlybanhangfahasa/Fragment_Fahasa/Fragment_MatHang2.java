package admin1.ufm.quanlybanhangfahasa.Fragment_Fahasa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_GioHang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Sach;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa.A_SachBan;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.Dialog.Dialog_GioHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Fragment_MatHang2 extends Fragment {

    private View view;
    private TextView txtTongSLDHCXL, txtSLMua, txtTongGiaTri;
    private Button btnXemGioHang;
    private A_SachBan sachBanAdapter;
    private Data_Sach sachData;
    private ArrayList<_Sach> sachList;
    private ListView lvSach;
    private Spinner spSach;
    private SearchView searchView;
    private ImageView img_AddCart;
    private Button txtClick;

    private Data_TheLoai theLoaiData;
    private Data_GioHang gioHangData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fahasa_mathang, container, false);
        anhXa();
        nhanGT();
        kiemTraGioHang();
        return view;
    }

    private void kiemTraGioHang() {
        gioHangData = new Data_GioHang(getActivity());
        ArrayList<_GioHang> gioHangList = new ArrayList<>();
        gioHangList = gioHangData.getDLGioHang(this.maTK);
        for (int i = 0; i < gioHangList.size(); i++) {
        }
        sachList = new ArrayList<>();// Khởi tạo ArrayList ngay lập tức
        if (gioHangList.size() < 1) { // Rỗng
            setSpSach();
            setSearchView();
            setBtnXemGioHang();
        }
        // trường hợp tài khoản còn sản phẩm trong giở hàng.
        else {
            sachData = new Data_Sach(getActivity());
            sachList = sachData.getDLSach();
            for (int i = 0; i < sachList.size(); i++) {
                for (int j = 0; j < gioHangList.size(); j++) {
                    if (sachList.get(i).getMaSach() == gioHangList.get(j).getMaSach()) {
                        sachList.get(i).setAddCart(true);
                        sachList.get(i).setsLmua(gioHangList.get(j).getSlMua());
                        sachList.get(i).setGiaBanKM(gioHangList.get(j).getGiaKM());
                    }
                }
            }
            setSpSach();
            setSearchView();
            setBtnXemGioHang();
        }

    }


    public void setSpSach() {
        theLoaiData = new Data_TheLoai(getActivity());
        int dodai = theLoaiData.layDLTheLoai().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTenTheLoai = new ArrayList<>();
        dlTenTheLoai.add("Tất cả");
        for (int i = 0; i < dodai; i++) {
            dlTenTheLoai.add(theLoaiData.layDLTheLoai().get(i).getTenTheLoai());
        }
        arr = dlTenTheLoai.toArray(arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
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

    int giohang = 0;
    int slMua = 0;
    int tongTien = 0;
    private ArrayList<_Sach> sachGioHangList = new ArrayList<>();

    private void setSachBanLV(int a) {
        if (a == 0) {
            for (int i = 0; i < sachList.size(); i++) {
                _Sach sach = sachList.get(i);
                if (sach.isAddCart()) {
                    sachGioHangList.add(sach);
                    giohang++;
                    slMua = slMua + sach.getsLmua();
                    int giaTri = 0;
                    if (sach.getGiaBanGoc() != sach.getGiaBanKM()) {
                        giaTri = sach.getGiaBanKM() * sach.getsLmua();
                    } else {
                        giaTri = sach.getGiaBanGoc() * sach.getsLmua();
                    }
                    tongTien = tongTien + giaTri;
                    txtTongSLDHCXL.setText("" + giohang);
                    txtSLMua.setText("" + slMua);
                    txtTongGiaTri.setText(tongTien+"đ");
                }
            }
            sachBanAdapter = new A_SachBan(getActivity(), R.layout.dong_sanpham, sachList);
            sachBanAdapter.notifyDataSetChanged();
        } else
            sachBanAdapter.notifyDataSetChanged();
        sachBanAdapter.setData(sachList, new A_SachBan.clickAddtoCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, _Sach sach) {
                if (!sach.isAddCart()) {
                    sach.setAddCart(true);
                    imgAddToCart.setBackgroundResource(R.drawable.bg_grey_corner_6);
                    sachBanAdapter.notifyDataSetChanged();
                    giohang++;
                    slMua = slMua + sach.getsLmua();
                    sachGioHangList.add(sach);
                    int giaTri = 0;
                    if (sach.getGiaBanGoc() != sach.getGiaBanKM()) {
                        giaTri = sach.getGiaBanKM() * sach.getsLmua();
                    } else {
                        giaTri = sach.getGiaBanGoc() * sach.getsLmua();
                    }
                    tongTien = tongTien + giaTri;
                } else if (sach.isAddCart()) {
                    sachGioHangList.remove(sach);
                    sach.setAddCart(false);
                    imgAddToCart.setBackgroundResource(R.drawable.bg_red_corner_6);
                    sachBanAdapter.notifyDataSetChanged();
                    slMua = slMua - sach.getsLmua();
                    giohang--;
                    int giaTri = 0;
                    if (sach.getGiaBanGoc() != sach.getGiaBanKM()) {
                        giaTri = sach.getGiaBanKM() * sach.getsLmua();
                    } else {
                        giaTri = sach.getGiaBanGoc() * sach.getsLmua();
                    }
                    tongTien = tongTien - giaTri;
                }
                txtTongSLDHCXL.setText("" + giohang);
                txtSLMua.setText("" + slMua);
                txtTongGiaTri.setText(tongTien+"đ");
            }
        });

        lvSach.setAdapter(sachBanAdapter);
    }

    private ArrayList<_Sach> sachListDuPhong;

    private void setLvSach(int viTri) {
        if (sachList.size() > 0) { // Giỏ hàng của khách hàng đã có dữ liệu trước đây
            String luaChonSP = spSach.getSelectedItem().toString();
            if (luaChonSP.equals("Tất cả")) {
                setSachBanLV(0);
                return;
            }
            sachList = sachData.getDLSachTheoTL(viTri);
            setSachBanLV(1);
            Toast.makeText(getActivity(), "Thay đổi", Toast.LENGTH_SHORT).show();
        } else if (sachList.size() < 1) { // giỏ hàng của khách hàng chưa có dữ liệu trước đây
            System.out.println("Chạy dòng 2");
            sachList = new ArrayList<>();// Khởi tạo ArrayList nếu ArrayList chưa New
            sachData = new Data_Sach(getActivity());
            sachList = sachData.getDLSach();
            System.out.println("size:" + sachList.size());
            sachListDuPhong = new ArrayList<>(sachList);
            sachBanAdapter = new A_SachBan(getActivity(), R.layout.dong_sanpham, sachList);
            setSachBanLV(0);
        }
    }

    private int maTK;

    private void nhanGT() {
        int maTK = Integer.parseInt(getArguments().getString("maTK")); // return 0 ?
        this.maTK = maTK;
    }

    private void setBtnXemGioHang() {

        btnXemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_GioHang dialogFragment = new Dialog_GioHang();
                Bundle bundle = new Bundle();
                bundle.putInt("maTK", maTK);
                bundle.putSerializable("sachGioHangList", sachGioHangList); // GUI DU LIEU ARRAYLIST
                dialogFragment.setArguments(bundle);
                dialogFragment.show((getActivity()).getSupportFragmentManager(), "Image Dialog");
            }
        });
    }

    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sachBanAdapter.filter(newText);
                return false;
            }
        });
    }

    private void anhXa() {
        txtTongGiaTri = view.findViewById(R.id.txtTongGiaTri);
        btnXemGioHang = view.findViewById(R.id.btnXemGioHang);
        txtSLMua = view.findViewById(R.id.txtSLMua);
        txtTongSLDHCXL = view.findViewById(R.id.txtTongSLDHCXL);
        img_AddCart = view.findViewById(R.id.img_AddCart);
        lvSach = view.findViewById(R.id.lvSach);
        spSach = view.findViewById(R.id.spSach);
        searchView = view.findViewById(R.id.fhsSearchView);
    }


}
