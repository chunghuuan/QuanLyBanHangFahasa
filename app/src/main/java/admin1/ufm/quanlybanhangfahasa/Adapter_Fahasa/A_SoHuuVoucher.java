package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._SoHuu;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_SoHuuVoucher extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<_SoHuu> soHuuList;

    public A_SoHuuVoucher(Context context, int layout, ArrayList<_SoHuu> soHuuList) {
        this.context = context;
        this.layout = layout;
        this.soHuuList = soHuuList;
    }

    @Override
    public int getCount() {
        return soHuuList.size();
    }

    @Override
    public Object getItem(int position) {
        return soHuuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        private TextView lb_MaVoucher,lb_TyLeGiam,lb_soLuong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.lb_MaVoucher=(TextView)convertView.findViewById(R.id.lb_MaVoucher);
            holder.lb_TyLeGiam=(TextView)convertView.findViewById(R.id.lb_TyLeGiam);
            holder.lb_soLuong=(TextView)convertView.findViewById(R.id.lb_soLuong);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder)convertView.getTag();
        }
        holder.lb_MaVoucher.setText(soHuuList.get(position).getMaVoucher());
        holder.lb_TyLeGiam.setText(""+soHuuList.get(position).getTyLeGiam());
        holder.lb_soLuong.setText("Số lượng: "+soHuuList.get(position).getSoLuong());
        return convertView;
    }
}
