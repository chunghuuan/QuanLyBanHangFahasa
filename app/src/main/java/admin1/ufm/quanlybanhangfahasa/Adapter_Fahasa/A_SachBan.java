package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_SachBan extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<_Sach> sachList;
    private ArrayList<_Sach> sachListDuPhong;
    Data_TheLoai theLoaiData;
    Activity activity;

    public A_SachBan(Context context, int layout, ArrayList<_Sach> sachList) {
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

    private clickAddtoCartListener clickAddtoCartListener;

    public interface clickAddtoCartListener {
        void onClickAddToCart(ImageView imgAddToCart, _Sach sach);
    }

    public void setData(ArrayList<_Sach> sachList, clickAddtoCartListener listener) {
        this.sachList = sachList;
        this.clickAddtoCartListener = listener;
        notifyDataSetChanged();
    }

    private class viewHolder {
        TextView txt_TenSach, txt_NamXB, txt_GiaBanGoc, txt_TheLoai, txt_GiaKM;
        ImageView img_URLHinhAnh, img_AddCart;
        EditText txt_slMua;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        _Sach sach = sachList.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.txt_GiaKM = convertView.findViewById(R.id.txt_GiaKM);
            holder.txt_TheLoai = convertView.findViewById(R.id.txt_TheLoai);
            holder.txt_TenSach = (TextView) convertView.findViewById(R.id.txt_TenSach);
            holder.txt_NamXB = (TextView) convertView.findViewById(R.id.txt_NamXB);
            holder.txt_GiaBanGoc = (TextView) convertView.findViewById(R.id.txt_GiaBanGoc);
            holder.img_URLHinhAnh = (ImageView) convertView.findViewById(R.id.img_URLHinhAnh);
            holder.img_AddCart = (ImageView) convertView.findViewById(R.id.img_AddCart);
            holder.txt_slMua = convertView.findViewById(R.id.txt_slMua);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        if (sachList.get(position).getGiaBanGoc() != sachList.get(position).getGiaBanKM()) {
            holder.txt_GiaKM.setText("Giảm còn: " + sachList.get(position).getGiaBanKM() + " d");
//            holder.txt_GiaBanGoc.setPaintFlags(holder.txt_GiaBanGoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // Gạch ngang giá cũ
        } else {
            holder.txt_GiaKM.setText("");
        }
        holder.txt_TheLoai.setText(sachList.get(position).getTenTheLoai());
        holder.txt_TenSach.setText(sachList.get(position).getTenSach());
        holder.txt_NamXB.setText(sachList.get(position).getNamXB());
        holder.txt_GiaBanGoc.setText("Giá: " + sachList.get(position).getGiaBanGoc());
        if (!sachList.get(position).getuRLHinhAnh().equals("")) {
            Picasso.with(parent.getContext()).load(sachList.get(position).getuRLHinhAnh()).into(holder.img_URLHinhAnh);
        }
        if (sachList.get(position).isAddCart()) {
            holder.img_AddCart.setBackgroundResource(R.drawable.bg_grey_corner_6);
        } else {
            holder.img_AddCart.setBackgroundResource(R.drawable.bg_red_corner_6);
        }
        holder.img_AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slMua = Integer.parseInt(holder.txt_slMua.getText().toString());
                if (!sach.isAddCart()) {
                    sach.setsLmua(slMua);
                    clickAddtoCartListener.onClickAddToCart(holder.img_AddCart, sach);
                } else if (sach.isAddCart()) {
                    clickAddtoCartListener.onClickAddToCart(holder.img_AddCart, sach);
                }
            }
        });
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        sachList.clear();
        if (charText.length() == 0) {
            sachList.addAll(sachListDuPhong);
        } else {
            for (_Sach sach : sachListDuPhong) {
                if (sach.getTenSach().toLowerCase(Locale.getDefault()).contains(charText)) {
                    sachList.add(sach);
                }
            }
        }
        notifyDataSetChanged();
    }

}
