package com.example.samrans.demodbsearchviewofflineonlinejson.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.samrans.demodbsearchviewofflineonlinejson.AppFile;

/**
 * Created by Samrans on 12-12-2017.
 */

public class MyPreferenceManager {
    private static final String PREF_NAME = "mypref";
    private final Context mContext;
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    private static final String KEY_FIRSTTIME = "firsttime" ;


    public MyPreferenceManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public boolean getCountDetail()
    {
        return pref.getBoolean(KEY_FIRSTTIME,false);
    }

    public void addCountDetail(boolean value){
        editor.putBoolean(KEY_FIRSTTIME,value);
        editor.commit();
    }
}
