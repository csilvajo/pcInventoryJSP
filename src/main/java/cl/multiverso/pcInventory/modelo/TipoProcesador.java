package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoProcesador , modela la tabla tipo_procesador
 *
 * @author Carlos (13-03-2021)
 */
public class TipoProcesador {

    private int idTipoProcesador;
    private String tipoProcesador;

    /**
     * Constructor vacio
     */
    public TipoProcesador() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoProcesador
     * @param tipoProcesador
     */
    public TipoProcesador(int idTipoProcesador, String tipoProcesador) {
        this.idTipoProcesador = idTipoProcesador;
        this.tipoProcesador = tipoProcesador;
    }

    public String getTipoProcesador() {
        return tipoProcesador;
    }

    public void setTipoProcesador(String tipoProcesador) {
        this.tipoProcesador = tipoProcesador;
    }

    public int getIdTipoProcesador() {
        return idTipoProcesador;
    }

    public void setIdTipoProcesador(int idTipoProcesador) {
        this.idTipoProcesador = idTipoProcesador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoProcesador{idTipoProcesador=").append(idTipoProcesador);
        sb.append(", tipoProcesador=").append(tipoProcesador);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
