package com.example.administrator.mydatabasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/6/8.
 */

public class DatabaseHlper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHlper";
    public static final String CREATE_contacts="create table contacts (" +
            "id integer primary key autoincrement," +
            "name text," +
            "telephone integer" +
            "sex text)";
    private Context mContext;
    public DatabaseHlper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_contacts);
        Log.d(TAG, "onCreate:创建成功 ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }
}

