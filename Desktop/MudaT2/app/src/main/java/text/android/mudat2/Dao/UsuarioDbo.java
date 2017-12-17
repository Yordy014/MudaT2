package text.android.mudat2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import text.android.mudat2.Entity.enumTipoUsuario;
import text.android.mudat2.Entity.clsUsuario;

/**
 * Created by Y014 on 12/16/2017.
 */

public class UsuarioDbo {
    DbConnection connection;

    public UsuarioDbo(Context context) {
        connection = new DbConnection(context);
    }

    public long crear(clsUsuario clsUsuario) {
        SQLiteDatabase db = connection.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(clsUsuario.NOMBRE, clsUsuario.getNombre());
        values.put(clsUsuario.TIPO, clsUsuario.getEnumTipoUsuario().toString());
        values.put(clsUsuario.IDENTIFICACION, clsUsuario.getIdentificacion());
        values.put(clsUsuario.EMAIL, clsUsuario.getEmail());
        values.put(clsUsuario.TELEFONO, clsUsuario.getTelefono());
        values.put(clsUsuario.CLAVE, clsUsuario.getClave());
        values.put(clsUsuario.ESTATUS, clsUsuario.getEstatus());

        long retorno = db.insert(clsUsuario.class.getSimpleName(), clsUsuario.ID, values);
        db.close();
        return retorno;
    }

    public clsUsuario buscar(int id) {
        clsUsuario clsUsuario = new clsUsuario();
        List<clsUsuario> listado = this.listar();

        int index;
        if ((double) id > 0) {
            for (int x = 0; x < listado.size(); x++) {
                index = x;
                if (listado.get(index).getId() == id) {
                    clsUsuario = listado.get(index);
                    break;
                }
            }
        }

        return clsUsuario;
    }

    public long editar(clsUsuario clsUsuario) {
        SQLiteDatabase db = connection.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(clsUsuario.NOMBRE, clsUsuario.getNombre());
        values.put(clsUsuario.TIPO, clsUsuario.getEnumTipoUsuario().toString());
        values.put(clsUsuario.IDENTIFICACION, clsUsuario.getIdentificacion());
        values.put(clsUsuario.EMAIL, clsUsuario.getEmail());
        values.put(clsUsuario.TELEFONO, clsUsuario.getTelefono());
        values.put(clsUsuario.CLAVE, clsUsuario.getClave());
        values.put(clsUsuario.ESTATUS, clsUsuario.getEstatus());

        long retorno = db.update(clsUsuario.class.getSimpleName(), values, clsUsuario.ID + "= ?", new String[]{clsUsuario.getId().toString()});
        db.close();
        return retorno;
    }

    public long eliminar(int id) {
        SQLiteDatabase db = connection.getWritableDatabase();
        long retorno = db.delete(clsUsuario.class.getSimpleName(), clsUsuario.ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
        return retorno;
    }

    public List<clsUsuario> listar() {
        List<clsUsuario> clsUsuarios = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();

        String[] columnas = new String[]{clsUsuario.ID, clsUsuario.NOMBRE, clsUsuario.TIPO, clsUsuario.IDENTIFICACION, clsUsuario.EMAIL, clsUsuario.TELEFONO, clsUsuario.CLAVE, clsUsuario.ESTATUS};

        Cursor cursor = db.query(clsUsuario.class.getSimpleName(), columnas, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            clsUsuario clsUsuario = new clsUsuario();

            clsUsuario.setId(cursor.getInt(cursor.getColumnIndex(clsUsuario.ID)));
            clsUsuario.setNombre(cursor.getString(cursor.getColumnIndex(clsUsuario.NOMBRE)));
            clsUsuario.setEnumTipoUsuario(enumTipoUsuario.valueOf(cursor.getString(cursor.getColumnIndex(clsUsuario.TIPO))));
            clsUsuario.setIdentificacion(cursor.getString(cursor.getColumnIndex(clsUsuario.IDENTIFICACION)));
            clsUsuario.setEmail(cursor.getString(cursor.getColumnIndex(clsUsuario.EMAIL)));
            clsUsuario.setTelefono(cursor.getString(cursor.getColumnIndex(clsUsuario.TELEFONO)));
            clsUsuario.setClave(cursor.getString(cursor.getColumnIndex(clsUsuario.CLAVE)));
            clsUsuario.setEstatus(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(clsUsuario.ESTATUS))));

            cursor.moveToNext();
            clsUsuarios.add(clsUsuario);

            Log.i(this.getClass().getSimpleName(), clsUsuario.toString());
        }

        cursor.close();
        db.close();
        return clsUsuarios;
    }

}
