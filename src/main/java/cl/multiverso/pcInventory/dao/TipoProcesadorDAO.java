package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoProcesador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoProcesadorDAO, implementa la inteface TipoProcesadorDAOInterface
 *
 * @author Carlos (15-03-2021)
 */
public class TipoProcesadorDAO implements TipoProcesadorDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;
    
    /**
     * Metodo que permite validar si el Tipo de Procesador existe en la base de datos
     *
     * @param objTipoProcesador
     * @return
     * @throws Exception
     */
    public boolean validaTipoProcesador(TipoProcesador objTipoProcesador) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_procesador WHERE tipo_procesador = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoProcesador.getTipoProcesador());

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
                this.mensaje = "-Error- El Tipo de Procesador ya existe = " + objTipoProcesador.getTipoProcesador();
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
        List<TipoProcesador> listaTiposProcesador = new ArrayList<>();
        String sql = "SELECT * FROM tipo_procesador;";//consulta sql        

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                TipoProcesador objTipoProcesador = new TipoProcesador();//se instancia el objeto TipoProcesador
                objTipoProcesador.setIdTipoProcesador(objResultSet.getInt("id_tipo_procesador"));
                objTipoProcesador.setTipoProcesador(objResultSet.getString("tipo_procesador"));

                listaTiposProcesador.add(objTipoProcesador);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTiposProcesador;
    }//fin metodo

    @Override
    public int agregar(TipoProcesador objTipoProcesador) {
        String sql = "INSERT INTO tipo_procesador (tipo_procesador) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objTipoProcesador.getTipoProcesador());//se agrega la informacion al objeto

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Tipo de Procesador exitosamente = " + objTipoProcesador.getTipoProcesador();
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
    public TipoProcesador seleccionarPorId(int id) {
        TipoProcesador objTipoProcesador = new TipoProcesador();//se instancia el objeto TipoProcesador
        String sql = "SELECT * FROM tipo_procesador WHERE id_tipo_procesador = '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoProcesador.setIdTipoProcesador(this.objResultSet.getInt("id_tipo_procesador"));
                objTipoProcesador.setTipoProcesador(this.objResultSet.getString("tipo_procesador"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoProcesador;
    }//fin metodo

    @Override
    public int actualizar(TipoProcesador objTipoProcesador) {
        String sql = "UPDATE tipo_procesador SET tipo_procesador = ? WHERE id_tipo_procesador = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoProcesador.getTipoProcesador());
            this.objPreparedStatement.setInt(2, objTipoProcesador.getIdTipoProcesador());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Tipo de Procesador exitosamente = " + objTipoProcesador.getTipoProcesador();
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
        String sql = "DELETE FROM tipo_procesador WHERE id_tipo_procesador =" + id + ";";

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Tipo de Procesador exitosamente";
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
