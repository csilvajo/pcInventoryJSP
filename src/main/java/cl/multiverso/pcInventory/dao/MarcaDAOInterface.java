package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Marca;
import java.util.List;

/**
 * Interface MarcaDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * marca
 *
 * @author Carlos (11-03-2021)
 */
public interface MarcaDAOInterface {

    //::::: OPERACIONES CRUD :::::
    /**
     * Metodo que permite listar los registros contenidos en la tabla
     *
     * @return
     */
    public List listar();

    /**
     * Metodo que permite agregar un registro en la tabla
     *
     * @param objMarca
     * @return
     */
    public int agregar(Marca objMarca);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public Marca seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objMarca
     * @return
     */
    public int actualizar(Marca objMarca);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id);

}//fin interface
