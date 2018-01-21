package com.ali.zakatcount.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ali.zakatcount.MainActivity;

import java.util.ArrayList;

/**
 * Created by ALI on 21/01/2018.
 */

public class Db_Helper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "zakatdata";
    private final  static  int DATABASE_VERSION = 1;

    private final static String TABEL_ZF = "tbzf";

    private final static String FITRAH_ID = "id";
    private final static String COL_NAMAKEPKEL = "namaKK";
    private final static String COL_JUMLAHANGKEL = "jumlahAngkel";
    private final static String COL_TOTALZAKATFITRAH = "totalzakatfitrah";

    public Db_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABEL_ZF+"("
                +FITRAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +COL_JUMLAHANGKEL+" INTEGER,"
        +COL_TOTALZAKATFITRAH+" INTEGER,"
        +COL_NAMAKEPKEL+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABEL_ZF);
        onCreate(sqLiteDatabase);
    }
    public void saveDataZF(Mdl_ZF mdl_zf){
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_JUMLAHANGKEL, mdl_zf.getJumlahKel());
        contentValues.put(COL_TOTALZAKATFITRAH, mdl_zf.getTotalZakat());
        contentValues.put(COL_NAMAKEPKEL, mdl_zf.getNamaKK());
        sqlitedatabase.insert(TABEL_ZF,null,contentValues);
        sqlitedatabase.close();
    }
    public ArrayList<Mdl_ZF> getAlldataFitr(){
        SQLiteDatabase sqlitedatabase = this.getReadableDatabase();
        String [] allColums ={
                FITRAH_ID,
                COL_JUMLAHANGKEL,
                COL_TOTALZAKATFITRAH,
                COL_NAMAKEPKEL
        };
        Cursor cursor = sqlitedatabase.query(TABEL_ZF, allColums,
                null, null, null, null, null);
        ArrayList<Mdl_ZF> tempKembalian = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id= cursor.getInt(0);
                int jumlahKel = cursor.getInt(1);
                int totalZakat = cursor.getInt(2);
                String namaKK =cursor.getString(3);

                Mdl_ZF mdl_zf = new Mdl_ZF();
                mdl_zf.setId(id);
                mdl_zf.setJumlahKel(jumlahKel);
                mdl_zf.setTotalZakat(totalZakat);
                mdl_zf.setNamaKK(namaKK);
                tempKembalian.add(mdl_zf);
                cursor.moveToNext();

            }
        }
        sqlitedatabase.close();
        return tempKembalian;
    }
    public void deleteDataZfitr(Mdl_ZF mdl_zf){
        int id = mdl_zf.getId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABEL_ZF, FITRAH_ID+"="+id, null);
        /*sqLiteDatabase.delete(TABEL_ZF,FITRAH_ID+"=?",
                new String []{String.valueOf(id)});*/
        sqLiteDatabase.close();
    }
}
