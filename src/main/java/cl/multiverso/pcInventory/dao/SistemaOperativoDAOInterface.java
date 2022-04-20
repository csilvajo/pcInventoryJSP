package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.SistemaOperativo;
import java.util.List;

/**
 * Interface SistemaOperativoDAOInterface. Permite ejecutar interaccion CRUD con la tabla
 * sistema_operativo
 *
 * @author Carlos (15-03-2021)
 */
public interface SistemaOperativoDAOInterface {

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
     * @param objSistemaOperativo
     * @return
     */
    public int agregar(SistemaOperativo objSistemaOperativo);

    /**
     * Metodo que permite seleccionar un registro de la tabla por el id
     *
     * @param id
     * @return
     */
    public SistemaOperativo seleccionarPorId(int id);

    /**
     * Metodo que permite actualizar un registro en la tabla
     *
     * @param objSistemaOperativo
     * @return
     */
    public int actualizar(SistemaOperativo objSistemaOperativo);

    /**
     * Metodo que permite eliminar un registro en la tabla
     *
     * @param id
     * @return 
     */
    public int eliminar(int id); 

}//fin interface
