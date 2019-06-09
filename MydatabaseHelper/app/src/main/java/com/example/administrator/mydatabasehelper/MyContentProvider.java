package com.example.administrator.mydatabasehelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public  static final int contacts_DIR = 0;
    public  static final int contacts_ITEM = 1;
    private static UriMatcher uriMatcher;
    private static final  String AUTHORITY ="com.example.administrator.mydatabasehelper.provider";
    private DatabaseHlper databaseHlper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.administrator.sy9","table1",contacts_DIR);
        uriMatcher.addURI("com.example.administrator.sy9","table1/#",contacts_ITEM);

    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db=databaseHlper.getWritableDatabase();
        int deletedRows=0;
        switch (uriMatcher.match(uri)){
            case contacts_DIR:
                deletedRows=db.delete("contacts",selection,selectionArgs);
                break;
            case contacts_ITEM:
                String contactsId=uri.getPathSegments().get(1);
                deletedRows=db.delete("contacts","id=?",new String[]{contactsId});
                break;
            default:
                break;
        }
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case contacts_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.mydatabasehelper.provider.contacts";
            case contacts_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.mydatabasehelper.provider.contacts";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db=databaseHlper.getWritableDatabase();
        Uri uriReturn=null;
        switch(uriMatcher.match(uri)){
            case contacts_DIR:
            case  contacts_ITEM:
                long newContactsId=db.insert("contacts",null,values);
                uriReturn=Uri.parse("content://"+AUTHORITY+"/book/"+newContactsId);
                break;
            default:
                break;
        }
        return uriReturn;

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        databaseHlper=new DatabaseHlper(getContext(),"Contacts0.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=databaseHlper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case contacts_DIR:
                cursor=db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case contacts_ITEM:
                String contactsId=uri.getPathSegments().get(1);
                cursor=db.query("contacts",projection,"id=?",new String[]{contactsId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
       SQLiteDatabase db=databaseHlper.getWritableDatabase();
        int updatedRows=0;
        switch(uriMatcher.match(uri)){
            case contacts_DIR:
                updatedRows=db.update("contacts",values,selection,selectionArgs);
                break;
            case contacts_ITEM:
                String contactsId=uri.getPathSegments().get(1);
                updatedRows=db.update("contacts",values,"id=?",new String[]{contactsId});
                break;
            default:
                break;
        }
        return updatedRows;
    }
}
