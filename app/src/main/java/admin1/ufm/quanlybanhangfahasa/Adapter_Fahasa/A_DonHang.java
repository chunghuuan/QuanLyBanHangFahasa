package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_DonHang extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_DonHang> donHangList;

    public A_DonHang(Context context, int layout, ArrayList<_DonHang> donHangList) {
        this.context = context;
        this.layout = layout;
        this.donHangList = donHangList;
    }

    @Override
    public int getCount() {
        return donHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return donHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        TextView lb_maDH,lb_TenKH,lb_NVPhuTrach,lb_NgayLap,ngay_XL,lb_HTTT,lb_MaVoucher,lb_TyLeGiam,lb_TongTien,lb_PhiGiaoHang,lb_TinhTrang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView ==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new viewHolder();
            holder.lb_maDH=(TextView)convertView.findViewById(R.id.lb_maDH);
            holder.lb_TenKH=(TextView)convertView.findViewById(R.id.lb_TenKH);
            holder.lb_NVPhuTrach=(TextView)convertView.findViewById(R.id.lb_NVPhuTrach);
            holder.lb_NgayLap=(TextView)convertView.findViewById(R.id.lb_NgayLap);
            holder.ngay_XL=(TextView)convertView.findViewById(R.id.ngay_XL);
            holder.lb_HTTT=(TextView)convertView.findViewById(R.id.lb_HTTT);
            holder.lb_MaVoucher=(TextView)convertView.findViewById(R.id.lb_MaVoucher);
            holder.lb_TyLeGiam=(TextView)convertView.findViewById(R.id.lb_TyLeGiam);
            holder.lb_TongTien=(TextView)convertView.findViewById(R.id.lb_TongTien);
            holder.lb_PhiGiaoHang=(TextView)convertView.findViewById(R.id.lb_PhiGiaoHang);
            holder.lb_TinhTrang=(TextView)convertView.findViewById(R.id.lb_TinhTrang);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder)convertView.getTag();
        }
        holder.lb_maDH.setText("Đơn hàng: "+donHangList.get(position).getMaDH());
        holder.lb_TenKH.setText(donHangList.get(position).getTenKH());
        holder.lb_NVPhuTrach.setText(donHangList.get(position).getTenNV());
        holder.lb_NgayLap.setText(donHangList.get(position).getNgayLap());
        holder.ngay_XL.setText(donHangList.get(position).getNgayXuLy());
        holder.lb_HTTT.setText(donHangList.get(position).getTenHTTT());
        holder.lb_MaVoucher.setText(donHangList.get(position).getMaVoucher());
        holder.lb_TyLeGiam.setText(""+donHangList.get(position).getTyLeGiam());
        float tongTien = donHangList.get(position).getTongTien();
        String sTongTien = (String.valueOf(tongTien));
        String sTongTien2 = sTongTien.substring(0,sTongTien.length()-2);
        String kq = themPhay(sTongTien2);
        holder.lb_TongTien.setText("(VNĐ) "+kq);
        holder.lb_PhiGiaoHang.setText(""+donHangList.get(position).getPhiGiaoHang());
        holder.lb_TinhTrang.setText(donHangList.get(position).getGhiChu());
        if (donHangList.get(position).getGhiChu().equals("Hoàn tất")){
            holder.lb_TinhTrang.setTextColor(Color.GREEN);
        }
        else {
            holder.lb_TinhTrang.setTextColor(Color.RED);
        }

        return convertView;
    }
    public String themPhay(String a) {
        int b = Integer.parseInt(a);
        String kq = String.format("%,d%n", b);
        return kq;
    }
}
