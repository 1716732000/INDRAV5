package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.unidad04.indrav5.db.adapters.NivelesAdapter;
import com.unidad04.indrav5.db.dao.NivelDao;
import com.unidad04.indrav5.db.models.Nivel;
import java.util.ArrayList;

public class NivelesListar extends AppCompatActivity {

    private ListView lvNiveles;
    private NivelDao nivelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_listar);

        lvNiveles = findViewById(R.id.lvNiveles);
        nivelDao = new NivelDao(this);

        /**
         * Se agregan los eventos a los botones de navegacion
         */
        Button btnCreateNivel = findViewById(R.id.btnCreateNivel);
        btnCreateNivel.setOnClickListener(this::openNivelManagement);

        Button btnExitNivelListar = findViewById(R.id.btnExitNivelListar);
        btnExitNivelListar.setOnClickListener(this::exit);
    }

    @Override
    protected void onResume() {
        super.onResume();

        nivelDao.openConnection();
        ArrayList<Nivel> niveles = nivelDao.findAll();
        nivelDao.closeConnection();

        lvNiveles.setAdapter(new NivelesAdapter(this, niveles));
    }

    protected void openNivelManagement(View view) {
        Intent intent = new Intent(this, Niveles.class);
        startActivity(intent);
    }

    protected void exit(View view) {
        finish();
    }
}