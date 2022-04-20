package cl.multiverso.pcInventory.modelo;

/**
 * Clase Unidad, modela la tabla unidad
 *
 * @author Carlos (11-03-2021)
 */
public class Unidad {

    private int idUnidad;
    private String unidad;

    /**
     * Constructor vacio
     */
    public Unidad() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idUnidad
     * @param unidad
     */
    public Unidad(int idUnidad, String unidad) {
        this.idUnidad = idUnidad;
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unidad{idUnidad=").append(idUnidad);
        sb.append(", unidad=").append(unidad);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
