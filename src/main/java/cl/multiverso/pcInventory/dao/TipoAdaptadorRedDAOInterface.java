package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoAdaptadorRed;
import java.util.List;

/**
 * Interface TipoAdaptadorRedDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * tipo_adaptador_red
 *
 * @author Carlos (15-03-2021)
 */
public interface TipoAdaptadorRedDAOInterface {

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
     * @param objTipoAdaptadorRed
     * @return
     */
    public int agregar(TipoAdaptadorRed objTipoAdaptadorRed);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public TipoAdaptadorRed seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objTipoAdaptadorRed
     * @return
     */
    public int actualizar(TipoAdaptadorRed objTipoAdaptadorRed);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
