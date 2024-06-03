package com.example.jeancraft.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jeancraft.Entity.User;
import com.google.gson.Gson;

public class Share {
    private static final String PREFS_FILE = "AppPrefs";
    private static final String USER_KEY = "User";

    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        editor.putString(USER_KEY, jsonString);
        editor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(USER_KEY, null);
        if (jsonString == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(jsonString, User.class);
    }
}
