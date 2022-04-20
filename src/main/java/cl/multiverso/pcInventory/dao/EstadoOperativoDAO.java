package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.EstadoOperativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase EstadoOperativoDAO, implementa la interface
 * EstadoOperativoDAOInterface.
 *
 * @author Carlos (13-03-2021)
 */
public class EstadoOperativoDAO implements EstadoOperativoDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Estado Operativo existe en la base de
     * datos
     *
     * @param objEstadoOperativo
     * @return
     * @throws Exception
     */
    public boolean validaEstadoOperacional(EstadoOperativo objEstadoOperativo) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM estado_operativo WHERE estado_operativo = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objEstadoOperativo.getEstadoOperativo());

            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //variable que almacena la cantidad de registros encontrados
            int contador = 0;

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                contador = this.objResultSet.getInt("cuenta");
            }
            if (contador > 0) {
                existe = true;
                this.mensaje = "-Error- El Estado Operacional ya existe = " + objEstadoOperativo.getEstadoOperativo();
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException- : " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return existe;
    }//fin metodo    

    //::::: OPERACIONES CRUD :::::
    @Override
    public List listar() {
        List<EstadoOperativo> listaEstadosOperativos = new ArrayList<>();
        //consulta sql
        String sql = "SELECT * FROM estado_operativo;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                //se instancia el objeto EstadoOperativo
                EstadoOperativo objEstadoOperativo = new EstadoOperativo();
                objEstadoOperativo.setIdEstadoOperativo(this.objResultSet.getInt("id_estado_operativo"));
                objEstadoOperativo.setEstadoOperativo(this.objResultSet.getString("estado_operativo"));

                //se agrega el objeto a la lista
                listaEstadosOperativos.add(objEstadoOperativo);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaEstadosOperativos;
    }//fin metodo

    @Override
    public int agregar(EstadoOperativo objEstadoOperativo) {
        //sentencia sql
        String sql = "INSERT INTO estado_operativo (estado_operativo) VALUES (?);";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setString(1, objEstadoOperativo.getEstadoOperativo());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Estado Operacional exitosamente = " + objEstadoOperativo.getEstadoOperativo();
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return this.filas;
    }//fin metodo

    @Override
    public EstadoOperativo seleccionarPorId(int id) {
        //se instancia el objeto EstadoOperativo
        EstadoOperativo objEO = new EstadoOperativo();
        //sentencia sql
        String sql = "SELECT * FROM estado_operativo WHERE id_estado_operativo = '" + id + "';";        

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objEO.setIdEstadoOperativo(this.objResultSet.getInt("id_estado_operativo"));
                objEO.setEstadoOperativo(this.objResultSet.getString("estado_operativo"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }        
        return objEO;
    }//fin metodo

    @Override
    public int actualizar(EstadoOperativo objEstadoOperativo) {
        //sentencia sql
        String sql = "UPDATE estado_operativo SET estado_operativo = ? WHERE  id_estado_operativo = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objEstadoOperativo.getEstadoOperativo());
            this.objPreparedStatement.setInt(2, objEstadoOperativo.getIdEstadoOperativo());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Estado Operacional exitosamente = " + objEstadoOperativo.getEstadoOperativo();
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return this.filas;
    }//fin metodo

    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM estado_operativo WHERE id_estado_operativo =" + id + ";";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la sentencia sql             
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Estado Operacional exitosamente";
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return this.filas;
    }//fin metodo

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}//fin clase
