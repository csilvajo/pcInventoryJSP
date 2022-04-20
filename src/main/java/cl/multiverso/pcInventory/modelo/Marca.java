package cl.multiverso.pcInventory.modelo;

/**
 * Clase Marca, modela la tabla marca
 *
 * @author Carlos (13-03-2021)
 */
public class Marca {

    private int idMarca;
    private String marca;

    /**
     * Constructor vacio
     */
    public Marca() {
    }

    /**
     * Constructor que inicializa las variables
     *
     * @param idMarca
     * @param marca
     */
    public Marca(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca{idMarca=").append(idMarca);
        sb.append(", marca=").append(marca);
        sb.append('}');
        return sb.toString();
    }

}//fin clase
