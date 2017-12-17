package text.android.mudat2.Registro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import text.android.mudat2.Dao.AnuncioDbo;
import text.android.mudat2.Dao.CategoriaDbo;
import text.android.mudat2.Entity.clsAnuncio;
import text.android.mudat2.Entity.clsCategoria;
import text.android.mudat2.Entity.CondicionAnuncio;
import text.android.mudat2.Entity.Utils;
import text.android.mudat2.MainActivity;
import text.android.mudat2.R;
import text.android.mudat2.view.Adapters.ListAdapter.CategoriaListAdapter;

public class RegistroAnuncio extends AppCompatActivity {
    private Boolean editando = false;

    private EditText tituloEdit;
    private EditText ubicacionEdit;
    private EditText precioEdit;
    private EditText detalleEdit;
    private Spinner condicionSpinner;
    private Spinner categoriaSpinner;
    private Button borrarButton;

    private CategoriaListAdapter categoriasAdapter;
    private ArrayAdapter condicionesAdapter;

    public clsAnuncio clsAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_anuncio);
        clsAnuncio = new clsAnuncio();

        asignarEdits();
        llenarSpinners();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editando = true;

            clsAnuncio = (clsAnuncio) bundle.getSerializable(clsAnuncio.class.getSimpleName());
            borrarButton.setVisibility(View.VISIBLE);

            tituloEdit.setText(clsAnuncio.getTitulo());
            categoriaSpinner.setSelection(categoriasAdapter.getPosition(clsAnuncio.getClsCategoria()));
            condicionSpinner.setSelection(condicionesAdapter.getPosition(clsAnuncio.getCondicion()));
            ubicacionEdit.setText(clsAnuncio.getUbicacion());
            precioEdit.setText(clsAnuncio.getPrecio().toString());
            detalleEdit.setText(clsAnuncio.getDetalle());
        }
    }

    public void guardarClick(View view) {
        AnuncioDbo db = new AnuncioDbo(this);

        if (!MainActivity.validarEntry(tituloEdit) || !MainActivity.validarEntry(precioEdit) || !MainActivity.validarEntry(detalleEdit)) {
            return;
        }

        clsCategoria clsCategoria = (clsCategoria) categoriasAdapter.getItem(categoriaSpinner.getSelectedItemPosition());
        CondicionAnuncio condicion = (CondicionAnuncio) condicionesAdapter.getItem(condicionSpinner.getSelectedItemPosition());

        clsAnuncio.setTitulo(tituloEdit.getText().toString());

        clsAnuncio.setClsCategoria(clsCategoria);
        clsAnuncio.setFecha(Utils.getFechaFormateada());
        clsAnuncio.setCondicion(condicion.toString());
        clsAnuncio.setUbicacion(ubicacionEdit.getText().toString());
        clsAnuncio.setPrecio(Double.valueOf(precioEdit.getText().toString()));
        clsAnuncio.setDetalle(detalleEdit.getText().toString());
        clsAnuncio.setIdClsUsuario(MainActivity.clsUsuarioActual);

        long paso = 0;

        if (!editando) {
            paso = db.crear(clsAnuncio);
        } else {
            paso = db.editar(clsAnuncio);
        }

        if (paso < 0) {
            Toast.makeText(this, R.string.msjErrorGuardar, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void borrarButtonClick(View view) {
        AnuncioDbo db = new AnuncioDbo(this);

        if (db.eliminar(clsAnuncio.getId()) >= 0) {
            Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void asignarEdits() {
        tituloEdit = findViewById(R.id.editTextTitulo);
        condicionSpinner = findViewById(R.id.spinnerCondicion);
        categoriaSpinner = findViewById(R.id.spinnerCategoria);
        ubicacionEdit  = findViewById(R.id.editTextUbicacion);
        precioEdit = findViewById(R.id.editTextPrecio);
        detalleEdit = findViewById(R.id.editTextDetalle);
        borrarButton = findViewById(R.id.borrarButton);
    }

    private void llenarSpinners() {
        CategoriaDbo dbo = new CategoriaDbo(this);

        condicionesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CondicionAnuncio.values());
        condicionSpinner.setAdapter(condicionesAdapter);

        categoriasAdapter = new CategoriaListAdapter(this, dbo.listar(),true); //ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbo.listar());
        categoriaSpinner.setAdapter(categoriasAdapter);
    }
}
