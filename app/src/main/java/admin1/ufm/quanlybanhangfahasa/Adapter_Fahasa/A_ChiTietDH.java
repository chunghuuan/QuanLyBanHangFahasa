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

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.R;

public class A_ChiTietDH extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_ChiTietDH> chiTietDHList;

    public A_ChiTietDH(Context context, int layout, ArrayList<_ChiTietDH> chiTietDHList) {
        this.context = context;
        this.layout = layout;
        this.chiTietDHList = chiTietDHList;
    }

    @Override
    public int getCount() {
        return chiTietDHList.size();
    }

    @Override
    public Object getItem(int position) {
        return chiTietDHList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        ImageView urlHinhAnh;
        TextView txt_TenSach,txt_DonGia,txt_GiamGiaKM,txt_SoLuong,txt_TamTinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new viewHolder();
            holder.txt_TenSach=(TextView)convertView.findViewById(R.id.txt_TenSach);
            holder.txt_DonGia=(TextView)convertView.findViewById(R.id.txt_DonGia);
            holder.txt_GiamGiaKM=(TextView)convertView.findViewById(R.id.txt_GiamGiaKM);
            holder.txt_SoLuong=(TextView)convertView.findViewById(R.id.txt_SoLuong);
            holder.txt_TamTinh=(TextView)convertView.findViewById(R.id.txt_TamTinh);
            holder.urlHinhAnh=(ImageView) convertView.findViewById(R.id.urlHinhAnh);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder)convertView.getTag();
        }
        holder.txt_TenSach.setText(chiTietDHList.get(position).getTenSach());
        holder.txt_DonGia.setText(""+chiTietDHList.get(position).getGiaBanGoc());
        holder.txt_GiamGiaKM.setText(""+chiTietDHList.get(position).getGiaKM());
        holder.txt_SoLuong.setText(""+chiTietDHList.get(position).getSoLuong());
        int tamTinh = chiTietDHList.get(position).getGiaKM()*chiTietDHList.get(position).getSoLuong();
        holder.txt_TamTinh.setText(""+tamTinh);
        if (!chiTietDHList.get(position).getuRLHinhAnh().equals("")){
            Picasso.with(parent.getContext()).load(chiTietDHList.get(position).getuRLHinhAnh()).into(holder.urlHinhAnh);
        }
        return convertView;
    }
}
