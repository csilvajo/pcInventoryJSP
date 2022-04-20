package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Empleado;
import java.util.List;

/**
 * Interface EmpleadoDAOinterface. Permite ejecutar interaccion con la tabla
 * empleado
 *
 * @author Carlos (09-03-2021)
 */
public interface EmpleadoDAOInterface {   
    
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
     * @param objEmpleado
     * @return
     */
    public int agregar(Empleado objEmpleado);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public Empleado seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objEmpleado
     * @return
     */
    public int actualizar(Empleado objEmpleado);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
