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
import com.example.makeupstudio.model.Product;
import com.example.makeupstudio.viewholder.ProductViewHolder;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.makeUpProductName.setText(product.getProduct_name());
        Glide.with(context).load(product.getProduct_image()).placeholder(R.drawable.imagehint).into(holder.makeUpProductImg);

        String productImg = product.getProduct_image();
        String productName = product.getProduct_name();
        String productDescription = product.getProduct_description();

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContainerActivity.class);
            intent.putExtra("product", "product");
            intent.putExtra("productImg", productImg);
            intent.putExtra("productName", productName);
            intent.putExtra("productDescription", productDescription);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
