package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._KhachHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_KhachHang extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_KhachHang> khachHangList;

    public A_KhachHang(Context context, int layout, ArrayList<_KhachHang> khachHangList) {
        this.context = context;
        this.layout = layout;
        this.khachHangList = khachHangList;
    }

    @Override
    public int getCount() {
        return khachHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        TextView txt_TenKH,txt_NgaySinhKH,txt_QueQuanKH,txt_SDTKH,txt_EmailKH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView ==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new viewHolder();
            holder.txt_TenKH=(TextView)convertView.findViewById(R.id.txt_TenKH);
            holder.txt_NgaySinhKH=(TextView)convertView.findViewById(R.id.txt_NgaySinhKH);
            holder.txt_QueQuanKH=(TextView)convertView.findViewById(R.id.txt_QueQuanKH);
            holder.txt_SDTKH=(TextView)convertView.findViewById(R.id.txt_SDTKH);
            holder.txt_EmailKH=(TextView)convertView.findViewById(R.id.txt_EmailKH);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder)convertView.getTag();
        }
        holder.txt_TenKH.setText(khachHangList.get(position).getTenKH());
        holder.txt_NgaySinhKH.setText(khachHangList.get(position).getNgaySinh());
        holder.txt_QueQuanKH.setText(khachHangList.get(position).getQueQuan());
        holder.txt_SDTKH.setText(khachHangList.get(position).getSdtKH());
        holder.txt_EmailKH.setText(khachHangList.get(position).getEmailKH());
        return convertView;
    }
}
