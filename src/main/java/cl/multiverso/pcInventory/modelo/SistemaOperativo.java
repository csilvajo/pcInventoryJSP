package cl.multiverso.pcInventory.modelo;

/**
 * Clase SistemaOperativo, modela la tabla sistema_operativo
 *
 * @author Carlos (13-03-2021)
 */
public class SistemaOperativo {

    private int idSistemaOperativo;
    private String sistemaOperativo;

    /**
     * Constructor vacio
     */
    public SistemaOperativo() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idSistemaOperativo
     * @param sistemaOperativo
     */
    public SistemaOperativo(int idSistemaOperativo, String sistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public int getIdSistemaOperativo() {
        return idSistemaOperativo;
    }

    public void setIdSistemaOperativo(int idSistemaOperativo) {
        this.idSistemaOperativo = idSistemaOperativo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SistemaOperativo{idSistemaOperativo=").append(idSistemaOperativo);
        sb.append(", sistemaOperativo=").append(sistemaOperativo);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
