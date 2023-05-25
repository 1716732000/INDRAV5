package com.unidad04.indrav5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {
       BaseDatos.execSQL("create table tblNiveles(" +
                //"idNivel int primary key autoincrement," +
                "codNivel text," +
                "nomNivel text)");

        BaseDatos.execSQL("create table tblMaterias(" +
                //"idMateria int primary key autoincrement," +
                "codMateria text," +
                "nomMateria text)");

        BaseDatos.execSQL("create table tblAlumnos(" +
                //"idAlumno int primary key autoincrement," +
                "codAlumno text," +
                "nomAlumno text," +
                "apeAlumno text)");

        BaseDatos.execSQL("create table tblNotas(" +
                //"idNotas int primary key autoincrement," +
                "codAlumno text," +
                "codNivel text," +
                "codMateria text," +
                "notP1 real," +
                "notP2 real," +
                "notP3 real," +
                "notP4 real," +
                "notPr real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
