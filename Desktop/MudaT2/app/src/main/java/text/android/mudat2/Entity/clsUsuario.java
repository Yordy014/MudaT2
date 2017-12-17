package text.android.mudat2.Entity;

import java.io.Serializable;

/**
 * Created by Y014 on 12/16/2017.
 */

public class clsUsuario implements Serializable {

    Integer id;
    String nombre;
    enumTipoUsuario enumTipoUsuario;
    String identificacion;
    String email;
    String telefono;
    String clave;
    Boolean estatus;

    public  static final String ID = "id";
    public  static final String NOMBRE = "nombre";
    public  static final String TIPO = "enumTipoUsuario";
    public  static final String IDENTIFICACION = "identificacion";
    public  static final String EMAIL = "email";
    public  static final String TELEFONO = "telefono";
    public  static final String CLAVE = "clave";
    public  static final String ESTATUS = "estatus";

    public clsUsuario() {
        this.id = 0;
        this.nombre = "";
        this.enumTipoUsuario = enumTipoUsuario.CLIENTE;
        this.identificacion = "";
        this.email = "";
        this.telefono = "";
        this.clave = "";
        this.estatus = true;
    }

    public clsUsuario(Integer idUsuario, String nombre, enumTipoUsuario enumTipoUsuario, String identificacion, String email, String telefono, String clave, Boolean estatus) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.enumTipoUsuario = enumTipoUsuario;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public enumTipoUsuario getEnumTipoUsuario() {
        return enumTipoUsuario;
    }

    public void setEnumTipoUsuario(enumTipoUsuario enumTipoUsuario) {
        this.enumTipoUsuario = enumTipoUsuario;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "clsUsuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", enumTipoUsuario=" + enumTipoUsuario +
                ", identificacion=" + identificacion +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", clave='" + clave + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}
