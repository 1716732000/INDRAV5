package com.unidad04.indrav5.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "indra.db";
    private static final int DB_VERSION = 1;
    public static final String TBL_NIVELES = "niveles";
    public static final String TBL_MATERIAS = "materias";
    public static final String TBL_ALUMNOS = "alumnos";
    public static final String TBL_NOTAS = "notas";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TBL_NIVELES +"(" +
                "id integer primary key autoincrement," +
                "nombre text)");

        db.execSQL("create table "+ TBL_MATERIAS +"(" +
                "id integer primary key autoincrement," +
                "nombre text)");

        db.execSQL("create table "+TBL_ALUMNOS + "(" +
                "id integer primary key autoincrement," +
                "nombre text," +
                "apellido text)");

        db.execSQL("create table "+TBL_NOTAS + "(" +
                "id integer primary key autoincrement," +
                "alumno_id integer not null," +
                "nivel_id integer not null," +
                "materia_id integer not null," +
                "nota1 real," +
                "nota2 real," +
                "nota3 real," +
                "nota4 real," +
                "promedio real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
