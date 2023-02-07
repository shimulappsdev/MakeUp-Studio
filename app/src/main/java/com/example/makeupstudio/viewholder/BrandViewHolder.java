package com.example.makeupstudio.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupstudio.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandViewHolder extends RecyclerView.ViewHolder{

    public ConstraintLayout brandLayout;
    public CircleImageView brandImg;
    public TextView brandName;

    public BrandViewHolder(@NonNull View itemView) {
        super(itemView);

        brandLayout = itemView.findViewById(R.id.brandLayout);
        brandImg = itemView.findViewById(R.id.brandImg);
        brandName = itemView.findViewById(R.id.brandName);

    }
}
