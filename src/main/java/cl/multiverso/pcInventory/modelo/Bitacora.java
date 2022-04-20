package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase Bitacora, mapea la tabla bitacora
 *
 * @author Carlos (13-03-2021)
 */
public class Bitacora {

    private int idBitacora;
    private Date fechaCreacionRegistro;
    private Equipo objEquipo;

    /**
     * Constructor vacio
     */
    public Bitacora() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idBitacora
     * @param fechaCreacionRegistro
     * @param objEquipo
     */
    public Bitacora(int idBitacora, Date fechaCreacionRegistro, Equipo objEquipo) {
        this.idBitacora = idBitacora;
        this.fechaCreacionRegistro = fechaCreacionRegistro;
        this.objEquipo = objEquipo;
    }

    public Equipo getObjEquipo() {
        return objEquipo;
    }

    public void setObjEquipo(Equipo objEquipo) {
        this.objEquipo = objEquipo;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Date getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }

    public void setFechaCreacionRegistro(Date fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bitacora{idBitacora=").append(idBitacora);
        sb.append(", fechaCreacionRegistro=").append(fechaCreacionRegistro);
        sb.append(", objEquipo=").append(objEquipo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
