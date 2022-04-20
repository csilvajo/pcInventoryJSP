package cl.multiverso.pcInventory.modelo;

import java.sql.Date;

/**
 * Clase DetalleBitacora, mapea la tabla detalle_bitacora
 *
 * @author Carlos (13-03-2021)
 */
public class DetalleBitacora {

    private int idDetalleBitacora;
    private Bitacora objBitacora;
    private Date fechaRegistroDetalle;
    private Empleado objEmpleado;
    private String detalleBitacora;

    /**
     * Constructor vacio
     */
    public DetalleBitacora() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idDetalleBitacora
     * @param objBitacora
     * @param fechaRegistroDetalle
     * @param objEmpleado
     * @param detalleBitacora
     */
    public DetalleBitacora(int idDetalleBitacora, Bitacora objBitacora, Date fechaRegistroDetalle, Empleado objEmpleado, String detalleBitacora) {
        this.idDetalleBitacora = idDetalleBitacora;
        this.objBitacora = objBitacora;
        this.fechaRegistroDetalle = fechaRegistroDetalle;
        this.objEmpleado = objEmpleado;
        this.detalleBitacora = detalleBitacora;
    }

    public String getDetalleBitacora() {
        return detalleBitacora;
    }

    public void setDetalleBitacora(String detalleBitacora) {
        this.detalleBitacora = detalleBitacora;
    }

    public int getIdDetalleBitacora() {
        return idDetalleBitacora;
    }

    public void setIdDetalleBitacora(int idDetalleBitacora) {
        this.idDetalleBitacora = idDetalleBitacora;
    }

    public Bitacora getObjBitacora() {
        return objBitacora;
    }

    public void setObjBitacora(Bitacora objBitacora) {
        this.objBitacora = objBitacora;
    }

    public Date getFechaRegistroDetalle() {
        return fechaRegistroDetalle;
    }

    public void setFechaRegistroDetalle(Date fechaRegistroDetalle) {
        this.fechaRegistroDetalle = fechaRegistroDetalle;
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
        sb.append("DetalleBitacora{idDetalleBitacora=").append(idDetalleBitacora);
        sb.append(", objBitacora=").append(objBitacora);
        sb.append(", fechaRegistroDetalle=").append(fechaRegistroDetalle);
        sb.append(", objEmpleado=").append(objEmpleado);
        sb.append(", detalleBitacora=").append(detalleBitacora);
        sb.append('}');
        return sb.toString();
    }
    
}//fin clase
