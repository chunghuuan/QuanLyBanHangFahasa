package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_Sach extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_Sach> sachList;
    private ArrayList<_Sach> sachListDuPhong;
    Data_TheLoai theLoaiData;
    Activity activity;

    public A_Sach(Context context, int layout, ArrayList<_Sach> sachList, Activity activity) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
        sachListDuPhong = new ArrayList<>();
        sachListDuPhong.addAll(sachList);
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

    private class viewHolder{
        TextView txt_TheLoai,txt_TenSach, txt_NamXB,txt_GiaBanGoc,txt_SLTon;
        ImageView img_uRLHinhAnh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new viewHolder();
            holder.txt_TheLoai = (TextView)convertView.findViewById(R.id.txt_TheLoai);
            holder.txt_TenSach = (TextView)convertView.findViewById(R.id.txt_TenSach);
            holder.txt_NamXB = (TextView)convertView.findViewById(R.id.txt_NamXB);
            holder.txt_GiaBanGoc = (TextView)convertView.findViewById(R.id.txt_GiaBanGoc);
            holder.txt_SLTon = (TextView)convertView.findViewById(R.id.txt_SLTon);
            holder.img_uRLHinhAnh = (ImageView)convertView.findViewById(R.id.img_URLHinhAnh);
            convertView.setTag(holder);
        }
        else {
            holder =(viewHolder) convertView.getTag();
        }
//        theLoaiData = new Data_TheLoai(activity);
        holder.txt_TheLoai.setText(sachList.get(position).getTenTheLoai());
        holder.txt_TenSach.setText(sachList.get(position).getTenSach());
        holder.txt_NamXB.setText(sachList.get(position).getNamXB());
        holder.txt_GiaBanGoc.setText("Giá: "+sachList.get(position).getGiaBanGoc());
        holder.txt_SLTon.setText("Tồn: "+sachList.get(position).getsLTon());
        if (!sachList.get(position).getuRLHinhAnh().equals("")){
            Picasso.with(parent.getContext()).load(sachList.get(position).getuRLHinhAnh()).into(holder.img_uRLHinhAnh);
        }
        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        sachList.clear();
        if (charText.length()==0){
            sachList.addAll(sachListDuPhong);
        }
        else{
            for (_Sach sach: sachListDuPhong){
                if (sach.getTenSach().toLowerCase(Locale.getDefault()).contains(charText)){
                    sachList.add(sach);
                }
            }
        }
        notifyDataSetChanged();
    }

}
