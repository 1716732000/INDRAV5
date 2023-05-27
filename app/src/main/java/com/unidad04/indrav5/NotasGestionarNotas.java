package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotasGestionarNotas extends AppCompatActivity {

    EditText edtCodigoAlumno, edtCodigoNivel, edtCodigoMateria;
    EditText edtNota1, edtNota2, edtNota3, edtNota4, edtProm;
    String txtCodigoAlumno, txtCodigoNivel, txtCodigoMateria;
    String txtNota1, txtNota2, txtNota3, txtNota4, txtProm;
    EditText edtNombreAlumno, edtNombreNivel, edtNombreMateria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_gestionar_notas);

        edtCodigoAlumno = findViewById(R.id.edtNGNCodigoAlumno);
        edtCodigoNivel = findViewById(R.id.edtNGNCodigoNivel);
        edtCodigoMateria = findViewById(R.id.edtNGNCodigoMateria);
        //
        edtNombreAlumno = findViewById(R.id.edtNGNNombreAlumno);
        edtNombreNivel = findViewById(R.id.edtNGNNombreNivel);
        edtNombreMateria = findViewById(R.id.edtNGNNombreMateria);
        //
        edtNota1 = findViewById(R.id.edtNGNNota1);
        edtNota2 = findViewById(R.id.edtNGNNota2);
        edtNota3 = findViewById(R.id.edtNGNNota3);
        edtNota4 = findViewById(R.id.edtNGNNota4);
        edtProm = findViewById(R.id.edtNGNPromedio);
        //
        String CodigoA = getIntent().getStringExtra("CodigoAlumno");
        String CodigoN = getIntent().getStringExtra("CodigoNivel");
        String CodigoM = getIntent().getStringExtra("CodigoMateria");
        //
        String NombreA = getIntent().getStringExtra("NombreAlumno");
        String NombreN = getIntent().getStringExtra("NombreNivel");
        String NombreM = getIntent().getStringExtra("NombreMateria");
        //
        edtCodigoAlumno.setText("" + CodigoA);
        edtCodigoNivel.setText("" + CodigoN);
        edtCodigoMateria.setText("" + CodigoM);
        //
        edtNombreAlumno.setText("" + NombreA);
        edtNombreNivel.setText("" + NombreN);
        edtNombreMateria.setText("" + NombreM);

    }//

    //Actualizacion de notas
    /*
        Primero se debe validar el alumno, pues podria tener notas ya registradas
        Despues de validar el alumno se pueden editar las notas.
    * */
    public void NotasGestionarActualizar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        txtCodigoAlumno = edtCodigoAlumno.getText().toString();
        txtCodigoNivel = edtCodigoNivel.getText().toString();
        txtCodigoMateria = edtCodigoMateria.getText().toString();

        txtNota1 = edtNota1.getText().toString();
        txtNota2 = edtNota2.getText().toString();
        txtNota3 = edtNota3.getText().toString();
        txtNota4 = edtNota4.getText().toString();
        txtProm = edtProm.getText().toString();
        //
        if(!txtNota1.isEmpty()){txtNota1 = "0";}
        if(!txtNota2.isEmpty()){txtNota2 = "0";}
        if(!txtNota3.isEmpty()){txtNota3 = "0";}
        if(!txtNota4.isEmpty()){txtNota4 = "0";}
        //
        Double N1 = Double.parseDouble(txtNota1);
        Double N2 = Double.parseDouble(txtNota2);
        Double N3 = Double.parseDouble(txtNota3);
        Double N4 = Double.parseDouble(txtNota4);
        //
        Double PR = (N1 + N2 + N3 + N4)/4;
        //
        if(!txtCodigoAlumno.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("notP1", N1);
            registro.put("notP2", N2);
            registro.put("notP3", N3);
            registro.put("notP3", N4);
            registro.put("notPr", PR);
            //
            int cantidad = BaseDatos.update("tblNotas", registro, "codAlumno=" + txtCodigoAlumno, null);
            BaseDatos.close();

            if(cantidad == 1)
            {
                Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "UN CAMPO ESTA VACIO", Toast.LENGTH_LONG).show();
        }
    }//Modificar

    //Validar Alumno
    public void NotasGestionarValidarAlumno(View view){
        /*
            Busca el alumno en la tabla tblNotas validando con tres parametros
            codAlumno, codNivel, codMateria y carga los notas existentes
            en cualquiera de los 4 periodos.
            Despues de validar, se puede actualizar notas.
        */

        Toast.makeText(this, "EN PROCESO", Toast.LENGTH_LONG).show();
    }//Validar Alumno

    public void NGNFinalizar(View view){
        finish();
    }
}