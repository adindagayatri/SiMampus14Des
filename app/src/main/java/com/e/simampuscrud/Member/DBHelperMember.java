package com.e.simampuscrud.Member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperMember extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "simampusmember.db";
    public static final String TABLE3_NAME = "tb_member";

    public static final String COLUMN_4 = "_id";
    public static final String COLUMN_5 = "kode_member";
    public static final String COLUMN_6 = "nama_member";
    public static final String COLUMN_7 = "tempat_lahir";
    public static final String COLUMN_8 = "tanggal_lahir";
    public static final String COLUMN_9 = "jenis_kelamin";
    public static final String COLUMN_10 = "alamat";
    public static final String COLUMN_11 = "no_telepon";

    private SQLiteDatabase db;

    public DBHelperMember(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE3_NAME + "(" + COLUMN_4 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_5 + " TEXT, " + COLUMN_6 + " TEXT, " + COLUMN_7 + " TEXT, "
                + COLUMN_8 + " TEXT, " + COLUMN_9 + " TEXT, " + COLUMN_10 + " TEXT, "
                + COLUMN_11 + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
    }

    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE3_NAME, null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE3_NAME + " WHERE " + COLUMN_4 + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values){
        db.insert(TABLE3_NAME, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(TABLE3_NAME, values, COLUMN_4 + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(TABLE3_NAME, COLUMN_4 + "=" + id, null);
    }
}