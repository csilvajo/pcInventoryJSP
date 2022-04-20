package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase Empleado. Mapea la tabla empleado.
 *
 * @author Carlos (09-03-2021)
 */
public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private Date fechaCreacionRegistro;
    private Date fechaModificacionRegistro;
    private String username;
    private String password;
    private TipoUsuario objTipoUsuario;
    private Unidad objUnidad;

    /**
     * Constructor vacio
     */
    public Empleado() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idEmpleado
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param telefono
     * @param email
     * @param fechaCreacionRegistro
     * @param fechaModificacionRegistro
     * @param username
     * @param password
     * @param objTipoUsuario
     * @param objUnidad
     */
    public Empleado(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String email, Date fechaCreacionRegistro, Date fechaModificacionRegistro, String username, String password, TipoUsuario objTipoUsuario, Unidad objUnidad) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.fechaCreacionRegistro = fechaCreacionRegistro;
        this.fechaModificacionRegistro = fechaModificacionRegistro;
        this.username = username;
        this.password = password;
        this.objTipoUsuario = objTipoUsuario;
        this.objUnidad = objUnidad;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }

    public void setFechaCreacionRegistro(Date fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }

    public Date getFechaModificacionRegistro() {
        return fechaModificacionRegistro;
    }

    public void setFechaModificacionRegistro(Date fechaModificacionRegistro) {
        this.fechaModificacionRegistro = fechaModificacionRegistro;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getObjTipoUsuario() {
        return objTipoUsuario;
    }

    public void setObjTipoUsuario(TipoUsuario objTipoUsuario) {
        this.objTipoUsuario = objTipoUsuario;
    }

    public Unidad getObjUnidad() {
        return objUnidad;
    }

    public void setObjUnidad(Unidad objUnidad) {
        this.objUnidad = objUnidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{idEmpleado=").append(idEmpleado);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append(", fechaCreacionRegistro=").append(fechaCreacionRegistro);
        sb.append(", fechaModificacionRegistro=").append(fechaModificacionRegistro);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", objTipoUsuario=").append(objTipoUsuario);
        sb.append(", objUnidad=").append(objUnidad);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
