package text.android.mudat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import text.android.mudat2.Dao.CategoriaDbo;
import text.android.mudat2.Entity.clsCategoria;
import text.android.mudat2.Registro.RegistroCategoria;
import text.android.mudat2.view.Adapters.ListAdapter.CategoriaListAdapter;

public class VisualizarCategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_categorias);

        buscarDatos();
    }

    @Override
    protected void onResume(){
        super.onResume();
        buscarDatos();
    }

    private void buscarDatos() {
        CategoriaDbo db = new CategoriaDbo(this);

        ListView list = findViewById(R.id.listviewDatos);
        CategoriaListAdapter adapter = new CategoriaListAdapter(this, db.listar(),false);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clsCategoria clsCategoria = ((clsCategoria) arg0.getItemAtPosition(position));
                Intent i = new Intent(VisualizarCategoriasActivity.this, RegistroCategoria.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(clsCategoria.class.getSimpleName(), clsCategoria);

                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public void crearCategoriaClick(View view) {
        startActivity(new Intent(this, RegistroCategoria.class));
    }
}
