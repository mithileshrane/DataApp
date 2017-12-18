package com.example.samrans.demodbsearchviewofflineonlinejson;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.samrans.demodbsearchviewofflineonlinejson.utils.ConnectivityReceiver;
import com.example.samrans.demodbsearchviewofflineonlinejson.utils.DBHelper;
import com.example.samrans.demodbsearchviewofflineonlinejson.utils.MyPreferenceManager;

import static com.example.samrans.demodbsearchviewofflineonlinejson.utils.Constants.SHARED_PREF;

/**
 * Created by Samrans on 12-12-2017.
 */

public class AppFile extends MultiDexApplication {
    private static AppFile mInstance;
    private static DBHelper mydb ;
    private static MyPreferenceManager myPrefs;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mydb=new DBHelper(this);
        myPrefs=new MyPreferenceManager(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppFile getInstance() {
        return mInstance;
    }

    public DBHelper getMydb() {
        return mydb;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public MyPreferenceManager getPrefManager() {
        if (myPrefs == null) {
            myPrefs = new MyPreferenceManager(this);
        }
        return myPrefs;
    }

}
