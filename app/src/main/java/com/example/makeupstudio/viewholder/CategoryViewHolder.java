package com.example.makeupstudio.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupstudio.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout categoryLayout;
    public CircleImageView categoryImg;
    public TextView categoryName;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryLayout = itemView.findViewById(R.id.categoryLayout);
        categoryImg = itemView.findViewById(R.id.categoryImg);
        categoryName = itemView.findViewById(R.id.categoryName);

    }
}
