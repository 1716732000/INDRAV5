package com.unidad04.indrav5.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.unidad04.indrav5.db.DBHelper;
import java.util.ArrayList;

public abstract class BaseDao<T> {

    protected DBHelper dbHelper;
    protected SQLiteDatabase db;

    public BaseDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void openConnection() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeConnection() {
        db.close();
    }

    public abstract ArrayList<T> findAll();

    public abstract  ArrayList<T> findAll(String selection, String[] selectionArgs);

    public abstract T findById(int id);

    public abstract long insert(T model);

    public abstract long update(T model);

    public abstract long delete(T model);
}
