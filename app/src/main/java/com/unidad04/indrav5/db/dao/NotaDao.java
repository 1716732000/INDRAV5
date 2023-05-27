package com.unidad04.indrav5.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.unidad04.indrav5.db.DBHelper;
import com.unidad04.indrav5.db.models.Nota;
import java.util.ArrayList;

public class NotaDao extends BaseDao<Nota> {

    private Context context;

    public NotaDao(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public ArrayList<Nota> findAll() {
        return findAll(null, null);
    }

    @Override
    public ArrayList<Nota> findAll(String selection, String[] selectionArgs) {
        ArrayList<Nota> notas = new ArrayList<>();

        String[] columns = { "id", "alumno_id", "nivel_id", "materia_id", "nota1", "nota2", "nota3", "nota4", "promedio" };
        Cursor result = db.query(
                DBHelper.TBL_NOTAS, columns, selection, selectionArgs, null, null, null
        );

        AlumnoDao alumnoDao = new AlumnoDao(context);
        MateriaDao materiaDao = new MateriaDao(context);
        NivelDao nivelDao = new NivelDao(context);

        if (result.getCount() != 0) {
            do {
                result.moveToNext();
                notas.add(new Nota(
                        result.getInt(0),
                        alumnoDao.findById(result.getInt(1)),
                        nivelDao.findById(result.getInt(2)),
                        materiaDao.findById(result.getInt(3)),
                        result.getDouble(4),
                        result.getDouble(5),
                        result.getDouble(6),
                        result.getDouble(7),
                        result.getDouble(8)
                ));
            } while(!result.isLast());

        }
        result.close();

        return notas;
    }

    @Override
    public Nota findById(int id) {
        String[] args = {String.valueOf(id) };
        ArrayList<Nota> notas = findAll("id=?", args);

        if (notas.size() != 0) {
            return notas.get(0);
        }

        return null;
    }

    protected ContentValues getValues(Nota nota) {
        ContentValues values = new ContentValues();
        values.put("alumno_id", nota.getAlumno().getId());
        values.put("nivel_id", nota.getNivel().getId());
        values.put("materia_id", nota.getMateria().getId());
        values.put("nota1", nota.getNota1());
        values.put("nota2", nota.getNota2());
        values.put("nota3", nota.getNota3());
        values.put("nota4", nota.getNota4());
        values.put("promedio", nota.getPromedio());
        return values;
    }

    @Override
    public long insert(Nota nota) {
        ContentValues values = getValues(nota);
        return db.insert(DBHelper.TBL_NOTAS, null, values);
    }

    @Override
    public long update(Nota nota) {
        ContentValues values = getValues(nota);
        String[] args = { String.valueOf(nota.getId()) };
        return db.update(DBHelper.TBL_NOTAS, values, "id=?", args);
    }

    @Override
    public long delete(Nota nota) {
        String[] args = { String.valueOf(nota.getId()) };
        return db.delete(DBHelper.TBL_NOTAS, "id=?", args);
    }
}