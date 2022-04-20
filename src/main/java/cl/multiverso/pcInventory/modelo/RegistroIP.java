package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase RegistroIP, modela la tabla registro_ip
 *
 * @author Carlos (13-03-2021)
 */
public class RegistroIP {

    private int idRegistroIP;
    private String direccionMAC;
    private String direccionIP;
    private Date fechaCreacionRegistro;
    private Date fechaModificacionRegistro;
    private TipoAdaptadorRed objTipoAdaptadorRed;
    private Equipo objEquipo;

    /**
     * Constructor vacio
     */
    public RegistroIP() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idRegistroIP
     * @param direccionMAC
     * @param direccionIP
     * @param fechaCreacionRegistro
     * @param fechaModificacionRegistro
     * @param objTipoAdaptadorRed
     * @param objEquipo
     */
    public RegistroIP(int idRegistroIP, String direccionMAC, String direccionIP, Date fechaCreacionRegistro, Date fechaModificacionRegistro, TipoAdaptadorRed objTipoAdaptadorRed, Equipo objEquipo) {
        this.idRegistroIP = idRegistroIP;
        this.direccionMAC = direccionMAC;
        this.direccionIP = direccionIP;
        this.fechaCreacionRegistro = fechaCreacionRegistro;
        this.fechaModificacionRegistro = fechaModificacionRegistro;
        this.objTipoAdaptadorRed = objTipoAdaptadorRed;
        this.objEquipo = objEquipo;
    }

    public int getIdRegistroIP() {
        return idRegistroIP;
    }

    public void setIdRegistroIP(int idRegistroIP) {
        this.idRegistroIP = idRegistroIP;
    }

    public String getDireccionMAC() {
        return direccionMAC;
    }

    public void setDireccionMAC(String direccionMAC) {
        this.direccionMAC = direccionMAC;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
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

    public TipoAdaptadorRed getObjTipoAdaptadorRed() {
        return objTipoAdaptadorRed;
    }

    public void setObjTipoAdaptadorRed(TipoAdaptadorRed objTipoAdaptadorRed) {
        this.objTipoAdaptadorRed = objTipoAdaptadorRed;
    }

    public Equipo getObjEquipo() {
        return objEquipo;
    }

    public void setObjEquipo(Equipo objEquipo) {
        this.objEquipo = objEquipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroIP{idRegistroIP=").append(idRegistroIP);
        sb.append(", direccionMAC=").append(direccionMAC);
        sb.append(", direccionIP=").append(direccionIP);
        sb.append(", fechaCreacionRegistro=").append(fechaCreacionRegistro);
        sb.append(", fechaModificacionRegistro=").append(fechaModificacionRegistro);
        sb.append(", objTipoAdaptadorRed=").append(objTipoAdaptadorRed);
        sb.append(", objEquipo=").append(objEquipo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
