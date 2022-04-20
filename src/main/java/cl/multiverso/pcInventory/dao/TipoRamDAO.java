package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoRam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoRamDAO, implementa la inteface TipoRamDAOInterface
 *
 * @author Carlos (15-03-2021)
 */
public class TipoRamDAO implements TipoRamDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;
    
    /**
     * Metodo que permite validar si el Tipo de RAM existe en la base de datos
     *
     * @param objTipoRam
     * @return
     * @throws Exception
     */
    public boolean validaTipoRAM(TipoRam objTipoRam) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_ram WHERE tipo_ram = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoRam.getTipoRam());

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
                this.mensaje = "-Error- El Tipo de Memoria RAM ya existe = " + objTipoRam.getTipoRam();
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
        List<TipoRam> listaTiposRam = new ArrayList<>();
        String sql = "SELECT * FROM tipo_ram;";//consulta sql        

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                TipoRam objTipoRam = new TipoRam();//se instancia el objeto TipoRam
                objTipoRam.setIdTipoRam(this.objResultSet.getInt("id_tipo_ram"));
                objTipoRam.setTipoRam(this.objResultSet.getString("tipo_ram"));

                listaTiposRam.add(objTipoRam);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTiposRam;
    }//fin metodo

    @Override
    public int agregar(TipoRam objTipoRam) {
        String sql = "INSERT INTO tipo_ram (tipo_ram) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objTipoRam.getTipoRam());//se agrega la informacion al objeto

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Tipo de Memoria RAM exitosamente = " + objTipoRam.getTipoRam();
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
    public TipoRam seleccionarPorId(int id) {
        TipoRam objTipoRam = new TipoRam();//se instancia el objeto TipoRam
        String sql = "SELECT * FROM tipo_ram WHERE id_tipo_ram = '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoRam.setIdTipoRam(this.objResultSet.getInt("id_tipo_ram"));
                objTipoRam.setTipoRam(this.objResultSet.getString("tipo_ram"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoRam;
    }//fin metodo

    @Override
    public int actualizar(TipoRam objTipoRam) {
        String sql = "UPDATE tipo_ram SET tipo_ram = ? WHERE id_tipo_ram = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoRam.getTipoRam());
            this.objPreparedStatement.setInt(2, objTipoRam.getIdTipoRam());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Tipo de Memoria RAM exitosamente = " + objTipoRam.getTipoRam();
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
        String sql = "DELETE FROM tipo_ram WHERE id_tipo_ram =" + id + ";";

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Tipo de Memoria RAM exitosamente";
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
