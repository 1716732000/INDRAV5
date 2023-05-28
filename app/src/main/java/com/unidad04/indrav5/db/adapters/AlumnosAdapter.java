package com.unidad04.indrav5.db.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.unidad04.indrav5.R;
import com.unidad04.indrav5.db.dao.AlumnoDao;
import com.unidad04.indrav5.db.models.Alumno;
import java.util.ArrayList;

public class AlumnosAdapter extends ArrayAdapter<Alumno> {

    private AppCompatActivity context;
    private int resource;
    private ArrayList<Alumno> alumnos;

    public AlumnosAdapter(AppCompatActivity context, ArrayList<Alumno> alumnos) {
        super(context, R.layout.list_item_alumno, alumnos);
        this.context = context;
        this.resource = R.layout.list_item_alumno;
        this.alumnos = alumnos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource, null, false);

        Alumno alumno = alumnos.get(position);
        TextView txtAlumnoInfo = view.findViewById(R.id.txtAlumnoInfo);
        txtAlumnoInfo.setText("Id: " + alumno.getId() + ", Nombre: " + alumno.getNombre() + " " + alumno.getApellido());

        Button btnEliminarAlumno = view.findViewById(R.id.btnEliminarAlumno);

        /**
         * Agregando el evento para poder eliminar registros de tipo Alumno
         */
        btnEliminarAlumno.setOnClickListener(v -> {

            AlumnoDao alumnoDao = new AlumnoDao(context);
            alumnoDao.openConnection();
            alumnoDao.delete(alumnos.get(position));
            alumnoDao.closeConnection();

            remove(alumnos.get(position));

        });

        return view;
    }

}
