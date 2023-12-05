package com.example.group4_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserManagement {
    Context c;
    SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public static final String pref_name ="User_login";
    public static final String login ="is_User_login";
    public static final String names ="name_customer";
    public static final String emails ="email";
    public static final String phones ="phone";
    public UserManagement(Context c){
        this.c = c;
        sp = c.getSharedPreferences(pref_name,Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public boolean isUserlog(){
        return sp.getBoolean(login,true);
    }

    public void userSessions(String name, String email, String phone){
        editor.putBoolean(login,true);
        editor.putString(names,name);
        editor.putString(emails,email);
        editor.putString(phones,phone);
        editor.apply();
    }
    public void checkLog(){
        if(this.isUserlog()){
            Intent i = new Intent(c, login_page.class);
            c.startActivity(i);
            ((ProfileActivity)c).finish();
        }
    }
   public HashMap<String, String> userDetails(){
    HashMap<String,String> u = new HashMap<>();
    u.put(names, sp.getString(names, ""));
    u.put(emails, sp.getString(emails, ""));
    u.put(phones, sp.getString(phones, ""));
    return u;
}
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(c, login_page.class);
        c.startActivity(i);
        ((ProfileActivity)c).finish();
    }
}
