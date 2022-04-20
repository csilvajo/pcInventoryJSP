package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.EstadoOperativo;
import java.util.List;

/**
 * Interface EstadoOperativoDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * estado_operativo
 *
 * @author Carlos (13-03-2021)
 */
public interface EstadoOperativoDAOInterface {

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
     * @param objEstadoOperativo
     * @return
     */
    public int agregar(EstadoOperativo objEstadoOperativo);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public EstadoOperativo seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objEstadoOperativo
     * @return
     */
    public int actualizar(EstadoOperativo objEstadoOperativo);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
