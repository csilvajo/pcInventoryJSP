package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Unidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase UnidadDAO, implementa la interface UnidadDAOInterface.
 *
 * @author Carlos (11-03-2021)
 */
public class UnidadDAO implements UnidadDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si Unidad existe en la base de datos
     *
     * @param objUnidad
     * @return
     * @throws Exception
     */
    public boolean validaUnidad(Unidad objUnidad) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM unidad WHERE nombre_unidad = ?;";

        try {
            //se efectua la conexion a la base de datos
            objConnection = objConexion.Conexion();
            //se preprara la consulta sql
            objPreparedStatement = objConnection.prepareStatement(sql);

            objPreparedStatement.setString(1, objUnidad.getUnidad());

            //se ejecuta la consulta
            objResultSet = objPreparedStatement.executeQuery();

            //variable que almacena la cantidad de registros encontrados
            int contador = 0;

            //se itera el objeto para obtener los valores
            while (objResultSet.next()) {
                contador = objResultSet.getInt("cuenta");
            }
            if (contador > 0) {
                existe = true;
                mensaje = "-Error- La Unidad ya existe = " + objUnidad.getUnidad();
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException- : " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(objResultSet);
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return existe;
    }//fin metodo 

    //::::: OPERACIONES CRUD :::::
    @Override
    public List listar() {
        List<Unidad> listaUnidades = new ArrayList<>();
        String sql = "SELECT * FROM unidad;";//consulta sql        

        try {
            objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            objPreparedStatement = objConnection.prepareStatement(sql);//se preprara la consulta sql
            objResultSet = objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (objResultSet.next()) {
                Unidad objUnidad = new Unidad();//se intsancia el objeto Unidad
                objUnidad.setIdUnidad(objResultSet.getInt("id_unidad"));
                objUnidad.setUnidad(objResultSet.getString("nombre_unidad"));

                listaUnidades.add(objUnidad);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(objResultSet);
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return listaUnidades;
    }//fin metodo

    @Override
    public int agregar(Unidad objUnidad) {
        String sql = "INSERT INTO unidad (nombre_unidad) VALUES (?);";//sentencia sql

        try {
            objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            objPreparedStatement = objConnection.prepareStatement(sql);//se preprara la sentencia sql

            objPreparedStatement.setString(1, objUnidad.getUnidad());//se agrega la informacion al objeto

            filas = objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (filas > 0) {
                mensaje = ":) Se ha ingresado la Unidad exitosamente = " + objUnidad.getUnidad();
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return filas;
    }//fin metodo

    @Override
    public Unidad seleccionarPorId(int id) {
        Unidad objUnidad = new Unidad();
        String sql = "SELECT * FROM unidad WHERE id_unidad = " + id + ";";

        try {
            objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            objPreparedStatement = objConnection.prepareStatement(sql);//se preprara la consulta sql
            objResultSet = objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (objResultSet.next()) {
                objUnidad.setIdUnidad(objResultSet.getInt("id_unidad"));
                objUnidad.setUnidad(objResultSet.getString("nombre_unidad"));
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(objResultSet);
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return objUnidad;
    }//fin metodo

    @Override
    public int actualizar(Unidad objUnidad) {
        String sql = "UPDATE unidad SET nombre_unidad=? WHERE id_unidad=?;";//sentencia sql

        try {
            objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            objPreparedStatement = objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            objPreparedStatement.setString(1, objUnidad.getUnidad());
            objPreparedStatement.setInt(2, objUnidad.getIdUnidad());

            filas = objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del insert, se envia mensaje
            if (filas > 0) {
                mensaje = ":) Se ha actualizado la Unidad exitosamente = " + objUnidad.getUnidad();
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return filas;
    }//fin metodo

    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM unidad WHERE id_unidad=" + id + ";";

        try {
            objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            objPreparedStatement = objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            filas = objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del insert, se envia mensaje
            if (filas > 0) {
                mensaje = ":) Se ha eliminado la Unidad exitosamente";
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(objPreparedStatement);
            Conexion.close(objConnection);
        }
        return filas;
    }//fin metodo

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}//fin clase
