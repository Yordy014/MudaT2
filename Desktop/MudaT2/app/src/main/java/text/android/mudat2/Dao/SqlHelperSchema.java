package text.android.mudat2.Dao;

/**
 * Created by Y014 on 12/16/2017.
 */

public class SqlHelperSchema {

    public static final String CREATE_TABLE_USUARIOS = "CREATE TABLE clsUsuario (\n" +
            "\tid\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tnombre\tTEXT NOT NULL,\n" +
            "\tidentificacion\tTEXT NOT NULL,\n" +
            "\ttipoUsuario\tTEXT NOT NULL,\n" +
            "\temail\tTEXT NOT NULL,\n" +
            "\ttelefono\tTEXT NOT NULL,\n" +
            "\tclave\tTEXT NOT NULL,\n" +
            "\testatus\tINTEGER NOT NULL\n" +
            ");";

    public static final String CREATE_TABLE_CATEGORIAS = "CREATE TABLE clsCategoria (\n" +
            "\tid\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tdescripcion\tTEXT NOT NULL\n" +
            ");";

    public static final String CREATE_TABLE_ANUNCIOS = "CREATE TABLE clsAnuncio (\n" +
            "\tid\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tidCategoria\tINTEGER NOT NULL,\n" +
            "\tidUsuario\tINTEGER NOT NULL,\n" +
            "\tfecha\tTEXT NOT NULL,\n" +
            "\tcondicion\tTEXT NOT NULL,\n" +
            "\tprecio\tNUMERIC NOT NULL,\n" +
            "\ttitulo\tTEXT NOT NULL,\n" +
            "\tubicacion\tTEXT NOT NULL,\n" +
            "\tdetalle\tTEXT NOT NULL,\n" +
            "\tFOREIGN KEY(idCategoria) REFERENCES clsCategoria(id),\n" +
            "\tFOREIGN KEY(idUsuario) REFERENCES clsUsuario(id)\n" +
            ");";
}
