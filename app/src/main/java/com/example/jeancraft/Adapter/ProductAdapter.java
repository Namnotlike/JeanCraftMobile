package com.example.jeancraft.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeancraft.Entity.Product;
import com.example.jeancraft.R;
import com.example.jeancraft.activity.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context,ArrayList<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater
                .inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        Picasso.get()
                .load("https://cdn.dribbble.com/userupload/3276321/file/original-aa1026bc4e3a6601e8a77436468aa285.png?resize=1905x1429")
                .placeholder(R.drawable.ic_launcher_background) // Hình ảnh tạm thời khi tải
                .error(R.drawable.ic_launcher_foreground)       // Hình ảnh hiển thị khi có lỗi
                .into(holder.imgProduct);
        holder.tvPrice.setText("Price: " + product.getPrice());
        holder.tvSize.setText("Size: " + product.getSize());
        holder.tvQuantity.setText("Quantity: " + product.getQuantity());
        holder.tvId.setText(product.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product_id", product.getId()); // Passing product ID to detail activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProduct;
        public TextView tvPrice;
        public TextView tvSize;
        public TextView tvQuantity;
        public TextView tvId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);;
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvSize = itemView.findViewById(R.id.tv_size);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvId = itemView.findViewById(R.id.tv_id);
        }
    }
}
