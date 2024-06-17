package com.example.jeancraft.service;

import android.content.Context;
import android.util.Log;

import com.example.jeancraft.Entity.Cart;
import com.example.jeancraft.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CartAPI {
    private Context context;

    public CartAPI(Context context) {
        this.context = context;
    }

    public interface CartCallback {
        void onCartLoaded(ArrayList<Cart> cartItems);
        void onError(String error);
    }

    public void getCartItems(String userId, CartCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.baseUrl + Constants.getCartForUserAPI + "?userId=" + userId)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ArrayList<Cart> cartItems = handleResponseArray(responseData);
                    callback.onCartLoaded(cartItems);
                } else {
                    callback.onError("Response not successful");
                }
            }
        });
    }

    private ArrayList<Cart> handleResponseArray(String jsonData) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String code = jsonObject.getString("code");
            if (code.equals("01")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject data = dataArray.getJSONObject(i);
                    String cartItemId = data.getString("cartItemId");
                    String productId = data.getString("productId");
                    String productName = data.getString("productName");
                    double price = data.getDouble("price");
                    int quantity = data.getInt("quantity");
                    Cart cartItem = new Cart(cartItemId, productId, productName, price, quantity);
                    cartItems.add(cartItem);
                }
                return cartItems;
            } else {
                throw new IOException("Invalid response code: " + code);
            }
        } catch (Exception e) {
            Log.e("handleResponse Error", e.toString());
            return null;
        }
    }
}
