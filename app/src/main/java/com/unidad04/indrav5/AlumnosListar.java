package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.unidad04.indrav5.db.adapters.AlumnosAdapter;
import com.unidad04.indrav5.db.dao.AlumnoDao;
import com.unidad04.indrav5.db.models.Alumno;
import java.util.ArrayList;

public class AlumnosListar extends AppCompatActivity {

    private ListView lvAlumnos;
    private AlumnoDao alumnoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_listar);

        alumnoDao = new AlumnoDao(this);
        lvAlumnos = findViewById(R.id.lvAlumnos);

        /**
         * Se agregan los eventos a los botones de navegacion
         */
        Button btnCreateAlumno = findViewById(R.id.btnCreateAlumno);
        btnCreateAlumno.setOnClickListener(this::openAlumnoManagement);

        Button btnExitAlumnoListar = findViewById(R.id.btnExitAlumnoListar);
        btnExitAlumnoListar.setOnClickListener(this::closeAlumnosListar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        alumnoDao.openConnection();
        ArrayList<Alumno> alumnos = alumnoDao.findAll();
        alumnoDao.closeConnection();

        lvAlumnos.setAdapter(new AlumnosAdapter(this, alumnos));
    }

    protected void openAlumnoManagement(View view) {
        Intent intent = new Intent(this, Alumnos.class);
        startActivity(intent);
    }

    protected void closeAlumnosListar(View view) {
        finish();
    }
}