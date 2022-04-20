package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoRam;
import java.util.List;

/**
 * Interface TipoProcesadorDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * tipo_ram
 *
 * @author Carlos (15-03-2021)
 */
public interface TipoRamDAOInterface {

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
     * @param objTipoRam
     * @return
     */
    public int agregar(TipoRam objTipoRam);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public TipoRam seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objTipoRam
     * @return
     */
    public int actualizar(TipoRam objTipoRam);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
