package text.android.mudat2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import text.android.mudat2.Entity.clsCategoria;

/**
 * Created by Y014 on 12/16/2017.
 */

public class CategoriaDbo {

    DbConnection connection;

    public CategoriaDbo(Context context) {
        connection = new DbConnection(context);
    }

    public clsCategoria buscar(int id) {
        clsCategoria clsCategoria = new clsCategoria();
        List<clsCategoria> listado = this.listar();

        int index;
        if ((double) id > 0) {
            for (int x = 0; x < listado.size(); x++) {
                index = x;
                if (listado.get(index).getId() == id) {
                    clsCategoria = listado.get(index);
                    break;
                }
            }
        }

        return clsCategoria;
    }

    public long crear(clsCategoria clsCategoria) {
        SQLiteDatabase db = connection.getWritableDatabase();

        long retorno = db.insert(clsCategoria.class.getSimpleName(), clsCategoria.IDCATEGORIA, setValues(clsCategoria));

        db.close();
        return retorno;
    }

    public long editar(clsCategoria clsCategoria) {
        SQLiteDatabase db = connection.getWritableDatabase();

        long retorno = db.update(clsCategoria.class.getSimpleName(), setValues(clsCategoria),
                clsCategoria.IDCATEGORIA + "= ?", new String[]{clsCategoria.getId().toString()});

        db.close();
        return retorno;
    }

    private ContentValues setValues(clsCategoria clsCategoria) {
        ContentValues values = new ContentValues();
        values.put(clsCategoria.DESCRIPCION, clsCategoria.getDescripcion());
        return values;
    }

    public long eliminar(int id) {
        SQLiteDatabase db = connection.getWritableDatabase();
        long retorno = db.delete(clsCategoria.class.getSimpleName(),
                clsCategoria.IDCATEGORIA + "= ?", new String[]{String.valueOf(id)});
        db.close();
        return retorno;
    }

    public List<clsCategoria> listar() {
        List<clsCategoria> clsCategorias = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();

        String[] columnas = new String[]{clsCategoria.IDCATEGORIA, clsCategoria.DESCRIPCION};

        Cursor cursor = db.query(clsCategoria.class.getSimpleName(), columnas, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            clsCategoria clsCategoria = new clsCategoria();

            clsCategoria.setId(cursor.getInt(cursor.getColumnIndex(clsCategoria.IDCATEGORIA)));
            clsCategoria.setDescripcion(cursor.getString(cursor.getColumnIndex(clsCategoria.DESCRIPCION)));

            cursor.moveToNext();
            clsCategorias.add(clsCategoria);
        }

        cursor.close();
        db.close();
        return clsCategorias;
    }
}
