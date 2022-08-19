package admin1.ufm.quanlybanhangfahasa.Adapter_Fahasa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class A_MatHang extends RecyclerView.Adapter<A_MatHang.ProductViewHolder> {


    private ArrayList<_Sach> sachList;

    private  clickAddtoCartListener clickAddtoCartListener;
    public interface clickAddtoCartListener{
        void onClickAddToCart(ImageView imgAddToCart, _Sach sach);
    }

    private interface clickToCartListener{
        void reTurnClickToCart(String maSach);
    }

    public void setData(ArrayList<_Sach> sachList,clickAddtoCartListener listener){
        this.sachList = sachList;
        this.clickAddtoCartListener = listener;
        notifyDataSetChanged();
    }

    private ViewGroup parent;
    @Override
    public ProductViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham,parent,false);
        this.parent = parent;
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull A_MatHang.ProductViewHolder holder, int position) {
        _Sach sach = sachList.get(position);
        if (sach == null){
            return;
        }
        if (!sachList.get(position).getuRLHinhAnh().equals("")) {
            Picasso.with(this.parent.getContext()).load(sach.getuRLHinhAnh()).into(holder.imgHinhAnh);
        }
        holder.lbTenSach.setText(sach.getTenSach());
        if (sach.isAddCart()){
            holder.imgAddCart.setBackgroundResource(R.drawable.bg_grey_corner_6);
        }
        else
            holder.imgAddCart.setBackgroundResource(R.drawable.bg_red_corner_6);

        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sach.isAddCart()){
                    clickAddtoCartListener.onClickAddToCart(holder.imgAddCart, sach);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (sachList != null){
            return sachList.size();
        };
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHinhAnh,imgAddCart;
        private TextView lbTenSach,giaBan;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            anhXa();
        }

        private void anhXa() {
            imgHinhAnh = itemView.findViewById(R.id.img_URLHinhAnh);
            imgAddCart = itemView.findViewById(R.id.img_AddCart);
            lbTenSach = itemView.findViewById(R.id.txt_TenSach);
            giaBan = itemView.findViewById(R.id.txt_GiaBanGoc);
        }
    }
}
