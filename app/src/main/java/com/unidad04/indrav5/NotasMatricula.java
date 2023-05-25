package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotasMatricula extends AppCompatActivity {
    EditText edtCodigoAlumno, edtCodigoNivel, edtCodigoMateria;
    EditText textNombreAl, textNombreNi, textNombreMa;
    String txtCodigoA, txtCodigoN, txtCodigoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_matricula);

        edtCodigoAlumno = findViewById(R.id.edtGestionCodigoAlumno);
        edtCodigoNivel = findViewById(R.id.edtGestionCodigoNivel);
        edtCodigoMateria = findViewById(R.id.edtGestionCodigoMateria);

        textNombreAl = findViewById(R.id.textGestionNombreAlumno);
        textNombreNi = findViewById(R.id.textGestionNombreNivel);
        textNombreMa = findViewById(R.id.textGestionNombreMateria);
    }

    //Confirmar Alumno
    public void NotasConfirmarAlumno(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        txtCodigoA = edtCodigoAlumno.getText().toString();

        if(!txtCodigoA.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nomAlumno " +
                            "FROM tblAlumnos " +
                            "WHERE codAlumno =" + txtCodigoA, null);

            if(fila.moveToFirst())
            {
                //txtCodigo.setText(fila.getString(0));
                //txtNombre.setText(fila.getString(0));
                textNombreAl.setText(fila.getString(0));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
        }
    }//Confirmar Alumno

///////////////////////////////////////////////////////////////////////////////////////////
    //Confirma Nivel
    public void NotasConfirmarNivel(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        txtCodigoN = edtCodigoNivel.getText().toString();

        if(!txtCodigoN.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nomNivel " +
                            "FROM tblNiveles " +
                            "WHERE codNivel =" + txtCodigoN, null);

            if(fila.moveToFirst())
            {
                //txtCodigo.setText(fila.getString(0));
                //txtNombre.setText(fila.getString(0));
                textNombreNi.setText(fila.getString(0));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
        }

    }//Confirmar Nivel

//////////////////////////////////////////////////////////////////
    //Confirmar Materia
    public void NotasConfirmarMaterias(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        txtCodigoM = edtCodigoMateria.getText().toString();

        if(!txtCodigoM.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nomMateria " +
                            "FROM tblMaterias " +
                            "WHERE codMateria =" + txtCodigoM, null);

            if(fila.moveToFirst())
            {
                //txtCodigo.setText(fila.getString(0));
                //txtNombre.setText(fila.getString(0));
                textNombreMa.setText(fila.getString(0));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
                BaseDatos.close();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
        }

    }//Confirmar Materia

    //Matricular alumno
    public void NotasMatricularAlumno(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        txtCodigoA = edtCodigoAlumno.getText().toString();
        txtCodigoN = edtCodigoNivel.getText().toString();
        txtCodigoM = edtCodigoMateria.getText().toString();

        try{
            if(!txtCodigoA.isEmpty() && !txtCodigoN.isEmpty() && !txtCodigoM.isEmpty())
            {
                ContentValues registro = new ContentValues();
                registro.put("codAlumno", txtCodigoA);
                registro.put("codNivel", txtCodigoN);
                registro.put("codMateria", txtCodigoM);

                BaseDatos.insert("tblNotas", null, registro);
                BaseDatos.close();

                LimpiarCajas();
            }
            else
            {
                Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "ERROR AL INGRESAR DATOS", Toast.LENGTH_LONG).show();
        }
    }//Matricular alumno

    ////////////////////////////////
    ///////////////////////////////
    public void LimpiarCajas()
    {
        edtCodigoAlumno.setText("");
        edtCodigoNivel.setText("");
        edtCodigoMateria.setText("");

        textNombreAl.setText("");
        textNombreNi.setText("");
        textNombreMa.setText("");
    }

    public void MatriculaLimpiarCajas(View view)
    {
        LimpiarCajas();
    }
    public void NotasMatriculaFinalizar(View view)
    {
        finish();
    }
}