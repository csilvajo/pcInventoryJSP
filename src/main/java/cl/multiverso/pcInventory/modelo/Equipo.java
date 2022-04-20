package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase Equipo, modela la tabla equipo
 *
 * @author Carlos (13-03-2021)
 */
public class Equipo {

    private int idEquipo;
    private TipoEquipo objTipoEquipo;
    private String numeroSerie;
    private Date fechaCreacionRegistro;
    private Date fechaModificacionRegistro;
    private Marca objMarca;
    private String hostname;
    private TipoProcesador objTipoProcesador;
    private int velocidadProcesador;
    private TipoRam objTipoRam;
    private int capacidadRam;
    private TipoDiscoDuro objTipoDiscoDuro;
    private int capacidadDiscoDuro;
    private EstadoOperativo objEstadoOperativo;
    private SistemaOperativo objSistemaOperativo;
    private Empleado objEmpleado;

    /**
     * Constructor vacio
     */
    public Equipo() {
    }

    /**
     * Constructor que inicializa las variables
     * 
     * @param idEquipo
     * @param objTipoEquipo
     * @param numeroSerie
     * @param fechaCreacionRegistro
     * @param fechaModificacionRegistro
     * @param objMarca
     * @param hostname
     * @param objTipoProcesador
     * @param velocidadProcesador
     * @param objTipoRam
     * @param capacidadRam
     * @param objTipoDiscoDuro
     * @param capacidadDiscoDuro
     * @param objEstadoOperativo
     * @param objSistemaOperativo
     * @param objEmpleado 
     */
    public Equipo(int idEquipo, TipoEquipo objTipoEquipo, String numeroSerie, Date fechaCreacionRegistro, Date fechaModificacionRegistro, Marca objMarca, String hostname, TipoProcesador objTipoProcesador, int velocidadProcesador, TipoRam objTipoRam, int capacidadRam, TipoDiscoDuro objTipoDiscoDuro, int capacidadDiscoDuro, EstadoOperativo objEstadoOperativo, SistemaOperativo objSistemaOperativo, Empleado objEmpleado) {
        this.idEquipo = idEquipo;
        this.objTipoEquipo = objTipoEquipo;
        this.numeroSerie = numeroSerie;
        this.fechaCreacionRegistro = fechaCreacionRegistro;
        this.fechaModificacionRegistro = fechaModificacionRegistro;
        this.objMarca = objMarca;
        this.hostname = hostname;
        this.objTipoProcesador = objTipoProcesador;
        this.velocidadProcesador = velocidadProcesador;
        this.objTipoRam = objTipoRam;
        this.capacidadRam = capacidadRam;
        this.objTipoDiscoDuro = objTipoDiscoDuro;
        this.capacidadDiscoDuro = capacidadDiscoDuro;
        this.objEstadoOperativo = objEstadoOperativo;
        this.objSistemaOperativo = objSistemaOperativo;
        this.objEmpleado = objEmpleado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public TipoEquipo getObjTipoEquipo() {
        return objTipoEquipo;
    }

    public void setObjTipoEquipo(TipoEquipo objTipoEquipo) {
        this.objTipoEquipo = objTipoEquipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public Marca getObjMarca() {
        return objMarca;
    }

    public void setObjMarca(Marca objMarca) {
        this.objMarca = objMarca;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public TipoProcesador getObjTipoProcesador() {
        return objTipoProcesador;
    }

    public void setObjTipoProcesador(TipoProcesador objTipoProcesador) {
        this.objTipoProcesador = objTipoProcesador;
    }

    public int getVelocidadProcesador() {
        return velocidadProcesador;
    }

    public void setVelocidadProcesador(int velocidadProcesador) {
        this.velocidadProcesador = velocidadProcesador;
    }

    public TipoRam getObjTipoRam() {
        return objTipoRam;
    }

    public void setObjTipoRam(TipoRam objTipoRam) {
        this.objTipoRam = objTipoRam;
    }

    public int getCapacidadRam() {
        return capacidadRam;
    }

    public void setCapacidadRam(int capacidadRam) {
        this.capacidadRam = capacidadRam;
    }

    public TipoDiscoDuro getObjTipoDiscoDuro() {
        return objTipoDiscoDuro;
    }

    public void setObjTipoDiscoDuro(TipoDiscoDuro objTipoDiscoDuro) {
        this.objTipoDiscoDuro = objTipoDiscoDuro;
    }

    public int getCapacidadDiscoDuro() {
        return capacidadDiscoDuro;
    }

    public void setCapacidadDiscoDuro(int capacidadDiscoDuro) {
        this.capacidadDiscoDuro = capacidadDiscoDuro;
    }

    public EstadoOperativo getObjEstadoOperativo() {
        return objEstadoOperativo;
    }

    public void setObjEstadoOperativo(EstadoOperativo objEstadoOperativo) {
        this.objEstadoOperativo = objEstadoOperativo;
    }

    public SistemaOperativo getObjSistemaOperativo() {
        return objSistemaOperativo;
    }

    public void setObjSistemaOperativo(SistemaOperativo objSistemaOperativo) {
        this.objSistemaOperativo = objSistemaOperativo;
    }

    public Empleado getObjEmpleado() {
        return objEmpleado;
    }

    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipo{idEquipo=").append(idEquipo);
        sb.append(", objTipoEquipo=").append(objTipoEquipo);
        sb.append(", numeroSerie=").append(numeroSerie);
        sb.append(", fechaCreacionRegistro=").append(fechaCreacionRegistro);
        sb.append(", fechaModificacionRegistro=").append(fechaModificacionRegistro);
        sb.append(", objMarca=").append(objMarca);
        sb.append(", hostname=").append(hostname);
        sb.append(", objTipoProcesador=").append(objTipoProcesador);
        sb.append(", velocidadProcesador=").append(velocidadProcesador);
        sb.append(", objTipoRam=").append(objTipoRam);
        sb.append(", capacidadRam=").append(capacidadRam);
        sb.append(", objTipoDiscoDuro=").append(objTipoDiscoDuro);
        sb.append(", capacidadDiscoDuro=").append(capacidadDiscoDuro);
        sb.append(", objEstadoOperativo=").append(objEstadoOperativo);
        sb.append(", objSistemaOperativo=").append(objSistemaOperativo);
        sb.append(", objEmpleado=").append(objEmpleado);
        sb.append('}');
        return sb.toString();
    }
    
}//fin clase
