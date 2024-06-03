package com.example.jeancraft.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jeancraft.Entity.Product;
import com.example.jeancraft.Utils.Constants;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProductAPI {
    private Context context;

    public ProductAPI(Context context) {
        this.context = context;
    }

    public interface ProductListCallback {
        void onProductListLoaded(ArrayList<Product> products);
        void onError(String error);
    }

    public interface ProductDetailCallback {
        void onProductDetailLoaded(Product product);
        void onError(String error);
    }

    public void performProductList(ProductListCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.baseUrl + Constants.listProductAPI)
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
                    ArrayList<Product> list = handleResponseArray(responseData);
                    callback.onProductListLoaded(list);
                } else {
                    callback.onError("Response not successful");
                }
            }
        });
    }

    public void performProductDetail(ProductDetailCallback callback ,String productId) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.baseUrl + Constants.getProductByIdAPI + productId)
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
                    Product product = handleResponse(responseData);
                    callback.onProductDetailLoaded(product);
                } else {
                    callback.onError("Response not successful");
                }
            }
        });
    }

    private ArrayList<Product> handleResponseArray(String jsonData) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String code = jsonObject.getString("code");
            if (code.equals("01")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject data = (JSONObject) dataArray.get(i);
                    String productID = data.getString("productId");
                    String image = data.getString("image");
                    double price = data.getDouble("price");
                    double size = data.getDouble("size");
                    int quantity = data.optInt("quantity", 1);
                    Product product = new Product(productID, image, price, size, quantity);
                    list.add(product);
                }
                return list;
            } else {
                throw new IOException("Invalid response code: " + code);
            }
        } catch (Exception e) {
            Log.e("handleResponse Error", e.toString());
            return null;
        }
    }
    private Product handleResponse(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String code = jsonObject.getString("code");
            String message = jsonObject.getString("message");
            if (code.equals("01")) {
                JSONObject data = jsonObject.getJSONObject("data");
                    String productID = data.getString("productId");
                    String image = data.getString("image");
                    double price = data.getDouble("price");
                    double size = data.getDouble("size");
                    int quantity = data.optInt("quantity", 1);
                    Product product = new Product(productID, image, price, size, quantity);
                return product;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("handleResponse Error", e.toString());
            return null;
        }
    }
}
