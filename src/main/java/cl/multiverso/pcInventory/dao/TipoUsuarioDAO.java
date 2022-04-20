package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoUsuarioDAO, implementa la interface TipoUsuarioDAOInterface.
 *
 * @author Carlos (14-03-2021)
 */
public class TipoUsuarioDAO implements TipoUsuarioDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Tipo de Usuario existe en la base de
     * datos
     *
     * @param objTipoUsuario
     * @return
     * @throws Exception
     */
    public boolean validaTipoUsuario(TipoUsuario objTipoUsuario) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_usuario WHERE tipo_usuario = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoUsuario.getTipoUsuario());

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
                this.mensaje = "-Error- El Tipo de Usuario ya existe = " + objTipoUsuario.getTipoUsuario();
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
        //se trae la lista de tipos de usuario
        List<TipoUsuario> listaTipoUsuario = new ArrayList<>();
        //consulta sql 
        String sql = "SELECT * FROM tipo_usuario;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                //se instancia el objeto TipoUsuario
                TipoUsuario objTipoUsuario = new TipoUsuario();
                objTipoUsuario.setIdTipoUsuario(this.objResultSet.getInt("id_tipo_usuario"));
                objTipoUsuario.setTipoUsuario(this.objResultSet.getString("tipo_usuario"));
                //se agrega el objeto a la lista
                listaTipoUsuario.add(objTipoUsuario);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTipoUsuario;
    }//fin metodo

    @Override
    public int agregar(TipoUsuario objTipoUsuario) {
        //sentencia sql   
        String sql = "INSERT INTO tipo_usuario (tipo_usuario) VALUES (?);";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setString(1, objTipoUsuario.getTipoUsuario());

            //se ejecuta la sentencia sql 
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Tipo de Usuario exitosamente = " + objTipoUsuario.getTipoUsuario();
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
    public TipoUsuario seleccionarPorId(int id) {
        //se instancia el TipoUsuario
        TipoUsuario objTipoUsuario = new TipoUsuario();
        //sentencia sql
        String sql = "SELECT * FROM tipo_usuario WHERE id_tipo_usuario = " + id + ";";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoUsuario.setTipoUsuario(this.objResultSet.getString("tipo_usuario"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoUsuario;
    }//fin metodo

    @Override
    public int actualizar(TipoUsuario objTipoUsuario) {
        //sentencia sql
        String sql = "UPDATE tipo_usuario SET tipo_usuario = ? WHERE id_tipo_usuario = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoUsuario.getTipoUsuario());
            this.objPreparedStatement.setInt(2, objTipoUsuario.getIdTipoUsuario());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Tipo de Usuario exitosamente = " + objTipoUsuario.getTipoUsuario();
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return filas;
    }//fin metodo

    @Override
    public int eliminar(int id) {
        //sentencia sql
        String sql = "DELETE FROM tipo_usuario WHERE id_tipo_usuario = " + id + ";";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Tipo de Usuario exitosamente";
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
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
