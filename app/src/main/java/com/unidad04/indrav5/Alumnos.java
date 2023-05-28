package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.unidad04.indrav5.db.dao.AlumnoDao;
import com.unidad04.indrav5.db.models.Alumno;

public class Alumnos extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtApellido;
    private AlumnoDao alumnoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        txtCodigo = findViewById(R.id.edtAlumnosCodigo);
        txtNombre = findViewById(R.id.edtAlumnosNombre);
        txtApellido = findViewById(R.id.edtAlumnosApellidos);

        alumnoDao = new AlumnoDao(getApplicationContext());
    }

    public void AlumnosAgregar(View view) {

        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            return;
        }

        try {

            Alumno alumno = new Alumno(0, nombre, apellido);
            alumnoDao.openConnection();
            alumnoDao.insert(alumno);
            alumnoDao.closeConnection();
            LimpiarCajas();

        } catch (Exception e) {
            Toast.makeText(this, "ERROR AL INGRESAR DATOS", Toast.LENGTH_LONG).show();
        }

    }

    public void AlumnosConsultar(View view) {

        String codigo = txtCodigo.getText().toString();
        if(codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        alumnoDao.openConnection();
        Alumno alumno = alumnoDao.findById(Integer.parseInt(codigo));
        alumnoDao.closeConnection();

        if(alumno == null) {
            Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
            LimpiarCajas();
            return;
        }

        txtNombre.setText(alumno.getNombre());
        txtApellido.setText(alumno.getApellido());

    }

    public void AlumnosModificar(View view) {

        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();

        if(codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            Toast.makeText(this, "UN CAMPO ESTA VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        Alumno alumno = new Alumno(Integer.parseInt(codigo), nombre, apellido);
        alumnoDao.openConnection();
        long cantidad = alumnoDao.update(alumno);
        alumnoDao.closeConnection();

        if(cantidad != 1) {
            Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
        LimpiarCajas();

    }

    public void AlumnosEliminar(View view) {

        String codigo = txtCodigo.getText().toString();
        if(codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            alumnoDao.openConnection();
            long cantidad = alumnoDao.delete(new Alumno(Integer.parseInt(codigo), null, null));
            alumnoDao.closeConnection();
            LimpiarCajas();

            if(cantidad != 1) {
                Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(this, "ARTICULO ELIMINADO", Toast.LENGTH_LONG).show();

        } catch (Exception e) {}

    }

    public void LimpiarCajas() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
    }

    public void AlumnosFinalizar(View view) {
        finish();
    }

}