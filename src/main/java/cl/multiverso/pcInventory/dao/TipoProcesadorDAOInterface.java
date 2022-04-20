package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoProcesador;
import java.util.List;

/**
 * Interface TipoProcesadorDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * tipo_procesador
 *
 * @author Carlos (15-03-2021)
 */
public interface TipoProcesadorDAOInterface {

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
     * @param objTipoProcesador
     * @return
     */
    public int agregar(TipoProcesador objTipoProcesador);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public TipoProcesador seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objTipoProcesador
     * @return
     */
    public int actualizar(TipoProcesador objTipoProcesador);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
