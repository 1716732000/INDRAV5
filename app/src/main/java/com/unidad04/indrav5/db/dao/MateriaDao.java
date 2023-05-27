package com.unidad04.indrav5.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.unidad04.indrav5.db.DBHelper;
import com.unidad04.indrav5.db.models.Materia;
import java.util.ArrayList;

public class MateriaDao extends BaseDao<Materia>{

    public MateriaDao(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Materia> findAll() {
        return findAll(null, null);
    }

    @Override
    public ArrayList<Materia> findAll(String selection, String[] selectionArgs) {
        ArrayList<Materia> materias = new ArrayList<>();

        String[] columns = { "id", "nombre" };
        Cursor result = db.query(
                DBHelper.TBL_MATERIAS, columns, selection, selectionArgs, null, null, null
        );

        if (result.getCount() != 0) {
            do {
                result.moveToNext();
                materias.add(new Materia(result.getInt(0), result.getString(1)));
            } while (!result.isLast());
        }
        result.close();

        return materias;
    }

    @Override
    public Materia findById(int id) {
        String[] args = { String.valueOf(id) };
        ArrayList<Materia> materias = findAll("id=?", args);

        if (materias.size() != 0) {
            return materias.get(0);
        }

        return null;
    }

    @Override
    public long insert(Materia materia) {
        ContentValues values = new ContentValues();
        values.put("nombre", materia.getNombre());

        return db.insert(DBHelper.TBL_MATERIAS, null, values);
    }

    @Override
    public long update(Materia materia) {
        ContentValues values = new ContentValues();
        values.put("nombre", materia.getNombre());

        String[] args = { String.valueOf(materia.getId()) };
        return db.update(DBHelper.TBL_MATERIAS, values, "id=?", args);
    }

    @Override
    public long delete(Materia materia) {
        String[] args = { String.valueOf(materia.getId()) };
        return db.delete(DBHelper.TBL_MATERIAS, "id=?", args);
    }
}
