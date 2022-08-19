package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_Voucher extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_Voucher> voucherList;

    public A_Voucher(Context context, int layout, ArrayList<_Voucher> voucherList) {
        this.context = context;
        this.layout = layout;
        this.voucherList = voucherList;
    }

    @Override
    public int getCount() {
        return voucherList.size();
    }

    @Override
    public Object getItem(int position) {
        return voucherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        TextView txt_MaVoucher,txt_TyLeGiam;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView ==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new viewHolder();
            holder.txt_MaVoucher=(TextView)convertView.findViewById(R.id.txt_MaVoucher);
            holder.txt_TyLeGiam=(TextView)convertView.findViewById(R.id.txt_TyLeGiam);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder)convertView.getTag();
        }
        holder.txt_MaVoucher.setText(voucherList.get(position).getMaVoucher());
        holder.txt_TyLeGiam.setText(""+voucherList.get(position).getTyLeGiam());
        return convertView;
    }
}
