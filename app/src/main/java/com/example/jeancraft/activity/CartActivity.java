package com.example.jeancraft.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeancraft.Adapter.CartAdapter;
import com.example.jeancraft.Entity.Cart;
import com.example.jeancraft.R;
import com.example.jeancraft.service.CartAPI;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list (to be populated later)
        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartItemList);
        recyclerView.setAdapter(cartAdapter);

        // Replace "userId" with the actual user ID or fetch it dynamically as per your app's logic
        String userId = "123"; // Replace with actual user ID logic

        // Fetch cart items using CartAPI
        CartAPI cartAPI = new CartAPI(this);
        cartAPI.getCartItems(userId, new CartAPI.CartCallback() {
            @Override
            public void onCartLoaded(ArrayList<Cart> cartItems) {
                // Update UI with fetched cart items
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (cartItems != null) {
                            cartItemList.clear();
                            cartItemList.addAll(cartItems);
                            cartAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(CartActivity.this, "Failed to load cart items", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onError(String error) {
                Log.e("CartActivity", "Error loading cart items: " + error);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CartActivity.this, "Error loading cart items", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
