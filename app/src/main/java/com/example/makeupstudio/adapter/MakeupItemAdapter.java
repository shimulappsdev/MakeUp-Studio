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
import com.example.makeupstudio.model.MakeupItem;
import com.example.makeupstudio.viewholder.MakeupItemViewHolder;

import java.util.List;

public class MakeupItemAdapter extends RecyclerView.Adapter<MakeupItemViewHolder> {

    private Context context;
    private List<MakeupItem> makeupItemList;

    public MakeupItemAdapter(Context context, List<MakeupItem> makeupItemList) {
        this.context = context;
        this.makeupItemList = makeupItemList;
    }

    @NonNull
    @Override
    public MakeupItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MakeupItemViewHolder(LayoutInflater.from(context).inflate(R.layout.makeup_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MakeupItemViewHolder holder, int position) {

        MakeupItem makeupItem = makeupItemList.get(position);
        holder.makeupItemName.setText(makeupItem.getMakeupItem_name());
        holder.makeupItemAbout.setText(makeupItem.getMakeupItem_about());
        Glide.with(context).load(makeupItem.getMakeupItem_image()).placeholder(R.drawable.imagehint).into(holder.makeupItemImg);

        String categoryId = makeupItem.getCategory_id();
        String makeupItemId = makeupItem.getMakeupItem_id();

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContainerActivity.class);
            intent.putExtra("details", "details");
            intent.putExtra("categoryId", categoryId);
            intent.putExtra("makeupItemId", makeupItemId);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return makeupItemList.size();
    }
}
