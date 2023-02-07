package com.example.makeupstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.makeupstudio.R;
import com.example.makeupstudio.activity.ContainerActivity;
import com.example.makeupstudio.model.PopularMakeup;
import com.example.makeupstudio.viewholder.PopularMakeupViewHolder;

import java.util.List;

public class PopularMakeupAdapter extends RecyclerView.Adapter<PopularMakeupViewHolder> {

    private Context context;
    private List<PopularMakeup> popularMakeupList;

    public PopularMakeupAdapter(Context context, List<PopularMakeup> popularMakeupList) {
        this.context = context;
        this.popularMakeupList = popularMakeupList;
    }

    @NonNull
    @Override
    public PopularMakeupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularMakeupViewHolder(LayoutInflater.from(context).inflate(R.layout.popular_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMakeupViewHolder holder, int position) {

        PopularMakeup popularMakeup = popularMakeupList.get(position);
        holder.makeupItemName.setText(popularMakeup.getPopularMakeUp_name());
        Glide.with(context).load(popularMakeup.getPopularMakeUp_image()).placeholder(R.drawable.imagehint).into(holder.makeupItemImg);

        String makeupImg = popularMakeup.getPopularMakeUp_image();
        String makeupName = popularMakeup.getPopularMakeUp_name();
        String makeupDescription = popularMakeup.getPopularMakeUp_description();

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContainerActivity.class);
            intent.putExtra("popularMakeup", "popularMakeup");
            intent.putExtra("makeupImg", makeupImg);
            intent.putExtra("makeupName", makeupName);
            intent.putExtra("makeupDescription", makeupDescription);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return popularMakeupList.size();
    }
}
