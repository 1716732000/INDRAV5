package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Niveles extends AppCompatActivity {

    EditText txtNivelCodigo, txtNivelNombre;
    String Codigo, Codigo1, Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        txtNivelCodigo = findViewById(R.id.edtNivelCodigo);
        txtNivelNombre = findViewById(R.id.edtNivelNombre);
    }

    //Agregar
    public void NivelesAgregar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getReadableDatabase();

        Codigo = txtNivelCodigo.getText().toString();
        Nombre = txtNivelNombre.getText().toString();

        try{
            if(!Codigo.isEmpty() && !Nombre.isEmpty())
            {
                ContentValues regNiveles = new ContentValues();
                regNiveles.put("codNivel", Codigo);
                regNiveles.put("nomNivel", Nombre);

                BaseDatos.insert("tblNiveles", null, regNiveles);
                BaseDatos.close();

                LimpiarCajas();
                Toast.makeText(this, "REGISTRO AGREGADO", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "ERROR AL INGRESAR DATOS", Toast.LENGTH_LONG).show();
        }
    }//Agregar

    //Consultar
    public void NivelesConsultar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtNivelCodigo.getText().toString();

        if(!Codigo.isEmpty())
        {
            Cursor fila = BaseDatos.rawQuery(
                    "SELECT codNivel, nomNivel " +
                            "FROM tblNiveles " +
                            "WHERE codNivel =" + Codigo, null);

            if(fila.moveToFirst())
            {
                txtNivelCodigo.setText(fila.getString(0));
                txtNivelNombre.setText(fila.getString(1));
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
    public void NivelesModificar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        Codigo = txtNivelCodigo.getText().toString();
        Codigo1 = txtNivelCodigo.getText().toString();
        Nombre = txtNivelNombre.getText().toString();

        if(!Codigo.isEmpty() && !Nombre.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codNivel", Codigo);
            registro.put("nomNivel", Nombre);

            int cantidad = BaseDatos.update("tblNiveles", registro, "codNivel=" + Codigo1, null);
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
    public void NivelesEliminar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "indra.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Codigo = txtNivelCodigo.getText().toString();

        try
        {
            if(!Codigo.isEmpty())
            {
                int Cantidad = BaseDeDatos.delete("tblNiveles", "codNivel=" + Codigo, null);
                BaseDeDatos.close();
                LimpiarCajas();

                if(Cantidad == 1)
                {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show();
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


    ///////////////////////////////////////
    ////////////////////////////////////////
    public void LimpiarCajas(){
        txtNivelCodigo.setText("");
        txtNivelNombre.setText("");
    }

    public void NivelesLimpiarCajas(View view){
        LimpiarCajas();
    }

    public void NivelesFinalizar(View view){
        finish();
    }
}