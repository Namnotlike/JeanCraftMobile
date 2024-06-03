package com.example.jeancraft.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.jeancraft.Entity.User;
import com.example.jeancraft.Utils.Constants;
import com.example.jeancraft.Utils.Share;
import com.example.jeancraft.activity.ProductListActivity;

import org.json.JSONObject;

import java.io.IOException;
import okhttp3.*;
public class UserAPI {
    private Context context;
    public UserAPI(Context context) {
        this.context = context;
    }

    public void performLogin(String email, String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("Email", email)
                            .addFormDataPart("Password", password)
                            .build();

                    Request request = new Request.Builder()
                            .url(Constants.baseUrl + Constants.loginAPI)
                            .post(requestBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace(); // Handle the error
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String responseData = response.body().string();
                                handleResponse(responseData);
                            } else {

                            }
                        }
                    });

                } catch (Exception e) {
                    Log.e("Network Error", e.toString());
                }
            }
        });
        thread.start();
    }

    private void handleResponse(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String code = jsonObject.getString("code");
            String message = jsonObject.getString("message");

            // Kiểm tra nếu mã phản hồi là thành công
            if (code.equals("01")) {
                JSONObject data = jsonObject.getJSONObject("data");
                String userID = data.getString("userID");
                String fullName = data.getString("fullName");
                String email = data.getString("email");
                // phoneNumber có thể null, cần xử lý trường hợp này
                String phoneNumber = data.isNull("phoneNumber") ? null : data.getString("phoneNumber");
                String role = data.getString("role");
                String token = data.getString("token");
                User user = new User(userID, fullName, role, email, phoneNumber, token);
                // Sử dụng các giá trị ở đây, ví dụ lưu token vào SharedPreferences hoặc hiển thị thông tin người dùng
                Share.saveUser(this.context, user);
                updateUI(fullName, email);
                Intent intent = new Intent(context, ProductListActivity.class);
                this.context.startActivity(intent);
            } else {
                // Xử lý lỗi
                handleError(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi phân tích JSON
            handleError(e.getMessage());
        }
    }
    private void updateUI(String fullName, String email) {

    }

    private void handleError(String errorMessage) {
        Toast.makeText(context, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
    }

}
