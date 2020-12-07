package com.e.simampuscrud.Peminjaman;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DBHelperPeminjaman extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "db_peminjaman";
    public static final String TABLE_SQLite = "tb_peminjaman";
    public static final String COLOUMN_ID = "id";
    public static final String COLOUMN_NAME = "nama_peminjam";
    public static final String COLOUMN_JUDUL = "judul_buku";
    public static final String COLOUMN_TGL = "tgl_pinjam";
    public static final String COLOUMN_STATUS = "status";
    public DBHelperPeminjaman (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE "+ TABLE_SQLite + " ("+ COLOUMN_ID +
                " INTEGER PRIMARY KEY autoincrement, " + COLOUMN_NAME + " TEXT NOT NULL," + COLOUMN_JUDUL +
                " TEXT NOT NULL," + COLOUMN_TGL + " TEXT NOT NULL," + COLOUMN_STATUS + " TEXT NOT NULL" + ")";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLOUMN_ID, cursor.getString(0));
                map.put(COLOUMN_NAME, cursor.getString(1));
                map.put(COLOUMN_JUDUL, cursor.getString(2));
                map.put(COLOUMN_TGL, cursor.getString(3));
                map.put(COLOUMN_STATUS, cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite", ""+ wordList);
        database.close();
        return wordList;
    }

    public void insert (String nama_peminjam, String judul_buku, String tgl_pinjam, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (nama_peminjam, judul_buku, tgl_pinjam, status)"+
                " VALUES ('"+nama_peminjam+"', '"+judul_buku+"', '"+tgl_pinjam+"', '"+status+"')";

        Log.e("insert sqlite", ""+queryValues);
        database.execSQL(queryValues);
        database.close();
    }


    public void update (int id, String nama_peminjam, String judul_buku, String tgl_pinjam, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_SQLite + " SET " + COLOUMN_NAME + "='" + nama_peminjam +
                "', "+ COLOUMN_JUDUL + "='" + judul_buku + "', " + COLOUMN_TGL + "='" + tgl_pinjam +
                "', " + COLOUMN_STATUS + "='" + status + "'" + " WHERE " + COLOUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLOUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


}