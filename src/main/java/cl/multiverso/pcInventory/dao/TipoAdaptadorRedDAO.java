package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoAdaptadorRed;
import cl.multiverso.pcInventory.modelo.Unidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoAdaptadorRedDAO, implementa la interface class
 * TipoAdaptadorRedDAOInterface
 *
 * @author Carlos (15-03-2021)
 */
public class TipoAdaptadorRedDAO implements TipoAdaptadorRedDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;
    
    /**
     * Metodo que permite validar si el Tipo de Adaptador existe en la base de datos
     *
     * @param objTipoAdaptadorRed
     * @return
     * @throws Exception
     */
    public boolean validaTipoAdaptador(TipoAdaptadorRed objTipoAdaptadorRed) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_adaptador_red WHERE tipo_adaptador_red = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoAdaptadorRed.getTipoAdaptadorRed());

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
                this.mensaje = "-Error- El Tipo de adaptador de red ya existe = " + objTipoAdaptadorRed.getTipoAdaptadorRed();
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
        List<TipoAdaptadorRed> listaTiposAdaptadorRed = new ArrayList<>();
        String sql = "SELECT * FROM tipo_adaptador_red;";//consulta sql        

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                TipoAdaptadorRed objTipoAdaptadorRed = new TipoAdaptadorRed();//se instancia el objeto TipoAdaptadorRed
                objTipoAdaptadorRed.setIdTipoAdaptadorRed(this.objResultSet.getInt("id_tipo_adaptador_red"));
                objTipoAdaptadorRed.setTipoAdaptadorRed(this.objResultSet.getString("tipo_adaptador_red"));

                listaTiposAdaptadorRed.add(objTipoAdaptadorRed);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTiposAdaptadorRed;
    }//fin metodo

    @Override
    public int agregar(TipoAdaptadorRed objTipoAdaptadorRed) {
        String sql = "INSERT INTO tipo_adaptador_red (tipo_adaptador_red) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objTipoAdaptadorRed.getTipoAdaptadorRed());//se agrega la informacion al objeto 

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Tipo de Adaptador de Red exitosamente = " + objTipoAdaptadorRed.getTipoAdaptadorRed();
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
    public TipoAdaptadorRed seleccionarPorId(int id) {
        TipoAdaptadorRed objTipoAdaptador = new TipoAdaptadorRed();//se instancia el objeto TipoRam
        String sql = "SELECT * FROM tipo_adaptador_red WHERE id_tipo_adaptador_red = '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoAdaptador.setIdTipoAdaptadorRed(this.objResultSet.getInt("id_tipo_adaptador_red"));
                objTipoAdaptador.setTipoAdaptadorRed(this.objResultSet.getString("tipo_adaptador_red"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos           
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoAdaptador;
    }//fin metodo

    @Override
    public int actualizar(TipoAdaptadorRed objTipoAdaptadorRed) {
        String sql = "UPDATE tipo_adaptador_red SET tipo_adaptador_red = ? WHERE  id_tipo_adaptador_red = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoAdaptadorRed.getTipoAdaptadorRed());
            this.objPreparedStatement.setInt(2, objTipoAdaptadorRed.getIdTipoAdaptadorRed());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Tipo de Adaptador de Red exitosamente = " + objTipoAdaptadorRed.getTipoAdaptadorRed();
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
        String sql = "DELETE FROM tipo_adaptador_red WHERE id_tipo_adaptador_red =" + id + ";";

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Tipo de Adaptador de Red exitosamente";
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
