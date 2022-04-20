package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoDiscoDuro;
import cl.multiverso.pcInventory.modelo.TipoRam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoDiscoDuroDAO, implementa la interface TipoDiscoDuroDAOInterface
 *
 * @author Carlos (15-03-2021)
 */
public class TipoDiscoDuroDAO implements TipoDiscoDuroDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Tipo de Disco Duro existe en la base de
     * datos
     *
     * @param objTipoDiscoDuro
     * @return
     * @throws Exception
     */
    public boolean validaTipoDiscoDuro(TipoDiscoDuro objTipoDiscoDuro) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_disco_duro WHERE tipo_disco_duro = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoDiscoDuro.getTipoDiscoDuro());

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
                this.mensaje = "-Error- El Tipo de Disco Duro ya existe = " + objTipoDiscoDuro.getTipoDiscoDuro();
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
        List<TipoDiscoDuro> listaTiposDiscoDuro = new ArrayList<>();
        String sql = "SELECT * FROM tipo_disco_duro;";//consulta sql        

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                TipoDiscoDuro objDiscoDuro = new TipoDiscoDuro();//se instancia el objeto TipoDiscoDuro
                objDiscoDuro.setIdTipoDiscoDuro(this.objResultSet.getInt("id_tipo_disco_duro"));
                objDiscoDuro.setTipoDiscoDuro(this.objResultSet.getString("tipo_disco_duro"));

                listaTiposDiscoDuro.add(objDiscoDuro);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTiposDiscoDuro;
    }//fin metodo

    @Override
    public int agregar(TipoDiscoDuro objTipoDiscoDuro) {
        String sql = "INSERT INTO tipo_disco_duro (tipo_disco_duro) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objTipoDiscoDuro.getTipoDiscoDuro());//se agrega la informacion al objeto

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Tipo de Disco Duro exitosamente = " + objTipoDiscoDuro.getTipoDiscoDuro();
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
    public TipoDiscoDuro seleccionarPorId(int id) {
        TipoDiscoDuro objTipoDisco = new TipoDiscoDuro();//se instancia el objeto TipoRam
        String sql = "SELECT * FROM tipo_disco_duro WHERE id_tipo_disco_duro = '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoDisco.setTipoDiscoDuro(this.objResultSet.getString("tipo_disco_duro"));
                objTipoDisco.setIdTipoDiscoDuro(this.objResultSet.getInt("id_tipo_disco_duro"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoDisco;
    }//fin metodo

    @Override
    public int actualizar(TipoDiscoDuro objTipoDiscoDuro) {
        String sql = "UPDATE tipo_disco_duro SET tipo_disco_duro = ? WHERE id_tipo_disco_duro = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoDiscoDuro.getTipoDiscoDuro());
            this.objPreparedStatement.setInt(2, objTipoDiscoDuro.getIdTipoDiscoDuro());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Tipo de Disco Duro exitosamente = " + objTipoDiscoDuro.getTipoDiscoDuro();
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
        String sql = "DELETE FROM tipo_disco_duro WHERE id_tipo_disco_duro =" + id + ";";

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Tipo de Disco Duro exitosamente";
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
