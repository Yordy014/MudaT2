package text.android.mudat2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import text.android.mudat2.Entity.clsAnuncio;

public class DetalleAnuncioActivity extends AppCompatActivity {

    private EditText tituloEdit;
    private EditText ubicacionEdit;
    private EditText precioEdit;
    private EditText detalleEdit;
    private EditText condicionEdit;
    private EditText categoriaEdit;
    private EditText usuarioEdit;

    public clsAnuncio clsAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_anuncio);
        clsAnuncio = new clsAnuncio();

        asignarEdits();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            clsAnuncio = (clsAnuncio) bundle.getSerializable(clsAnuncio.class.getSimpleName());

            tituloEdit.setText(clsAnuncio.getTitulo());
            categoriaEdit.setText(clsAnuncio.getClsCategoria().getDescripcion());
            condicionEdit.setText(clsAnuncio.getCondicion());
            ubicacionEdit.setText(clsAnuncio.getUbicacion());
            usuarioEdit.setText(clsAnuncio.getIdClsUsuario().getNombre());
            precioEdit.setText(clsAnuncio.getPrecio().toString());
            detalleEdit.setText(clsAnuncio.getDetalle());
        }
    }

    private void asignarEdits() {
        tituloEdit = findViewById(R.id.editTextTitulo);
        condicionEdit = findViewById(R.id.editTextCondicion);
        categoriaEdit = findViewById(R.id.editTextCategoria);
        ubicacionEdit  = findViewById(R.id.editTextUbicacion);
        precioEdit = findViewById(R.id.editTextPrecio);
        detalleEdit = findViewById(R.id.editTextDetalle);
        usuarioEdit = findViewById(R.id.editTextUsuario);
    }
}
