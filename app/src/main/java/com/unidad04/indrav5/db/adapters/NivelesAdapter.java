package com.unidad04.indrav5.db.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.unidad04.indrav5.R;
import com.unidad04.indrav5.db.dao.NivelDao;
import com.unidad04.indrav5.db.models.Nivel;
import java.util.ArrayList;

public class NivelesAdapter extends ArrayAdapter<Nivel> {

    private AppCompatActivity context;
    private int resource;
    private ArrayList<Nivel> niveles;

    public NivelesAdapter(AppCompatActivity context, ArrayList<Nivel> niveles) {
        super(context, R.layout.list_item_nivel, niveles);
        this.resource = R.layout.list_item_nivel;
        this.context = context;
        this.niveles = niveles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource, null, false);

        Nivel nivel = niveles.get(position);
        TextView txtNivelInfo = view.findViewById(R.id.txtNivelInfo);
        txtNivelInfo.setText("Id: " + nivel.getId() + ", " + nivel.getNombre());

        Button btnEliminarNivel = view.findViewById(R.id.btnEliminarNivel);

        btnEliminarNivel.setOnClickListener(v -> {

            NivelDao nivelDao = new NivelDao(context);
            nivelDao.openConnection();
            nivelDao.delete(niveles.get(position));
            nivelDao.closeConnection();

            remove(niveles.get(position));
        });

        return view;
    }
}
