package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.unidad04.indrav5.db.adapters.MateriasAdapter;
import com.unidad04.indrav5.db.dao.MateriaDao;
import com.unidad04.indrav5.db.models.Materia;
import java.util.ArrayList;

public class MateriasListar extends AppCompatActivity {

    private ListView lvMaterias;
    private MateriaDao materiaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias_listar);

        lvMaterias = findViewById(R.id.lvMaterias);
        materiaDao = new MateriaDao(this);

        /**
         * Se agregan los eventos a los botones de navegacion
         */
        Button btnCreateMateria = findViewById(R.id.btnCreateMateria);
        btnCreateMateria.setOnClickListener(this::openMateriaManagement);

        Button btnExitMateriaListar = findViewById(R.id.btnExitMateriaListar);
        btnExitMateriaListar.setOnClickListener(this::closeMateriasListar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        materiaDao.openConnection();
        ArrayList<Materia> materias = materiaDao.findAll();
        materiaDao.closeConnection();

        lvMaterias.setAdapter(new MateriasAdapter(this, materias));
    }

    protected void openMateriaManagement(View view) {
        Intent intent = new Intent(this, Materias.class);
        startActivity(intent);
    }

    protected void closeMateriasListar(View view) {
        finish();
    }
}