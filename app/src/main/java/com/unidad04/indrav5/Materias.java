package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.unidad04.indrav5.db.dao.MateriaDao;
import com.unidad04.indrav5.db.models.Materia;

public class Materias extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtNombre;
    private MateriaDao materiaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        txtCodigo = findViewById(R.id.edtMateriaCodigo);
        txtNombre = findViewById(R.id.edtMateriaNombre);

        materiaDao = new MateriaDao(this);
    }

    public void MateriasGuardar(View view) {

        String nombre = txtNombre.getText().toString();
        if (nombre.isEmpty()) {
            Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            return;
        }

        Materia materia = new Materia(0, nombre);
        materiaDao.openConnection();
        materiaDao.insert(materia);
        materiaDao.closeConnection();

        LimpiarCajas();
        Toast.makeText(this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();
    }

    public void MateriasConsultar(View view) {

        String codigo = txtCodigo.getText().toString();
        if (codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        materiaDao.openConnection();
        Materia materia = materiaDao.findById(Integer.parseInt(codigo));
        materiaDao.closeConnection();

        if(materia == null) {
            Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
            LimpiarCajas();
            return;
        }

        txtCodigo.setText(materia.getId());
        txtNombre.setText(materia.getNombre());

    }

    public void MateriasModificar(View view) {

        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();

        if(codigo.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "UN CAMPO ESTA VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        Materia materia = new Materia(Integer.parseInt(codigo), nombre);
        materiaDao.openConnection();
        long cantidad = materiaDao.update(materia);
        materiaDao.closeConnection();

        if(cantidad != 1) {
            Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
        LimpiarCajas();

    }

    public void MateriasEliminar(View view) {

        String codigo = txtCodigo.getText().toString();
        if (codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        materiaDao.openConnection();
        long cantidad = materiaDao.delete(new Materia(Integer.parseInt(codigo), null));
        materiaDao.closeConnection();

        if (cantidad != 1) {
            Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "ARTICULO ELIMINADO", Toast.LENGTH_LONG).show();
        LimpiarCajas();
    }

    public void LimpiarCajas() {
        txtCodigo.setText("");
        txtNombre.setText("");
    }

    public void MateriasFinalizar(View view){
        finish();
    }

}