package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

       public void MostrarAlumnos(View view){
        Intent MA = new Intent(this, Alumnos.class);
        startActivity(MA);
    }

    public void MostrarNiveles(View view){
        Intent MN = new Intent(this, Niveles.class);
        startActivity(MN);
    }
    public void MostrarMaterias(View view){
        Intent MM = new Intent(this, Materias.class);
        startActivity(MM);
    }

    public void MostrarNotasMatricula(View view){
        Intent MNM = new Intent(this, NotasMatricula.class);
        startActivity(MNM);
    }

    public void MostrarNotasGestion(View view){
        Intent MNG = new Intent(this, NotasGestionar.class);
        startActivity(MNG);
    }

    public void Finalizar(View view){
        finish();
    }
}