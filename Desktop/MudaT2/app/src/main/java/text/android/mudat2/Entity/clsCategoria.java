package text.android.mudat2.Entity;

import java.io.Serializable;

/**
 * Created by Y014 on 12/16/2017.
 */

public class clsCategoria implements Serializable {

    Integer id;
    String descripcion;

    public static final String IDCATEGORIA = "id";
    public static final String DESCRIPCION = "descripcion";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public clsCategoria() {
        this.id = 0;
        this.descripcion = "";
    }

    public clsCategoria(Integer idCategoria, String descripcion) {
        this.id = idCategoria;
        this.descripcion = descripcion;
    }
}
