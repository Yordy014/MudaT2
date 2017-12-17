package text.android.mudat2.view.Adapters.ListAdapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import text.android.mudat2.Entity.clsUsuario;
import text.android.mudat2.R;

/**
 * Created by Y014 on 12/16/2017.
 */

public class UsuarioListAdapter extends BaseAdapter{

    private List<clsUsuario> clsUsuarios;
    private Activity context;

    public UsuarioListAdapter(Activity context, List<clsUsuario> clsUsuarios) {
        this.context = context;
        this.clsUsuarios = clsUsuarios;
    }

    @Override
    public int getCount() {
        return clsUsuarios.size();
    }

    @Override
    public long getItemId(int position) {
        return clsUsuarios.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return clsUsuarios.get(position);
    }

    public String getItemNombre(int position) {
        return clsUsuarios.get(position).getNombre();
    }

    public String getItemIdentificacion(int position) {
        return clsUsuarios.get(position).getIdentificacion().toString();
    }

    public String getItemMail(int position) {
        return clsUsuarios.get(position).getEmail();
    }

    public String getItemTelefono(int position) {
        return clsUsuarios.get(position).getTelefono();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(context.getApplicationContext(), R.layout.item_usuarios, null);
        }

        TextView identificacion = view.findViewById(R.id.itemIdentifiacion);
        TextView nombre =  view.findViewById(R.id.itemNombre);
        TextView email =  view.findViewById(R.id.itemEmail);
        TextView telefono =  view.findViewById(R.id.itemTelefono);

        identificacion.setText("Identificacion: " +  getItemIdentificacion(position));
        nombre.setText("Nombre: " + getItemNombre(position));
        email.setText("Email: " + getItemMail(position));
        telefono.setText("Telefono: " + getItemTelefono(position));

        return view;
    }
}
