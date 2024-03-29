package com.example.administrator.sy9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2019/6/8.
 */

public class MyProvider extends ContentProvider {
    public  static final int TABLE1_DIR = 0;
    public  static final int TABLE1_ITEM = 1;
    public  static final int TABLE2_DIR = 2;
    public  static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.administrator.sy9","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.administrator.sy9","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.example.administrator.sy9","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.administrator.sy9","table2/#",TABLE2_ITEM);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                break;
            case TABLE1_ITEM:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.sy9.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.sy9.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.sy9.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.sy9.table2";
            default:
                break;

        }
        return null;
    }

}
