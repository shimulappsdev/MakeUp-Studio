package com.example.makeupstudio.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.makeupstudio.R;
import com.example.makeupstudio.model.Brand;
import com.example.makeupstudio.viewholder.BrandViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandAdapter extends RecyclerView.Adapter<BrandViewHolder> {

    private Context context;
    private List<Brand> brandList;

    public BrandAdapter(Context context, List<Brand> brandList) {
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BrandViewHolder(LayoutInflater.from(context).inflate(R.layout.popular_brands_layout, parent, false));
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {

        Brand brand = brandList.get(position);
        holder.brandName.setText(brand.getBrand_name());
        Glide.with(context).load(brand.getBrand_image()).placeholder(R.drawable.imagehint).into(holder.brandImg);

        holder.itemView.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());

            View dialogView = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.brand_popup, null);

            de.hdodenhof.circleimageview.CircleImageView popupImage;
            TextView popupText;
            popupImage = dialogView.findViewById(R.id.popupImage);
            popupText = dialogView.findViewById(R.id.popupText);
            popupText.setText(brand.getBrand_name());
            Glide.with(view.getRootView().getContext()).load(brand.getBrand_image()).placeholder(R.drawable.imagehint).into(popupImage);

            builder.setView(dialogView);
            builder.setCancelable(true);
            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
}
