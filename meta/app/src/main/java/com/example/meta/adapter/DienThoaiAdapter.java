package com.example.meta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meta.R;
import com.example.meta.model.SanPhamMoi;

import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public DienThoaiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamMoi sanPham = array.get(position);
        holder.TenSP.setText(sanPham.getTenSP());
        holder.DonGia.setText(sanPham.getDonGia());
        Glide.with(context).load(sanPham.getHinhAnh1()).into(holder.HinhAnh1);
        holder.MoTa.setText("Operating system:"+ sanPham.getCPU()+ "\n Ram"+ sanPham.getRam());

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TenSP, DonGia, MoTa;
        ImageView HinhAnh1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TenSP = itemView.findViewById(R.id.itemdt_ten);
            DonGia = itemView.findViewById(R.id.itemdt_gia);
            HinhAnh1 = itemView.findViewById(R.id.itemdt_image);
            MoTa = itemView.findViewById(R.id.itemdt_Mota);

        }
    }
}
