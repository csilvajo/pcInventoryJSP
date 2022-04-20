package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.SistemaOperativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase SistemaOperativoDAO, implementa la interface
 * SistemaOperativoDAOInterface
 *
 * @author Carlos (15-03-2021)
 */
public class SistemaOperativoDAO implements SistemaOperativoDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Sistema Operativo existe en la base de
     * datos
     *
     * @param objSistemaOperativo
     * @return
     * @throws Exception
     */
    public boolean validaSistemaOperativo(SistemaOperativo objSistemaOperativo) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM sistema_operativo WHERE sistema_operativo = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objSistemaOperativo.getSistemaOperativo());

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
                this.mensaje = "-Error- El Sistema Operativo ya existe = " + objSistemaOperativo.getSistemaOperativo();
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
        List<SistemaOperativo> listaSistemasOperativos = new ArrayList<>();
        String sql = "SELECT * FROM sistema_operativo;";//consulta sql        

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta sql

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                SistemaOperativo objSistemaOperativo = new SistemaOperativo();//se instancia el objeto SistemaOperativo
                objSistemaOperativo.setIdSistemaOperativo(this.objResultSet.getInt("id_sistema_operativo"));
                objSistemaOperativo.setSistemaOperativo(this.objResultSet.getString("sistema_operativo"));

                listaSistemasOperativos.add(objSistemaOperativo);//se agrega el objeto a la lista 
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaSistemasOperativos;
    }//fin metodo

    @Override
    public int agregar(SistemaOperativo objSistemaOperativo) {
        String sql = "INSERT INTO sistema_operativo (sistema_operativo) VALUES (?);";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            this.objPreparedStatement.setString(1, objSistemaOperativo.getSistemaOperativo());//se agrega la informacion al objeto 

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Sistema Operativo exitosamente = " + objSistemaOperativo.getSistemaOperativo();
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
    public SistemaOperativo seleccionarPorId(int id) {
        SistemaOperativo objSO = new SistemaOperativo();//se instancia el objeto SistemaOperativo
        String sql = "SELECT * FROM sistema_operativo WHERE id_sistema_operativo = '" + id + "';";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();//se ejecuta la consulta

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objSO.setIdSistemaOperativo(this.objResultSet.getInt("id_sistema_operativo"));
                objSO.setSistemaOperativo(this.objResultSet.getString("sistema_operativo"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objSO;
    }//fin metodo

    @Override
    public int actualizar(SistemaOperativo objSistemaOperativo) {
        String sql = "UPDATE sistema_operativo SET sistema_operativo = ? WHERE  id_sistema_operativo = ?;";//sentencia sql

        try {
            this.objConnection = this.objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setString(1, objSistemaOperativo.getSistemaOperativo());
            this.objPreparedStatement.setInt(2, objSistemaOperativo.getIdSistemaOperativo());

            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Sistema Operativo exitosamente = " + objSistemaOperativo.getSistemaOperativo();
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
        String sql = "DELETE FROM sistema_operativo WHERE id_sistema_operativo =" + id + ";";

        try {
            this.objConnection = objConexion.Conexion();//se efectua la conexion a la base de datos
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);//se preprara la sentencia sql            
            this.filas = this.objPreparedStatement.executeUpdate();//se ejecuta la sentencia sql            
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Sistema Operativo exitosamente";
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
