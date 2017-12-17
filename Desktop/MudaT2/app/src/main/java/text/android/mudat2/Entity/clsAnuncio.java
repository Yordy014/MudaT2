package text.android.mudat2.Entity;

import java.io.Serializable;

/**
 * Created by Y014 on 12/16/2017.
 */

public class clsAnuncio implements Serializable {

    Integer id;
    clsCategoria clsCategoria;
    clsUsuario idClsUsuario;
    String fecha;
    String condicion;
    Double precio;
    String titulo;
    String ubicacion;
    String detalle;

    public static final String ID = "id";
    public static final String IDCATEGORIA = "idCategoria";
    public static final String IDUSUARIO = "idClsUsuario";
    public static final String FECHA = "fecha";
    public static final String CONDICION = "condicion";
    public static final String PRECIO = "precio";
    public static final String TITULO = "titulo";
    public static final String UBICACION = "ubicacion";
    public static final String DETALLE = "detalle";

    public clsAnuncio() {
        this.id = 0;
        this.clsCategoria = new clsCategoria();
        this.idClsUsuario = new clsUsuario();
        this.fecha = "";
        this.condicion = "";
        this.precio = 0.0;
        this.titulo = "";
        this.ubicacion = "";
        this.detalle = "";
    }

    public clsAnuncio(Integer idAnuncio, clsCategoria clsCategoria, clsUsuario idClsUsuario, String fecha, String condicion, Double precio, String titulo, String ubicacion, String detalle) {
        this.id = idAnuncio;
        this.clsCategoria = clsCategoria;
        this.idClsUsuario = idClsUsuario;
        this.fecha = fecha;
        this.condicion = condicion;
        this.precio = precio;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.detalle = detalle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public clsCategoria getClsCategoria() {
        return clsCategoria;
    }

    public void setClsCategoria(clsCategoria clsCategoria) {
        this.clsCategoria = clsCategoria;
    }

    public clsUsuario getIdClsUsuario() {
        return idClsUsuario;
    }

    public void setIdClsUsuario(clsUsuario idClsUsuario) {
        this.idClsUsuario = idClsUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    @Override
    public String toString() {
        return "clsAnuncio{" +
                "id=" + id +
                ", clsCategoria=" + clsCategoria +
                ", id=" + idClsUsuario +
                ", fecha=" + fecha +
                ", condicion='" + condicion + '\'' +
                ", precio=" + precio +
                ", titulo='" + titulo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
