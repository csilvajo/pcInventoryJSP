package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase Inventario, mapea la tabla inventario
 *
 * @author Carlos (13-03-2021)
 */
public class Inventario {

    private int idInventario;
    private String numeroInventario;
    private Date fechaCreacionRegistro;
    private Date fechaModificacionRegistro;    
    private Equipo objEquipo;

    /**
     * Constructor vacio
     */
    public Inventario() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idInventario
     * @param numeroInventario
     * @param fechaCreacionRegistro
     * @param fechaModificacionRegistro
     * @param objEquipo
     */
    public Inventario(int idInventario, String numeroInventario, Date fechaCreacionRegistro, Date fechaModificacionRegistro, Equipo objEquipo) {
        this.idInventario = idInventario;
        this.numeroInventario = numeroInventario;
        this.fechaCreacionRegistro = fechaCreacionRegistro;
        this.fechaModificacionRegistro = fechaModificacionRegistro;
        this.objEquipo = objEquipo;
    }

    public Equipo getObjEquipo() {
        return objEquipo;
    }

    public void setObjEquipo(Equipo objEquipo) {
        this.objEquipo = objEquipo;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNumeroInventario() {
        return numeroInventario;
    }

    public void setNumeroInventario(String numeroInventario) {
        this.numeroInventario = numeroInventario;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario{idInventario=").append(idInventario);
        sb.append(", numeroInventario=").append(numeroInventario);
        sb.append(", fechaCreacionRegistro=").append(fechaCreacionRegistro);
        sb.append(", fechaModificacionRegistro=").append(fechaModificacionRegistro);
        sb.append(", objEquipo=").append(objEquipo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
