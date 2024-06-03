package com.example.jeancraft.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jeancraft.Entity.Product;
import com.example.jeancraft.R;
import com.example.jeancraft.service.ProductAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView quantity, price, size, id;
    private Button addToCart;
    private ImageView image;
    Product productDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        quantity = findViewById(R.id.txtQuantity);
        price = findViewById(R.id.txtPrice);
        size = findViewById(R.id.txtSize);
        id = findViewById(R.id.txtId);
        addToCart = findViewById(R.id.btnAddToCart);
        image = findViewById(R.id.imgProductDetail);
        // Get product ID from intent
        String productId = getIntent().getStringExtra("product_id");

        // Fetch product details from the database or API call using the product ID
        // Display product details on the views
        ProductAPI productAPI = new ProductAPI(this);
        productDetail = new Product();
        productAPI.performProductDetail(new ProductAPI.ProductDetailCallback() {
            @Override
            public void onProductDetailLoaded(Product product) {
                runOnUiThread(() -> updateUI(product));
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> Toast.makeText(ProductDetailActivity.this, "Error: " + error, Toast.LENGTH_LONG).show());
            }
        }, productId);
    }
    private void updateUI(Product product) {
        Picasso.get()
                .load("https://cdn.dribbble.com/userupload/3276321/file/original-aa1026bc4e3a6601e8a77436468aa285.png?resize=1905x1429")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(image);

        quantity.setText(String.valueOf(product.getQuantity()));
        price.setText(String.format("%s TL", product.getPrice()));
        size.setText(String.format("%s cm", product.getSize()));
        id.setText(product.getId());
    }
}
