package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotasGestionarNotas extends AppCompatActivity {

    EditText edtCodigoAlumno, edtCodigoNivel, edtCodigoMateria;
    EditText edtNombreAlumno, edtNombreNivel, edtNombreMateria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_gestionar_notas);

        //txvDato = findViewById(R.id.txvDato);
        edtCodigoAlumno = findViewById(R.id.edtNGNCodigoAlumno);
        edtCodigoNivel = findViewById(R.id.edtNGNCodigoNivel);
        edtCodigoMateria = findViewById(R.id.edtNGNCodigoMateria);
        //
        edtNombreAlumno = findViewById(R.id.edtNGNNombreAlumno);
        edtNombreNivel = findViewById(R.id.edtNGNNombreNivel);
        edtNombreMateria = findViewById(R.id.edtNGNNombreMateria);

        String CodigoA = getIntent().getStringExtra("CodigoAlumno");
        String CodigoN = getIntent().getStringExtra("CodigoNivel");
        String CodigoM = getIntent().getStringExtra("CodigoMateria");

        String NombreA = getIntent().getStringExtra("NombreAlumno");
        String NombreN = getIntent().getStringExtra("NombreNivel");
        String NombreM = getIntent().getStringExtra("NombreMateria");

        edtCodigoAlumno.setText("" + CodigoA);
        edtCodigoNivel.setText("" + CodigoN);
        edtCodigoMateria.setText("" + CodigoM);
        //
        edtNombreAlumno.setText("" + NombreA);
        edtNombreNivel.setText("" + NombreN);
        edtNombreMateria.setText("" + NombreM);

    }

    public void NGNFinalizar(View view){
        finish();
    }
}