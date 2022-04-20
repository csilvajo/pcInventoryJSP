package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.RegistroIP;
import java.util.List;

/**
 * Interface RegistroIPDAOInterface. Permite ejecutar interaccion con la tabla
 * regsitro_ip
 *
 * @author Carlos (10-05-2021)
 */
public interface RegistroIPDAOInterface {   
    
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
     * @param objRegistroIP
     * @return
     */
    public int agregar(RegistroIP objRegistroIP);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public RegistroIP seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objRegistroIP
     * @return
     */
    public int actualizar(RegistroIP objRegistroIP);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
