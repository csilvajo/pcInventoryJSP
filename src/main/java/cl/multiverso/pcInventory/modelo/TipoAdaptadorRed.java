package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoAdaptadorRed, modela la tabla tipo_adaptador_red
 *
 * @author Carlos (13-03-2021)
 */
public class TipoAdaptadorRed {

    private int idTipoAdaptadorRed;
    private String tipoAdaptadorRed;

    /**
     * Constructor vacio
     */
    public TipoAdaptadorRed() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoAdaptadorRed
     * @param tipoAdaptadorRed
     */
    public TipoAdaptadorRed(int idTipoAdaptadorRed, String tipoAdaptadorRed) {
        this.idTipoAdaptadorRed = idTipoAdaptadorRed;
        this.tipoAdaptadorRed = tipoAdaptadorRed;
    }

    public String getTipoAdaptadorRed() {
        return tipoAdaptadorRed;
    }

    public void setTipoAdaptadorRed(String tipoAdaptadorRed) {
        this.tipoAdaptadorRed = tipoAdaptadorRed;
    }

    public int getIdTipoAdaptadorRed() {
        return idTipoAdaptadorRed;
    }

    public void setIdTipoAdaptadorRed(int idTipoAdaptadorRed) {
        this.idTipoAdaptadorRed = idTipoAdaptadorRed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoAdaptadorRed{idTipoAdaptadorRed=").append(idTipoAdaptadorRed);
        sb.append(", tipoAdaptadorRed=").append(tipoAdaptadorRed);
        sb.append('}');
        return sb.toString();
    }
    
}//fin clase
