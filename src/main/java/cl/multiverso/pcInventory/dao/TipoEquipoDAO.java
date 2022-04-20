package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.TipoEquipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase TipoEquipoDAO, implementa la interface TipoEquipoDAOInterface.
 *
 * @author Carlos (14-03-2021)
 */
public class TipoEquipoDAO implements TipoEquipoDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Tipo de Equipo existe en la base de datos
     *
     * @param objTipoEquipo
     * @return
     * @throws Exception
     */
    public boolean validaTipoEquipo(TipoEquipo objTipoEquipo) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM tipo_equipo WHERE tipo_equipo = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objTipoEquipo.getTipoEquipo());

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
                this.mensaje = "-Error- El Tipo de Equipo ya existe = " + objTipoEquipo.getTipoEquipo();
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
        List<TipoEquipo> listaTiposEquipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_equipo;";//consulta sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                TipoEquipo objTipoEquipo = new TipoEquipo();//se intsancia el objeto TipoEquipo
                objTipoEquipo.setIdTipoEquipo(this.objResultSet.getInt("id_tipo_equipo"));
                objTipoEquipo.setTipoEquipo(this.objResultSet.getString("tipo_equipo"));

                listaTiposEquipos.add(objTipoEquipo);//se agrega el objeto a la lista
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaTiposEquipos;
    }//fin metodo

    @Override
    public int agregar(TipoEquipo objTipoEquipo) {
        String sql = "INSERT INTO tipo_equipo (tipo_equipo) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objTipoEquipo.getTipoEquipo());//se agrega la informacion al objeto

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = "Se ha ingresado el Tipo de Equipo (" + objTipoEquipo.getTipoEquipo() + "), exitosamente!";
            } else {
                this.mensaje = "-Error- No se ha podido ingresar el Tipo de Equipo";
            }
        } catch (SQLException ex) {
            mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos           
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return this.filas;
    }//fin metodo

    @Override
    public TipoEquipo seleccionarPorId(int id) {
        TipoEquipo objTipoEquipo = new TipoEquipo();//se instancia el objeto TipoEquipo
        String sql = "SELECT * FROM tipo_equipo WHERE id_tipo_equipo= '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objTipoEquipo.setIdTipoEquipo(this.objResultSet.getInt("id_tipo_equipo"));
                objTipoEquipo.setTipoEquipo(this.objResultSet.getString("tipo_equipo"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objTipoEquipo;
    }//fin metodo

    @Override
    public int actualizar(TipoEquipo objTipoEquipo) {
        String sql = "UPDATE tipo_equipo SET tipo_equipo = ? WHERE id_tipo_equipo = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objTipoEquipo.getTipoEquipo());
            this.objPreparedStatement.setInt(2, objTipoEquipo.getIdTipoEquipo());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = "Se ha actualizado el Tipo de Equipo (" + objTipoEquipo.getTipoEquipo() + "), exitosamente!";
            } else {
                this.mensaje = "-Error- No se ha podido actualizar el Tipo de Equipo";
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
        String sql = "DELETE FROM tipo_equipo WHERE id_tipo_equipo =" + id + ";";

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del insert, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = "Se ha eliminado el Tipo de Equipo exitosamente!";
            } else {
                this.mensaje = "-Error- No se ha podido eliminar el Tipo de Equipo";
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
