package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._NhanVien;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_NhanVien extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<_NhanVien> nhanVienList;

    public A_NhanVien(Context context, int layout, ArrayList<_NhanVien> nhanVienList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
        System.out.println("size:" + nhanVienList.size());
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder {
        private TextView txt_TenNV, txt_NgaySinhNV, txt_QueQuanNV, txt_NgayVaoLam, txt_NgayKetThuc, txt_SDTNV, txt_EmailNV;
        private LinearLayout ln1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.txt_TenNV = (TextView) convertView.findViewById(R.id.txt_TenNV);
            holder.txt_NgaySinhNV = (TextView) convertView.findViewById(R.id.txt_NgaySinhNV);
            holder.txt_QueQuanNV = (TextView) convertView.findViewById(R.id.txt_QueQuanNV);
            holder.txt_NgayVaoLam = (TextView) convertView.findViewById(R.id.txt_NgayVaoLam);
            holder.txt_NgayKetThuc = (TextView) convertView.findViewById(R.id.txt_NgayKetThuc);
            holder.txt_SDTNV = (TextView) convertView.findViewById(R.id.txt_SDTNV);
            holder.txt_EmailNV = (TextView) convertView.findViewById(R.id.txt_EmailNV);
            holder.ln1 = convertView.findViewById(R.id.ln1);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.txt_TenNV.setText(nhanVienList.get(position).getTenNV());
        holder.txt_NgaySinhNV.setText(nhanVienList.get(position).getNamSinh());
        holder.txt_QueQuanNV.setText(nhanVienList.get(position).getQueQuanNV());
        holder.txt_NgayVaoLam.setText(nhanVienList.get(position).getNgayVaoLam());
        holder.txt_NgayKetThuc.setText(nhanVienList.get(position).getNgayKetThuc());
        holder.txt_SDTNV.setText(nhanVienList.get(position).getSdtNV());
        holder.txt_EmailNV.setText(nhanVienList.get(position).getEmailNV());

        if (!nhanVienList.get(position).isLamViec()||!nhanVienList.get(position).getNgayKetThuc().equals("")) {
            System.out.println(holder.txt_TenNV.getText()+" Adapter");
            holder.txt_TenNV.setPaintFlags(holder.txt_TenNV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.ln1.setVisibility(View.VISIBLE);
        }
        else {
            holder.txt_TenNV.setPaintFlags(0);
            holder.ln1.setVisibility(View.GONE);
        }
        return convertView;
    }
}

