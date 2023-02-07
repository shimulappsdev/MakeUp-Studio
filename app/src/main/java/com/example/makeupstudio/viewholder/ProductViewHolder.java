package com.example.makeupstudio.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupstudio.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout productLayout;
    public CircleImageView makeUpProductImg;
    public TextView makeUpProductName;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        productLayout = itemView.findViewById(R.id.productLayout);
        makeUpProductImg = itemView.findViewById(R.id.makeUpProductImg);
        makeUpProductName = itemView.findViewById(R.id.makeUpProductName);

    }
}
