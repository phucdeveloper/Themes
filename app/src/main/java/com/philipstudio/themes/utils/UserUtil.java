package com.philipstudio.themes.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserUtil {
    private SharedPreferences preferences;

    public UserUtil(Context context){
        preferences = context.getSharedPreferences("idUser", Context.MODE_PRIVATE);
    }

    public void setUserUtil(String id){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUser", id);
        editor.apply();
    }

    public String getUserUtil(){
        String idUser = preferences.getString("idUser", null);
        return idUser;
    }
}
