package com.unidad04.indrav5.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.unidad04.indrav5.db.DBHelper;
import com.unidad04.indrav5.db.models.Nivel;
import java.util.ArrayList;

public class NivelDao extends BaseDao<Nivel> {

    public NivelDao(Context context) {
        super(context);
    }

    public ArrayList<Nivel> findAll() {
        return findAll(null, null);
    }

    public ArrayList<Nivel> findAll(String selection, String[] selectionArgs) {
        ArrayList<Nivel> niveles = new ArrayList<>();

        String[] columns = {"id", "nombre"};
        Cursor results = db.query(
                DBHelper.TBL_NIVELES, columns, selection, selectionArgs, null, null,null
        );

        if (results.getCount() != 0) {
            do {
                results.moveToNext();
                niveles.add(new Nivel(results.getInt(0), results.getString(1)));
            } while (!results.isLast());
        }
        results.close();

        return niveles;
    }

    public Nivel findById(int id) {
        String[] args = { String.valueOf(id) };
        ArrayList<Nivel> niveles = findAll("id=?", args);

        if (niveles.size() != 0) {
            return niveles.get(0);
        }

        return null;
    }

    public long insert(Nivel nivel) {
        ContentValues values = new ContentValues();
        values.put("nombre", nivel.getNombre());

        return db.insert(DBHelper.TBL_NIVELES, null, values);
    }

    public long update(Nivel nivel) {
        ContentValues values = new ContentValues();
        values.put("nombre", nivel.getNombre());

        String[] args = { String.valueOf(nivel.getId()) };
        return db.update(DBHelper.TBL_NIVELES, values, "id=?", args);
    }

    public long delete(Nivel nivel) {
        String[] args = { String.valueOf(nivel.getId()) };
        return db.delete(DBHelper.TBL_NIVELES, "id=?", args);
    }

}
