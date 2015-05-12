package config;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ex.alvaro.pronunciatel.R;

import java.util.ArrayList;

import Clases.Puntuacion;

/**
 * Created by Alvaro on 11/05/2015.
 */
public class itemListaPuntos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Puntuacion> items;

    public itemListaPuntos(Activity activity, ArrayList<Puntuacion> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0; //VERIFICAR SI FUNCIONA SIN LONG EN ID DE PUNTUACION
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.lista_puntuaciones, null);
        }

        Puntuacion item = items.get(position);


        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
        nombre.setText(item.getActividad());

        TextView palabra = (TextView) vi.findViewById(R.id.palabra);
        palabra.setText(item.getPalabra());

        TextView fechaHora = (TextView) vi.findViewById(R.id.fechayHora);
        fechaHora.setText(item.getFecha()+" "+item.getHora());

        TextView puntos = (TextView) vi.findViewById(R.id.puntos);
        puntos.setText(item.getPuntuacion()+" %");

        return vi;
    }
}
