package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoRam, modela la tabla tipo_ram
 *
 * @author Carlos (13-03-2021)
 */
public class TipoRam {

    private int idTipoRam;
    private String tipoRam;

    /**
     * Constructor vacio
     */
    public TipoRam() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoRam
     * @param tipoRam
     */
    public TipoRam(int idTipoRam, String tipoRam) {
        this.idTipoRam = idTipoRam;
        this.tipoRam = tipoRam;
    }

    public String getTipoRam() {
        return tipoRam;
    }

    public void setTipoRam(String tipoRam) {
        this.tipoRam = tipoRam;
    }

    public int getIdTipoRam() {
        return idTipoRam;
    }

    public void setIdTipoRam(int idTipoRam) {
        this.idTipoRam = idTipoRam;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoRam{idTipoRam=").append(idTipoRam);
        sb.append(", tipoRam=").append(tipoRam);
        sb.append('}');
        return sb.toString();
    }
    
}//fin clase
