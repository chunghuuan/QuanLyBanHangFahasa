package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_ThanhToan extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_GioHang> gioHangList;

    public A_ThanhToan(android.content.Context context, int layout, ArrayList<_GioHang> gioHangList) {
        this.context = context;
        this.layout = layout;
        this.gioHangList = gioHangList;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder {
        ImageView urlHinhAnh;
        TextView txt_TenSach, txt_DonGia, txt_GiamGiaKM, txt_SoLuong, txt_GiaKM,lbGiaKM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.lbGiaKM = convertView.findViewById(R.id.lbGiaKM);
            holder.txt_GiaKM = convertView.findViewById(R.id.txt_GiaKM);
            holder.txt_TenSach = (TextView) convertView.findViewById(R.id.txt_TenSach);
            holder.txt_DonGia = (TextView) convertView.findViewById(R.id.txt_DonGia);
            holder.txt_GiamGiaKM = (TextView) convertView.findViewById(R.id.txt_GiamGiaKM);
            holder.txt_SoLuong = (TextView) convertView.findViewById(R.id.txt_SoLuong);
            holder.urlHinhAnh = (ImageView) convertView.findViewById(R.id.urlHinhAnh);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        if (gioHangList.get(position).getGiaKM() == gioHangList.get(position).getGiaGoc()) {
            holder.txt_GiaKM.setText("");
            holder.lbGiaKM.setText("");
        } else
            holder.txt_GiaKM.setText(gioHangList.get(position).getGiaKM() + " d");
        holder.txt_TenSach.setText(gioHangList.get(position).getTenSach());
        holder.txt_DonGia.setText("" + gioHangList.get(position).getGiaGoc());
        holder.txt_SoLuong.setText("" + gioHangList.get(position).getSlMua());
        if (!gioHangList.get(position).getUrlHinhAnh().equals("")) {
            Picasso.with(parent.getContext()).load(gioHangList.get(position).getUrlHinhAnh()).into(holder.urlHinhAnh);
        }
        return convertView;
    }
}
