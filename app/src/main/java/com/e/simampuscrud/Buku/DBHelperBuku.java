package com.e.simampuscrud.Buku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DBHelperBuku extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "simampus.db";
    public static final String TABLE2_NAME = "tb_buku";

    public static final String COLUMN_4 = "_id";
    public static final String COLUMN_5 = "kode_buku";
    public static final String COLUMN_6 = "judul_buku";
    public static final String COLUMN_7 = "penulis";
    public static final String COLUMN_8 = "penerbit";
    public static final String COLUMN_9 = "tahun_terbit";
    public static final String COLUMN_10 = "jumlah_halaman";
    public static final String COLUMN_11 = "rak_buku";
    public static final String COLUMN_12 = "kategori";
    public static final String COLUMN_13 = "tanggal_masuk_buku";

    private SQLiteDatabase db;

    public DBHelperBuku(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE2_NAME + "(" + COLUMN_4 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_5 + " TEXT, " + COLUMN_6 + " TEXT, " + COLUMN_7 + " TEXT, "
                + COLUMN_8 + " TEXT, " + COLUMN_9 + " TEXT, " + COLUMN_10 + " TEXT, "
                + COLUMN_11 + " TEXT, " + COLUMN_12 + " TEXT, " + COLUMN_13 + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
    }

    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE2_NAME, null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE " + COLUMN_4 + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values){
        db.insert(TABLE2_NAME, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(TABLE2_NAME, values, COLUMN_4 + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(TABLE2_NAME, COLUMN_4 + "=" + id, null);
    }
}