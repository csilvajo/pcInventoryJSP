package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoDiscoDuro, modela la tabla tipo_disco_duro
 *
 * @author Carlos (13-03-2021)
 */
public class TipoDiscoDuro {

    private int idTipoDiscoDuro;
    private String tipoDiscoDuro;

    /**
     * Constructor vacio
     */
    public TipoDiscoDuro() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoDiscoDuro
     * @param tipoDiscoDuro
     */
    public TipoDiscoDuro(int idTipoDiscoDuro, String tipoDiscoDuro) {
        this.idTipoDiscoDuro = idTipoDiscoDuro;
        this.tipoDiscoDuro = tipoDiscoDuro;
    }

    public String getTipoDiscoDuro() {
        return tipoDiscoDuro;
    }

    public void setTipoDiscoDuro(String tipoDiscoDuro) {
        this.tipoDiscoDuro = tipoDiscoDuro;
    }

    public int getIdTipoDiscoDuro() {
        return idTipoDiscoDuro;
    }

    public void setIdTipoDiscoDuro(int idTipoDiscoDuro) {
        this.idTipoDiscoDuro = idTipoDiscoDuro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoDiscoDuro{idTipoDiscoDuro=").append(idTipoDiscoDuro);
        sb.append(", tipoDiscoDuro=").append(tipoDiscoDuro);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
