package com.unidad04.indrav5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.unidad04.indrav5.db.dao.NivelDao;
import com.unidad04.indrav5.db.models.Nivel;

public class Niveles extends AppCompatActivity {

    private EditText txtNivelCodigo;
    private EditText txtNivelNombre;
    private NivelDao nivelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        txtNivelCodigo = findViewById(R.id.edtNivelCodigo);
        txtNivelNombre = findViewById(R.id.edtNivelNombre);

        nivelDao = new NivelDao(this);
    }

    public void createNivel(View view) {

        String nombre = txtNivelNombre.getText().toString();
        if(nombre.isEmpty()) {
            Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
            return;
        }

        try {

            Nivel nivel = new Nivel(0, nombre);
            nivelDao.openConnection();
            nivelDao.insert(nivel);
            nivelDao.closeConnection();

            LimpiarCajas();
            Toast.makeText(this, "REGISTRO AGREGADO", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "ERROR AL INGRESAR DATOS", Toast.LENGTH_LONG).show();
        }

    }

    public void NivelesConsultar(View view) {

        String codigo = txtNivelCodigo.getText().toString();
        if (codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        nivelDao.openConnection();
        Nivel nivel = nivelDao.findById(Integer.parseInt(codigo));
        nivelDao.closeConnection();

        if(nivel == null) {
            Toast.makeText(this, "NO EXISTE", Toast.LENGTH_LONG).show();
            LimpiarCajas();
            return;
        }

        txtNivelCodigo.setText(nivel.getId());
        txtNivelNombre.setText(nivel.getNombre());

    }

    public void NivelesModificar(View view) {

        String codigo = txtNivelCodigo.getText().toString();
        String nombre = txtNivelNombre.getText().toString();

        if(codigo.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "UN CAMPO ESTA VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        nivelDao.openConnection();
        Nivel nivel = new Nivel(Integer.parseInt(codigo), nombre);
        long cantidad = nivelDao.update(nivel);
        nivelDao.closeConnection();

        if(cantidad != 1) {
            Toast.makeText(this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
        LimpiarCajas();
    }

    public void NivelesEliminar(View view) {

        String codigo = txtNivelCodigo.getText().toString();
        if (codigo.isEmpty()) {
            Toast.makeText(this, "CODIGO VACIO", Toast.LENGTH_LONG).show();
            return;
        }

        try {

            nivelDao.openConnection();
            long cantidad = nivelDao.delete(new Nivel(Integer.parseInt(codigo), null));
            nivelDao.closeConnection();
            LimpiarCajas();

            if(cantidad != 1) {
                Toast.makeText(this, "ERROR AL ELIMINAR", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show();

        } catch (Exception e) {}

    }

    public void LimpiarCajas() {
        txtNivelCodigo.setText("");
        txtNivelNombre.setText("");
    }

    public void NivelesFinalizar(View view) {
        finish();
    }
}