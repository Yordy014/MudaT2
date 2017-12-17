package text.android.mudat2.view.Adapters.ListAdapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import text.android.mudat2.Entity.clsAnuncio;
import text.android.mudat2.Entity.clsCategoria;
import text.android.mudat2.R;

/**
 * Created by Y014 on 12/16/2017.
 */

public class AnuncioListAdapter extends BaseAdapter{

    private List<clsAnuncio> listado;
    private Activity context;

    public AnuncioListAdapter(Activity context, List<clsAnuncio> clsAnuncios) {
        this.context = context;
        this.listado = clsAnuncios;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public long getItemId(int position) {
        return listado.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return listado.get(position);
    }

    public String getItemFecha(int position) {
        return listado.get(position).getFecha();
    }

    public String getItemTitulo(int position) {
        return listado.get(position).getTitulo();
    }

    public String getItemCategoria(int position) {
        return listado.get(position).getClsCategoria().getDescripcion();
    }

    public String getItemPrecio(int position) {
        return listado.get(position).getPrecio().toString();
    }

    public String getItemCondicion(int position) {
        return listado.get(position).getCondicion();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(context.getApplicationContext(), R.layout.item_anuncios, null);
        }

        TextView fecha = view.findViewById(R.id.itemFecha);
        TextView titulo = view.findViewById(R.id.itemTitulo);
        TextView categoria = view.findViewById(R.id.itemCategoria);
        TextView precio = view.findViewById(R.id.itemPrecio);
        TextView condicion = view.findViewById(R.id.itemCondicion);

        fecha.setText(getItemFecha(position));
        titulo.setText(getItemTitulo(position));
        categoria.setText(getItemCategoria(position));
        precio.setText("Precio: $" + getItemPrecio(position));
        condicion.setText(getItemCondicion(position));

        return view;
    }

    public int getPosition(@Nullable clsCategoria item) {
        return listado.indexOf(item);
    }
}
