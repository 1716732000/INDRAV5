package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Materias extends AppCompatActivity {

    EditText txtCodigo, txtNombre;
    String Codigo, Codigo1, Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        txtCodigo = findViewById(R.id.edtMateriaCodigo);
        txtNombre = findViewById(R.id.edtMateriaNombre);
    }

    public void MateriasGuardar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        Codigo = txtCodigo.getText().toString();
        Nombre = txtNombre.getText().toString();

        if(!Codigo.isEmpty() && !Nombre.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codMateria", Codigo);
            registro.put("nomMateria", Nombre);

            BaseDatos.insert("tblMaterias", null, registro);
            BaseDatos.close();

            LimpiarCajas();
            Toast.makeText(this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
        }
    }//MateriasGuardar

    //Consultar
    public void MateriasConsultar(View view)
    {
        txtCodigo = findViewById(R.id.edtMateriaCodigo);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();

        if(!Codigo.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT codMateria, nomMateria " +
                            "FROM tblMaterias " +
                            "WHERE codMateria =" + Codigo, null);

            if(fila.moveToFirst())
            {
                txtCodigo.setText(fila.getString(0));
                txtNombre.setText(fila.getString(1));
                BaseDatos.close();
            }
            else
            {
                Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
                LimpiarCajas();
            }
        }
        else
        {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
        }
    }//Materias Consultar

    //Modificar
    public void MateriasModificar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();
        Codigo1 = txtCodigo.getText().toString();
        Nombre = txtNombre.getText().toString();

        if(!Codigo.isEmpty() && !Nombre.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codMateria", Codigo);
            registro.put("nomMateria", Nombre);

            int cantidad = BaseDatos.update("tblMaterias", registro, "codMateria=" + Codigo1, null);
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

    public void MateriasEliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Codigo = txtCodigo.getText().toString();

        if (!Codigo.isEmpty()) {
            int Cantidad = BaseDeDatos.delete("tblMaterias", "codMateria=" + Codigo, null);
            BaseDeDatos.close();


            if (Cantidad == 1) {
                Toast.makeText(this, "ARTICULO ELIMINADO", Toast.LENGTH_LONG).show();
                LimpiarCajas();
            } else {
                Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
        }
    }
///////////////////////////////////////////////////////////
    public void LimpiarCajas() {
        txtCodigo.setText("");
        txtNombre.setText("");
    }

    public void LimparCajas2(View view){
        LimpiarCajas();
    }

    public void MateriasFinalizar(View view){
        finish();
    }


}//Clase