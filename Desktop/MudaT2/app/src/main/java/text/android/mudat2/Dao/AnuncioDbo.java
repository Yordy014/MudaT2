package text.android.mudat2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import text.android.mudat2.Entity.clsAnuncio;

/**
 * Created by Y014 on 12/16/2017.
 */

public class AnuncioDbo {
    DbConnection connection;
    Context context;

    public AnuncioDbo(Context context) {
        connection = new DbConnection(context);
        this.context = context;
    }

    public clsAnuncio buscar(int id) {
        clsAnuncio clsAnuncio = new clsAnuncio();

        if ((double) id > 0) {
            clsAnuncio = this.listar(null).get(id);
        }

        return clsAnuncio;
    }

    public long crear(clsAnuncio clsAnuncio) {
        SQLiteDatabase db = connection.getWritableDatabase();

        long retorno = db.insert(clsAnuncio.class.getSimpleName(), clsAnuncio.ID, setValues(clsAnuncio));

        db.close();
        return retorno;
    }

    public long editar(clsAnuncio clsAnuncio) {
        SQLiteDatabase db = connection.getWritableDatabase();

        long retorno = db.update(clsAnuncio.class.getSimpleName(), setValues(clsAnuncio),
                clsAnuncio.ID + "= ?", new String[]{clsAnuncio.getId().toString()});

        db.close();
        return retorno;
    }

    private ContentValues setValues(clsAnuncio clsAnuncio) {
        ContentValues values = new ContentValues();
        values.put(clsAnuncio.IDCATEGORIA, clsAnuncio.getClsCategoria().getId());
        values.put(clsAnuncio.IDUSUARIO, clsAnuncio.getIdClsUsuario().toString());
        values.put(clsAnuncio.FECHA, clsAnuncio.getFecha());
        values.put(clsAnuncio.CONDICION, clsAnuncio.getCondicion());
        values.put(clsAnuncio.PRECIO, clsAnuncio.getPrecio());
        values.put(clsAnuncio.TITULO, clsAnuncio.getTitulo());
        values.put(clsAnuncio.UBICACION, clsAnuncio.getUbicacion());
        values.put(clsAnuncio.DETALLE, clsAnuncio.getDetalle());
        values.put(clsAnuncio.IDUSUARIO, clsAnuncio.getIdClsUsuario().getId());
        return values;
    }

    public long eliminar(int id) {
        SQLiteDatabase db = connection.getWritableDatabase();
        long retorno = db.delete(clsAnuncio.class.getSimpleName(),
                clsAnuncio.ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
        return retorno;
    }

    public List<clsAnuncio> listar(String condicion) {
        List<clsAnuncio> clsAnuncios = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();

        String[] columnas = new String[]{clsAnuncio.ID, clsAnuncio.IDCATEGORIA, clsAnuncio.IDUSUARIO, clsAnuncio.FECHA, clsAnuncio.CONDICION, clsAnuncio.PRECIO, clsAnuncio.TITULO, clsAnuncio.UBICACION, clsAnuncio.DETALLE};

        Cursor cursor = db.query(clsAnuncio.class.getSimpleName(), columnas, condicion, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            clsAnuncio clsAnuncio = new clsAnuncio();
            CategoriaDbo categoria = new CategoriaDbo(context);
            UsuarioDbo usuario = new UsuarioDbo(context);

            clsAnuncio.setClsCategoria(categoria.buscar(cursor.getInt(cursor.getColumnIndex(clsAnuncio.IDCATEGORIA))));
            clsAnuncio.setIdClsUsuario(usuario.buscar(cursor.getInt(cursor.getColumnIndex(clsAnuncio.IDUSUARIO))));

            clsAnuncio.setId(cursor.getInt(cursor.getColumnIndex(clsAnuncio.ID)));
            clsAnuncio.setFecha(cursor.getString(cursor.getColumnIndex(clsAnuncio.FECHA)));
            clsAnuncio.setCondicion(cursor.getString(cursor.getColumnIndex(clsAnuncio.CONDICION)));
            clsAnuncio.setPrecio(cursor.getDouble(cursor.getColumnIndex(clsAnuncio.PRECIO)));
            clsAnuncio.setTitulo(cursor.getString(cursor.getColumnIndex(clsAnuncio.TITULO)));
            clsAnuncio.setUbicacion(cursor.getString(cursor.getColumnIndex(clsAnuncio.UBICACION)));
            clsAnuncio.setDetalle(cursor.getString(cursor.getColumnIndex(clsAnuncio.DETALLE)));

            cursor.moveToNext();
            clsAnuncios.add(clsAnuncio);
        }

        cursor.close();
        db.close();
        return clsAnuncios;
    }

}
