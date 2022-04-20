package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoUsuario;
import java.util.List;

/**
 * Interface TipoUsuarioDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * tipo_usuario
 *
 * @author Carlos (14-03-2021)
 */
public interface TipoUsuarioDAOInterface {

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
     * @param objTipoUsuario
     * @return
     */
    public int agregar(TipoUsuario objTipoUsuario);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public TipoUsuario seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objTipoUsuario
     * @return
     */
    public int actualizar(TipoUsuario objTipoUsuario);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
