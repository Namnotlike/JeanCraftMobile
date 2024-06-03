package com.example.jeancraft.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeancraft.Adapter.ProductAdapter;
import com.example.jeancraft.Entity.Product;
import com.example.jeancraft.Entity.User;
import com.example.jeancraft.R;
import com.example.jeancraft.service.ProductAPI;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    RecyclerView rvProducts;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_product);

        rvProducts = findViewById(R.id.rvProducts);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this,productList);
        rvProducts.setAdapter(productAdapter);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));

        ProductAPI productAPI = new ProductAPI(this);
        productAPI.performProductList(new ProductAPI.ProductListCallback() {
            @Override
            public void onProductListLoaded(ArrayList<Product> products) {
                runOnUiThread(() -> {
                    productList.clear();
                    productList.addAll(products);
                    productAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> Toast.makeText(ProductListActivity.this, "Error: " + error, Toast.LENGTH_LONG).show());
            }
        });
    }
}
