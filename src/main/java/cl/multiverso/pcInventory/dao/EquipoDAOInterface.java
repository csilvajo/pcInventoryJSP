package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Equipo;
import java.util.List;

/**
 * Interface EquipoDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * equipo
 *
 * @author Carlos (14-03-2021)
 */
public interface EquipoDAOInterface {

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
     * @param objEquipo
     * @return
     */
    public int agregar(Equipo objEquipo);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public Equipo seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objEquipo
     * @return
     */
    public int actualizar(Equipo objEquipo);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
