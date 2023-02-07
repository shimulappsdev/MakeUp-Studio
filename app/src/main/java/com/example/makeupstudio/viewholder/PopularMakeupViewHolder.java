package com.example.makeupstudio.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupstudio.R;

public class PopularMakeupViewHolder extends RecyclerView.ViewHolder {

    public ImageView makeupItemImg;
    public TextView makeupItemName;

    public PopularMakeupViewHolder(@NonNull View itemView) {
        super(itemView);

        makeupItemImg = itemView.findViewById(R.id.makeupItemImg);
        makeupItemName = itemView.findViewById(R.id.makeupItemName);

    }
}
