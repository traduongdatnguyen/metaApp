
//MyPurchaseAdapter
package com.example.meta.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meta.Interface.IImageClickListenner;
import com.example.meta.Interface.ItemClickListener;
import com.example.meta.R;
import com.example.meta.activity.MainActivity;
import com.example.meta.model.SanPhamMoi;
import com.example.meta.model.Wishlist;
import com.example.meta.retrofit.ApiBanHang;
import com.example.meta.retrofit.RetrofitClient;
import com.example.meta.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MyPurchaseAdapter extends RecyclerView.Adapter<MyPurchaseAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public MyPurchaseAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.irtem_spdonhang,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SanPhamMoi sanPhamMoi = array.get(position);
        holder.item_mych_tensp.setText(" "+sanPhamMoi.getTenSP());
        holder.item_wishlist_gia.setText("$ "+ sanPhamMoi.getDonGia());
        Glide.with(context).load(Utils.URL_IMAGE + sanPhamMoi.getHinhAnh1()).into(holder.item_mych_image);

        holder.setListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item_wishlist_gia, item_mych_tensp;
        ImageView item_mych_image,item_wishlist_tru;
        IImageClickListenner listenner;
        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_wishlist_gia = itemView.findViewById(R.id.item_wishlist_gia);
            item_mych_image  = itemView.findViewById(R.id.item_mych_image);
            item_mych_tensp = itemView.findViewById(R.id.item_my_tensp);


        }



        public void setListenner(IImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {
            if(view == item_wishlist_tru){
                listenner.onImageClick(view, getAdapterPosition(),1);
            }
        }
    }
}
