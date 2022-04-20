package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoEquipo. Mapea la tabla tipo_equipo.
 *
 * @author Carlos (13-03-2021)
 */
public class TipoEquipo {

    private int idTipoEquipo;
    private String tipoEquipo;

    /**
     * Constructor vacio
     */
    public TipoEquipo() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoEquipo
     * @param tipoEquipo
     */
    public TipoEquipo(int idTipoEquipo, String tipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
        this.tipoEquipo = tipoEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public int getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(int idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoEquipo{idTipoEquipo=").append(idTipoEquipo);
        sb.append(", tipoEquipo=").append(tipoEquipo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
