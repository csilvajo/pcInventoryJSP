package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Unidad;
import java.util.List;

/**
 * Interface UnidadDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * unidad
 *
 * @author Carlos (11-03-2021)
 */
public interface UnidadDAOInterface {

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
     * @param objUnidad
     * @return
     */
    public int agregar(Unidad objUnidad);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public Unidad seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objUnidad
     * @return
     */
    public int actualizar(Unidad objUnidad);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
