package text.android.mudat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import text.android.mudat2.Entity.enumTipoUsuario;
import text.android.mudat2.Entity.Utils;

public class PanelRegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_registro);

        if(MainActivity.clsUsuarioActual == null){
            finish();
        }

        if(MainActivity.clsUsuarioActual.getEnumTipoUsuario() == enumTipoUsuario.CLIENTE){
            Toast.makeText(this,"No posee permiso de visualizar el panel",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void verUsuarioClick(View view) {

        if(MainActivity.clsUsuarioActual.getEnumTipoUsuario() != enumTipoUsuario.ADMINISTRADOR)
        {
            Toast.makeText(this,"No posee permiso para realizar esta operacion",Toast.LENGTH_LONG).show();
            return;
        }

        startActivity(new Intent(this, VisualizarUsuariosActivity.class));
    }

    public void verCategoriaClick(View view) {
        startActivity(new Intent(this, VisualizarCategoriasActivity.class));
    }

    public void verAnuncioClick(View view) {
        startActivity(new Intent(this, VisualizarAnunciosActivity.class));
    }

    public void salirbuttonClick(View view) {
        Utils.guardarPreferencia(this,Utils.LOGIN_ID,null);
        Utils.guardarPreferencia(this,Utils.LOGIN_NAME,null);
        MainActivity.clsUsuarioActual = null;
        finish();
    }
}
