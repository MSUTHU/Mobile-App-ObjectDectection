package com.example.piedpiperd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManagement
{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    int mode =0;
    String Data="b" ;
    Context context;
    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,mode);
        editor = sharedPreferences.edit();
    }

    public  void secondTime()
    {
        editor.putBoolean(Data,true);
        editor.commit();
    }
    public void firstTime()
    {
        if (!this.login())
        {
            Intent intent = new Intent(context, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    private boolean login()
    {
        return sharedPreferences.getBoolean(Data, false);
    }


}
