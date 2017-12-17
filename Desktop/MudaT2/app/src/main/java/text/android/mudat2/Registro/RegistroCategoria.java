package text.android.mudat2.Registro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import text.android.mudat2.Dao.CategoriaDbo;
import text.android.mudat2.Entity.clsCategoria;
import text.android.mudat2.MainActivity;
import text.android.mudat2.R;

public class RegistroCategoria extends AppCompatActivity {

    Boolean editando = false;
    EditText descripcionEdit;
    clsCategoria clsCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_categoria);
        clsCategoria = new clsCategoria();

        Button borrarButton = findViewById(R.id.borrarButton);
        descripcionEdit = findViewById(R.id.editTextDescripcion);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editando = true;

            clsCategoria = (clsCategoria) bundle.getSerializable(clsCategoria.class.getSimpleName());
            borrarButton.setVisibility(View.VISIBLE);
            descripcionEdit.setText(clsCategoria.getDescripcion());
        }
    }

    public void guardarClick(View view) {
        CategoriaDbo db = new CategoriaDbo(this);

        if (!MainActivity.validarEntry(descripcionEdit)) {
            return;
        }

        clsCategoria.setDescripcion(descripcionEdit.getText().toString());

        long paso = 0;

        if (!editando) {
            paso = db.crear(clsCategoria);
        } else {
            paso = db.editar(clsCategoria);
        }

        if (paso < 0) {
            Toast.makeText(this, R.string.msjErrorGuardar, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void borrarButtonClick(View view) {
        CategoriaDbo db = new CategoriaDbo(this);

        if (db.eliminar(clsCategoria.getId()) >= 0) {
            Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
