package text.android.mudat2.Registro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import text.android.mudat2.Dao.UsuarioDbo;
import text.android.mudat2.Entity.enumTipoUsuario;
import text.android.mudat2.Entity.clsUsuario;
import text.android.mudat2.MainActivity;
import text.android.mudat2.R;

public class RegistroUsuario extends AppCompatActivity {
    private Boolean editando = false;

    private EditText nombreEdit;
    private Spinner tipoUsuarioSpinner;
    private EditText identificacionEdit;
    private EditText emailEdit;
    private EditText telefonoEdit;
    private EditText claveEdit;

    public clsUsuario clsUsuario;
    private ArrayAdapter tipoUsuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);
        clsUsuario = new clsUsuario();

        asignarEdits();

        tipoUsuarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, enumTipoUsuario.values());

        tipoUsuarioSpinner = findViewById(R.id.tipoUsuarioSpinner);
        tipoUsuarioSpinner.setAdapter(tipoUsuarioAdapter);

        Bundle bundle = getIntent().getExtras();
        Button borrarButton = findViewById(R.id.borrarButton);

        if (bundle != null) {
            editando = true;

            clsUsuario = (clsUsuario) bundle.getSerializable(clsUsuario.class.getSimpleName());
            borrarButton.setVisibility(View.VISIBLE);

            nombreEdit.setText(clsUsuario.getNombre());
            tipoUsuarioSpinner.setSelection(tipoUsuarioAdapter.getPosition(clsUsuario.getEnumTipoUsuario()));
            identificacionEdit.setText(clsUsuario.getIdentificacion());
            emailEdit.setText(clsUsuario.getEmail());
            telefonoEdit.setText(clsUsuario.getEmail());
            claveEdit.setText(clsUsuario.getClave());
        }
    }

    public void guardarClick(View view) {
        UsuarioDbo db = new UsuarioDbo(this);

        if (!MainActivity.validarEntry(nombreEdit) || !MainActivity.validarEntry(identificacionEdit) ||
                !MainActivity.validarEntry(emailEdit) || !MainActivity.validarEntry(telefonoEdit) ||
                !MainActivity.validarEntry(claveEdit)) {
            return;
        }

        clsUsuario.setNombre(nombreEdit.getText().toString());
        clsUsuario.setEnumTipoUsuario(enumTipoUsuario.valueOf(tipoUsuarioSpinner.getSelectedItem().toString()));
        clsUsuario.setIdentificacion(identificacionEdit.getText().toString());
        clsUsuario.setEmail(emailEdit.getText().toString());
        clsUsuario.setTelefono(telefonoEdit.getText().toString());
        clsUsuario.setClave(claveEdit.getText().toString());
        clsUsuario.setEstatus(true);

        long paso = 0;

        if (!editando) {
           paso =  db.crear(clsUsuario);
        }else{
            paso = db.editar(clsUsuario);
        }

        if (paso < 0){
            Toast.makeText(this, R.string.msjErrorGuardar, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void borrarButtonClick(View view) {
        UsuarioDbo db = new UsuarioDbo(this);

        if(db.eliminar(clsUsuario.getId()) >= 0){
            Toast.makeText(this, R.string.msjGuardo, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void asignarEdits() {
        nombreEdit = findViewById(R.id.editTextNombre);
        identificacionEdit = findViewById(R.id.editTextIdentificador);
        emailEdit = findViewById(R.id.editTextEmail);
        telefonoEdit = findViewById(R.id.editTextTelefono);
        claveEdit = findViewById(R.id.editTextClave);
    }
}
