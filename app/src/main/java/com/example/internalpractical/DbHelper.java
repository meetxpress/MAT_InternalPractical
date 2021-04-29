package com.example.internalpractical;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DBname = "Signup";

    public DbHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table tblEmployee (eid integer primary key autoincrement, ename text, eage integer, sal integer, edelp text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tblEmployee");
        onCreate(db);
    }

    public boolean insert(String e_name, String e_age, String e_sal, String e_add) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ename", e_name);
        cv.put("eage", e_age);
        cv.put("sal", e_sal);
        cv.put("edelp", e_add);
        long r = db.insert("tblEmployee", "null", cv);
        return r == -1 ? false : true;
    }

    public Cursor display(int empid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from tblEmployee where eid = " + empid, null);
        return cr;
    }

    public Cursor displayAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from tblEmployee", null);
        return cr;
    }

    public boolean update(int ud_eid, String ud_ename, int ud_eage, int ud_sal, String ud_add) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ename", ud_ename);
        cv.put("eage", ud_eage);
        cv.put("sal", ud_sal);
        cv.put("edelp", ud_add);
        Cursor cr = db.rawQuery("select * from tblEmployee where eid = " + ud_eid, null);
        if (cr.getCount() > 0) {
            long r = db.update("tblEmployee", cv, "eid=?", new String[]{String.valueOf(ud_eid)});
            return r == -1 ? false : true;
        } else {
            return false;
        }
    }

    public boolean delete(int ud_eid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from tblEmployee where eid = " + ud_eid, null);
        if (cr.getCount() > 0) {
            long r = db.delete("tblEmployee", "eid = ?", new String[]{String.valueOf(ud_eid)});
            return r == -1 ? false : true;
        } else {
            return false;
        }
    }
}

