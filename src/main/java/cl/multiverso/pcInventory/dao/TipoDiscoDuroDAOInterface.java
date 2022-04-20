package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoDiscoDuro;
import java.util.List;

/**
 * Interface TipoDiscoDuroDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * tipo_disco_duro
 *
 * @author Carlos (15-03-2021)
 */
public interface TipoDiscoDuroDAOInterface {

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
     * @param objTipoDiscoDuro
     * @return
     */
    public int agregar(TipoDiscoDuro objTipoDiscoDuro);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public TipoDiscoDuro seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objTipoDiscoDuro
     * @return
     */
    public int actualizar(TipoDiscoDuro objTipoDiscoDuro);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
