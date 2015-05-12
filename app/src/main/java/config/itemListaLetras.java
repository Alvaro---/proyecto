package config;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ex.alvaro.pronunciatel.R;

import java.util.ArrayList;

import Clases.Letra;

/**
 * Created by Alvaro on 11/05/2015.
 */
public class itemListaLetras extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Letra> items;

    public itemListaLetras(Activity activity, ArrayList<Letra> items) {
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
        return 0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.lista_letras, null);
        }

        Letra item = items.get(position);

        ImageView image = (ImageView) vi.findViewById(R.id.imagenLetra);
        int imageResource = activity.getResources().getIdentifier(item.getImagenLetra(), null, activity.getPackageName());
        image.setImageDrawable(activity.getResources().getDrawable(imageResource));

        TextView nombre = (TextView) vi.findViewById(R.id.nombreLetra);
        nombre.setText(item.getLetra());

        TextView tipo = (TextView) vi.findViewById(R.id.ejemploLetra);
        tipo.setText(item.getEjemplo());

        ImageView imageEj = (ImageView) vi.findViewById(R.id.imagenEjLetra);
        int imageResource2 = activity.getResources().getIdentifier(item.getImagenEjemplo(), null, activity.getPackageName());
        imageEj.setImageDrawable(activity.getResources().getDrawable(imageResource2));

        return vi;
    }
}
