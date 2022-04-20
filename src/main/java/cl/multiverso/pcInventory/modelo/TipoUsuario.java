package cl.multiverso.pcInventory.modelo;

/**
 * Clase TipoUsuario. Mapea la tabla tipo_usuario.
 *
 * @author Carlos (09-03-2021)
 */
public class TipoUsuario {

    private int idTipoUsuario;
    private String tipoUsuario;

    /**
     * Constructor vacio
     */
    public TipoUsuario() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idTipoUsuario
     * @param tipoUsuario
     */
    public TipoUsuario(int idTipoUsuario, String tipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoUsuario{idTipoUsuario=").append(idTipoUsuario);
        sb.append(", tipoUsuario=").append(tipoUsuario);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
