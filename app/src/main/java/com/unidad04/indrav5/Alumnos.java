package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Alumnos extends AppCompatActivity {

    EditText txtCodigo, txtNombre, txtApellido;
    String Codigo, Codigo1, Nombre, Apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        txtCodigo = findViewById(R.id.edtAlumnosCodigo);
        txtNombre = findViewById(R.id.edtAlumnosNombre);
        txtApellido = findViewById(R.id.edtAlumnosApellidos);
    }//onCreate

    //Agregar
    public void AlumnosAgregar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        Codigo = txtCodigo.getText().toString();
        Nombre = txtNombre.getText().toString();
        Apellido = txtApellido.getText().toString();

        try{
            if(!Codigo.isEmpty() && !Nombre.isEmpty() && !Apellido.isEmpty())
            {
                ContentValues registro = new ContentValues();
                registro.put("codAlumno", Codigo);
                registro.put("nomAlumno", Nombre);
                registro.put("apeAlumno", Apellido);

                BaseDatos.insert("tblAlumnos", null, registro);
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
    }//Guardar

    //Consultar
    public void AlumnosConsultar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();

        if(!Codigo.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT nomAlumno, apeAlumno " +
                            "FROM tblAlumnos " +
                            "WHERE codAlumno =" + Codigo, null);

            if(fila.moveToFirst())
            {
                txtNombre.setText(fila.getString(0));
                txtApellido.setText(fila.getString(1));
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
    }//Consultar

    //Modificar
    public void AlumnosModificar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();
        Codigo1 = txtCodigo.getText().toString();
        Nombre = txtNombre.getText().toString();
        Apellido = txtApellido.getText().toString();

        if(!Codigo.isEmpty() && !Nombre.isEmpty() && !Apellido.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codAlumno", Codigo);
            registro.put("nomAlumno", Nombre);
            registro.put("apeAlumno", Apellido);

            int cantidad = BaseDatos.update("tblAlumnos", registro, "codAlumno=" + Codigo1, null);
            BaseDatos.close();

            if(cantidad == 1)
            {
                Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                LimpiarCajas();
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

    //Eliminar
    public void AlumnosEliminar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();

        try
        {
            if(!Codigo.isEmpty())
            {
                int Cantidad = BaseDeDatos.delete("tblAlumnos", "codAlumno=" + Codigo, null);
                BaseDeDatos.close();
                LimpiarCajas();

                if(Cantidad == 1)
                {
                    Toast.makeText(this, "ARTICULO ELIMINADO", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {

        }
    }//Eliminar

    //////////////////////////
    /////////////////////////
    public void LimpiarCajas()
    {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
    }

    public void AlumnosLimpiarCajas(View view){
        LimpiarCajas();
    }
    public void AlumnosFinalizar(View view)
    {
        finish();
    }

}//Clase