package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Equipo;
import cl.multiverso.pcInventory.modelo.RegistroIP;
import cl.multiverso.pcInventory.modelo.TipoAdaptadorRed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase RegistroIPDAO, implementa la interface RegistroIPDAOInterface.
 *
 * @author Carlos (10-05-2021)
 */
public class RegistroIPDAO implements RegistroIPDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;
    
    /**
     * Metodo que permite validar si el Regsitro de IP existe en la base de datos
     *
     * @param objRegistroIP
     * @return
     * @throws Exception
     */
    public boolean validaRegistroIP (RegistroIP objRegistroIP) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM registro_ip "
                + "WHERE direccion_mac = ? "
                + "OR direccion_ip = ?"
                + "OR id_equipo = ?;";                

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objRegistroIP.getDireccionMAC());
            this.objPreparedStatement.setString(2, objRegistroIP.getDireccionIP());
            this.objPreparedStatement.setInt(3, objRegistroIP.getObjEquipo().getIdEquipo());
            
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
                this.mensaje = "-Error- La IP, la MAC o el Equipo ingresados ya existen = Por favor revise los datos ingresados ";
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
        //se trae la lista de registros del Objeto RegistroIP
        List<RegistroIP> listaRegistrosIP = new ArrayList<>();
        //sentencia sql
        String sql = "SELECT r.id_registro_ip, r.direccion_mac, r.direccion_ip, t.tipo_adaptador_red, e.hostname, r.fecha_creacion_registro, r.fecha_modificacion_registro "
                + "FROM registro_ip r, tipo_adaptador_red t, equipo e "
                + "WHERE r.id_tipo_adaptador_red = t.id_tipo_adaptador_red "
                + "AND r.id_equipo = e.id_equipo;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores, instanciando los objetos relacionados en la consulta sql
            while (this.objResultSet.next()) {
                //se instancia el objeto Empleado
                RegistroIP objRegistroIP = new RegistroIP();
                objRegistroIP.setIdRegistroIP(this.objResultSet.getInt("id_registro_ip"));
                objRegistroIP.setDireccionMAC(this.objResultSet.getString("direccion_mac"));
                objRegistroIP.setDireccionIP(this.objResultSet.getString("direccion_ip"));
                objRegistroIP.setFechaCreacionRegistro(this.objResultSet.getDate("fecha_creacion_registro"));
                objRegistroIP.setFechaModificacionRegistro(this.objResultSet.getDate("fecha_modificacion_registro"));

                //se instancia el objeto TipoAdapaptadorRed
                TipoAdaptadorRed objTipoAdaptadorRed = new TipoAdaptadorRed();
                objTipoAdaptadorRed.setTipoAdaptadorRed(this.objResultSet.getString("tipo_adaptador_red"));
                objRegistroIP.setObjTipoAdaptadorRed(objTipoAdaptadorRed);

                //se instancia el objeto Equipo
                Equipo objEquipo = new Equipo();
                objEquipo.setHostname(this.objResultSet.getString("hostname"));
                objRegistroIP.setObjEquipo(objEquipo);

                //se agrega el objeto a la lista
                listaRegistrosIP.add(objRegistroIP);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaRegistrosIP;
    }//fin metodo

    @Override
    public int agregar(RegistroIP objRegistroIP) {
        //sentencia sql
        String sql = "INSERT INTO registro_ip (direccion_mac, direccion_ip, id_tipo_adaptador_red, id_equipo, fecha_creacion_registro ) "
                + "VALUES (?, ?, ?, ?, CURDATE());";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setString(1, objRegistroIP.getDireccionMAC());
            this.objPreparedStatement.setString(2, objRegistroIP.getDireccionIP());
            this.objPreparedStatement.setInt(3, objRegistroIP.getObjTipoAdaptadorRed().getIdTipoAdaptadorRed());
            this.objPreparedStatement.setInt(4, objRegistroIP.getObjEquipo().getIdEquipo());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Registro de IP exitosamente = " + objRegistroIP.getDireccionIP();
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
    public RegistroIP seleccionarPorId(int id) {
        //se instancia el objeto Empleado
        RegistroIP objRegistroIP = new RegistroIP();
//        //sentencia sql
//        String sql = "SELECT  e.id_empleado, e.nombre, e.apellido_paterno, "
//                + "e.apellido_materno, e.telefono, e.email, "
//                + "e.username, t.id_tipo_usuario, u.id_unidad, "
//                + "e.password "
//                + "FROM empleado e, tipo_usuario t, unidad u "
//                + "WHERE e.id_tipo_usuario = t.id_tipo_usuario "
//                + "AND e.id_unidad = u.id_unidad "
//                + "AND e.id_empleado = " + id + " ";
//
//        try {
//            //se efectua la conexion a la base de datos
//            this.objConnection = this.objConexion.Conexion();
//            //se preprara la consulta sql
//            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
//            //se ejecuta la consulta
//            this.objResultSet = this.objPreparedStatement.executeQuery();
//
//            //se itera el objeto para obtener los valores
//            while (this.objResultSet.next()) {
//                objEmpleado.setIdEmpleado(this.objResultSet.getInt("id_empleado"));
//                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
//                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_paterno"));
//                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_materno"));
//                objEmpleado.setTelefono(this.objResultSet.getString("telefono"));
//                objEmpleado.setEmail(this.objResultSet.getString("email"));
//                objEmpleado.setUsername(this.objResultSet.getString("username"));
//
//                //se instancia el objeto TipoUsuario
//                TipoUsuario objTipoUsuario = new TipoUsuario();
//                objTipoUsuario.setIdTipoUsuario(this.objResultSet.getInt("id_tipo_usuario"));
//                objEmpleado.setObjTipoUsuario(objTipoUsuario);
//
//                //se instancia el objeto Unidad
//                Unidad objUnidad = new Unidad();
//                objUnidad.setIdUnidad(this.objResultSet.getInt("id_unidad"));
//                objEmpleado.setObjUnidad(objUnidad);
//                
//                objEmpleado.setPassword(this.objResultSet.getString("password"));
//            }
//        } catch (SQLException ex) {
//            this.mensaje = "Error -SQLException-: " + ex.getMessage();
//        } finally {
//            //se cierran los objetos de conexion a la base de datos que estan abiertos
//            Conexion.close(this.objResultSet);
//            Conexion.close(this.objPreparedStatement);
//            Conexion.close(this.objConnection);
//        }
        return objRegistroIP;
    }//fin metodo

    @Override
    public int actualizar(RegistroIP objRegistroIP) {
//        //sentencia sql
//        String sql = "UPDATE empleado "
//                + "SET nombre = ?, "
//                + "apellido_paterno = ?, "
//                + "apellido_materno = ?, "
//                + "telefono = ?, "
//                + "email = ?, "
//                + "fecha_modificacion_registro = CURDATE(), "
//                + "username = ?, "
//                + "id_tipo_usuario = ?, "
//                + "id_unidad = ? "
//                + "WHERE id_empleado = ? ";
//
//        try {
//            //se efectua la conexion a la base de datos
//            this.objConnection = this.objConexion.Conexion();
//            //se preprara la sentencia sql
//            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
//
//            //se agrega la informacion al objeto
//            this.objPreparedStatement.setString(1, objEmpleado.getNombre());
//            this.objPreparedStatement.setString(2, objEmpleado.getApellidoPaterno());
//            this.objPreparedStatement.setString(3, objEmpleado.getApellidoMaterno());
//            this.objPreparedStatement.setString(4, objEmpleado.getTelefono());
//            this.objPreparedStatement.setString(5, objEmpleado.getEmail());
//            this.objPreparedStatement.setString(6, objEmpleado.getUsername());
//            this.objPreparedStatement.setInt(7, objEmpleado.getObjTipoUsuario().getIdTipoUsuario());
//            this.objPreparedStatement.setInt(8, objEmpleado.getObjUnidad().getIdUnidad());
//            this.objPreparedStatement.setInt(9, objEmpleado.getIdEmpleado());
//
//            //se ejecuta la sentencia sql
//            this.filas = this.objPreparedStatement.executeUpdate();
//            //dependiendo del resultado del executeUpdate, se envia mensaje
//            if (this.filas > 0) {
//                this.mensaje = ":) Se ha actualizado el Empleado exitosamente = " + objEmpleado.getNombre() + " " + objEmpleado.getApellidoPaterno();
//            }
//        } catch (SQLException ex) {
//            this.mensaje = "Error -SQLException-: " + ex.getMessage();
//        } finally {
//            //se cierran los objetos de conexion a la base de datos que estan abiertos            
//            Conexion.close(this.objPreparedStatement);
//            Conexion.close(this.objConnection);
//        }
        return this.filas;
    }//fin metodo

    @Override
    public int eliminar(int id) {
//        //sentencia sql
//        String sql = "DELETE FROM empleado WHERE id_empleado = " + id + " ";
//
//        try {
//            //se efectua la conexion a la base de datos
//            this.objConnection = this.objConexion.Conexion();
//            //se preprara la sentencia sql
//            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
//            //se ejecuta la sentencia sql
//            this.filas = this.objPreparedStatement.executeUpdate();
//            //dependiendo del resultado del executeUpdate, se envia mensaje
//            if (this.filas > 0) {
//                this.mensaje = ":) Se ha eliminado el Empleado exitosamente";
//            }
//        } catch (SQLException ex) {
//            this.mensaje = "Error -SQLException-: " + ex.getMessage();
//        } finally {
//            //se cierran los objetos de conexion a la base de datos que estan abiertos            
//            Conexion.close(this.objPreparedStatement);
//            Conexion.close(this.objConnection);
//        }
        return this.filas;
    }//fin metodo

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}//fin clase
