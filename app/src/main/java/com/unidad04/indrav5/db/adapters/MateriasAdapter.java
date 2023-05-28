package com.unidad04.indrav5.db.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.unidad04.indrav5.R;
import com.unidad04.indrav5.db.dao.MateriaDao;
import com.unidad04.indrav5.db.models.Materia;
import java.util.ArrayList;

public class MateriasAdapter extends ArrayAdapter<Materia> {

    private AppCompatActivity context;
    private int resource;
    private ArrayList<Materia> materias;

    public MateriasAdapter(AppCompatActivity context, ArrayList<Materia> materias) {
        super(context, R.layout.list_item_materia, materias);
        this.resource = R.layout.list_item_materia;
        this.context = context;
        this.materias = materias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource, null, false);

        Materia materia = materias.get(position);
        TextView txtMateriaInfo = view.findViewById(R.id.txtMateriaInfo);
        txtMateriaInfo.setText("Id: " + materia.getId() + ", Nomnbre: " + materia.getNombre());

        Button btnEliminarMateria = view.findViewById(R.id.btnEliminarMateria);

        /**
         * Agregando el evento para poder eliminar registros de tipo Materia
         */
        btnEliminarMateria.setOnClickListener(v -> {

            MateriaDao materiaDao = new MateriaDao(context);
            materiaDao.openConnection();
            materiaDao.delete(materias.get(position));
            materiaDao.closeConnection();

            remove(materias.get(position));
        });

        return view;
    }
}
