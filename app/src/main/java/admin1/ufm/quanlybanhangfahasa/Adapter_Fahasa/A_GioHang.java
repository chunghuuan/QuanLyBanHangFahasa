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

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_GioHang extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_Sach> sachList;

    public A_GioHang(android.content.Context context, int layout, ArrayList<_Sach> sachList) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return sachList.get(position);
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

        if (sachList.get(position).getGiaBanKM() == sachList.get(position).getGiaBanGoc()) {
            holder.txt_GiaKM.setText("");
            holder.lbGiaKM.setText("");
        } else
            holder.txt_GiaKM.setText(sachList.get(position).getGiaBanKM() + " d");
        holder.txt_TenSach.setText(sachList.get(position).getTenSach());
        holder.txt_DonGia.setText("" + sachList.get(position).getGiaBanGoc());
        holder.txt_SoLuong.setText("" + sachList.get(position).getsLmua());
        if (!sachList.get(position).getuRLHinhAnh().equals("")) {
            Picasso.with(parent.getContext()).load(sachList.get(position).getuRLHinhAnh()).into(holder.urlHinhAnh);
        }
        return convertView;
    }
}
