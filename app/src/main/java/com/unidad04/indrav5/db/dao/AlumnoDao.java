package com.unidad04.indrav5.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.unidad04.indrav5.db.DBHelper;
import com.unidad04.indrav5.db.models.Alumno;
import java.util.ArrayList;

public class AlumnoDao extends BaseDao<Alumno> {

    public AlumnoDao(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Alumno> findAll() {
        return findAll(null, null);
    }

    @Override
    public ArrayList<Alumno> findAll(String selection, String[] selectionArgs) {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        String[] columns = {"id", "nombre", "apellido"};
        Cursor result = db.query(
                DBHelper.TBL_ALUMNOS, columns, selection, selectionArgs, null, null, null
        );

        if (result.getCount() != 0) {
            do {
                result.moveToNext();
                alumnos.add(new Alumno(result.getInt(0), result.getString(1), result.getString(2)));
            } while (!result.isLast());
        }

        result.close();
        return alumnos;
    }

    @Override
    public Alumno findById(int id) {

        String selection = "id=?";
        String[] args = { String.valueOf(id) };
        ArrayList<Alumno> alumnos = findAll(selection, args);

        if (alumnos.size() != 0) {
            return alumnos.get(0);
        }

        return null;
    }

    @Override
    public long insert(Alumno alumno) {
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("apellido", alumno.getApellido());

        return db.insert(DBHelper.TBL_ALUMNOS, null, values);
    }

    @Override
    public long update(Alumno alumno) {
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("apellido", alumno.getApellido());

        String[] args = { String.valueOf(alumno.getId()) };
        return db.update(DBHelper.TBL_ALUMNOS, values, "id=?", args);
    }

    @Override
    public long delete(Alumno alumno) {
        String[] args = { String.valueOf(alumno.getId()) };
        return db.delete(DBHelper.TBL_ALUMNOS, "id=?", args);
    }

}