package text.android.mudat2.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Y014 on 12/16/2017.
 */

public class DbConnection extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "mudat.db";
    public static int DATABASE_VERSION = 1;

    public static String LOG_T = "DbConnection";

    public DbConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlHelperSchema.CREATE_TABLE_USUARIOS);
        db.execSQL(SqlHelperSchema.CREATE_TABLE_CATEGORIAS);
        db.execSQL(SqlHelperSchema.CREATE_TABLE_ANUNCIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
