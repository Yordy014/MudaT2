package text.android.mudat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import text.android.mudat2.Dao.UsuarioDbo;
import text.android.mudat2.Entity.clsUsuario;
import text.android.mudat2.Registro.RegistroUsuario;
import text.android.mudat2.view.Adapters.ListAdapter.UsuarioListAdapter;

public class VisualizarUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuarios);

        buscarDatos();
    }

    @Override
    protected void onResume(){
        super.onResume();
        buscarDatos();
    }

    private void buscarDatos() {
        UsuarioDbo db = new UsuarioDbo(this);

        ListView list = findViewById(R.id.listviewDatos);
        UsuarioListAdapter adapter = new UsuarioListAdapter(this, db.listar());

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clsUsuario clsUsuario = ((clsUsuario) arg0.getItemAtPosition(position));
                Intent i = new Intent(VisualizarUsuariosActivity.this, RegistroUsuario.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(clsUsuario.class.getSimpleName(), clsUsuario);

                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public void crearUsuarioClick(View view) {
        startActivity(new Intent(this, RegistroUsuario.class));
    }
}
