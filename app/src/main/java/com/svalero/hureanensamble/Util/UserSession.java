package com.svalero.hureanensamble.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    private static final String PREF_NAME = "user_session";
    private static final String USER_NAME = "user_name";
    private static final String USER_ROL = "user_rol";
   // private static final String USER_ID = "user_id";

    private SharedPreferences sharedPreferences; //para guardar los datos
    private SharedPreferences.Editor editor;

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(String name, String rol) {
        editor.putString(USER_NAME, name);
        editor.putString(USER_ROL, rol);
        //editor.putString(USER_ID, id);
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, null);
    }

    public String getUserRol() {
        return sharedPreferences.getString(USER_ROL, null);
    }

   // public static String getUserId() {
      //  return sharedPreferences.getString(USER_ID, null);
    //}

    public void clear() {
        editor.clear();
        editor.apply();
    }
}
