package cl.multiverso.pcInventory.modelo;

/**
 * Clase EstadoOperativo, modela la tabla estado_operativo
 *
 * @author Carlos
 */
public class EstadoOperativo {

    private int idEstadoOperativo;
    private String estadoOperativo;

    /**
     * Constructor vacio
     */
    public EstadoOperativo() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idEstadoOperativo
     * @param estadoOperativo
     */
    public EstadoOperativo(int idEstadoOperativo, String estadoOperativo) {
        this.idEstadoOperativo = idEstadoOperativo;
        this.estadoOperativo = estadoOperativo;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public int getIdEstadoOperativo() {
        return idEstadoOperativo;
    }

    public void setIdEstadoOperativo(int idEstadoOperativo) {
        this.idEstadoOperativo = idEstadoOperativo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EstadoOperativo{idEstadoOperativo=").append(idEstadoOperativo);
        sb.append(", estadoOperativo=").append(estadoOperativo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
