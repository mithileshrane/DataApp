package com.example.samrans.demodbsearchviewofflineonlinejson.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Samrans on 12-12-2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    private String TAG="DBHelper";
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "alerts";
//    public static final String CONTACTS_COLUMN_ID = "id";
//    public static final String CONTACTS_COLUMN_NAME = "fname";
//    public static final String CONTACTS_COLUMN_IMAGE = "image";
//    public static final String CONTACTS_COLUMN_TIME = "time";
//    public static final String CONTACTS_COLUMN_OFFER = "offer";
//    public static final String CONTACTS_COLUMN_OFFERID = "offer_id";
//    public static final String CONTACTS_COLUMN_RE_ID = "re_id";
//    public static final String CONTACTS_COLUMN_UNREAD = "unread";
//    public static final String CONTACTS_COLUMN_PHONE = "phone";
//    public static final String CONTACTS_COLUMN_TYPE = "type";
//
//
//    public static final String COLUMN_PROFILE_IMG = "profile_pic";
//    public static final String COLUMN_FNAME = "fname";
//    public static final String COLUMN_TIME = "time";
//    public static final String COLUMN_MESSAGE = "message";
//    public static final String COLUMN_INTENT_DATA = "intent_data";
//    public static final String COLUMN_INTENT_DATA_EXTRA = "intent_data_extra";
//    public static final String COLUMN_UNREAD = "unread";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_USER_ID = "user_id";

    public static final String COLUMN_CATEGORY="category";
    public static final String COLUMN_PRICE="price";

    public static final String COLUMN_INSTRUCTIONS="instructions";
    public static final String COLUMN_PHOTO="photo";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_PRODUCTID="productID";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_PRODUCTID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_PRICE + " TEXT,"
                + COLUMN_PHOTO + " TEXT,"
                + COLUMN_INSTRUCTIONS + " TEXT" + ")";
        try{
            db.execSQL(CREATE_CONTACTS_TABLE);
        }
        catch (Exception e)
        {
            Log.d(TAG,e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
