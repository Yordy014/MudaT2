package text.android.mudat2.view.Adapters.ListAdapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import text.android.mudat2.R;
import text.android.mudat2.Entity.clsCategoria;

/**
 * Created by Y014 on 12/16/2017.
 */

public class CategoriaListAdapter extends BaseAdapter{

    private List<clsCategoria> clsCategorias;
    private Activity context;
    private Boolean esSpinner;

    public CategoriaListAdapter(Activity context, List<clsCategoria> clsCategorias, Boolean esSpinner) {
        this.context = context;
        this.clsCategorias = clsCategorias;
        this.esSpinner = esSpinner;
    }

    @Override
    public int getCount() {
        return clsCategorias.size();
    }

    @Override
    public long getItemId(int position) {
        return clsCategorias.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return clsCategorias.get(position);
    }

    public String getItemNombre(int position) {
        return clsCategorias.get(position).getDescripcion();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(context.getApplicationContext(), R.layout.item_categorias, null);
        }

        TextView descripcion =  view.findViewById(R.id.itemDescripcion);
        descripcion.setText(getItemNombre(position));

        if(esSpinner){
            TextView itemNombre =  view.findViewById(R.id.itemNombre);
            TextView tv_signal =  view.findViewById(R.id.itemSignal);

            itemNombre.setVisibility(View.GONE);
            tv_signal.setVisibility(View.GONE);
        }

        return view;
    }

    public int getPosition(@Nullable clsCategoria item) {
        return clsCategorias.indexOf(item);
    }
}
