package com.example.makeupstudio.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupstudio.R;

public class MakeupItemViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout makeupItemLayout;
    public ImageView makeupItemImg;
    public TextView makeupItemName, makeupItemAbout;

    public MakeupItemViewHolder(@NonNull View itemView) {
        super(itemView);

        makeupItemLayout = itemView.findViewById(R.id.makeupItemLayout);
        makeupItemImg = itemView.findViewById(R.id.makeupItemImg);
        makeupItemName = itemView.findViewById(R.id.makeupItemName);
        makeupItemAbout = itemView.findViewById(R.id.makeupItemAbout);

    }
}
