package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.Empleado;
import cl.multiverso.pcInventory.modelo.TipoUsuario;
import cl.multiverso.pcInventory.modelo.Unidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase EmpleadoDAO, implementa la interface EmpleadoDAOInterface.
 *
 * @author Carlos (09-03-2021)
 */
public class EmpleadoDAO implements EmpleadoDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que valida el usuario y contraseña
     *
     * @param objEmpleado
     * @return
     * @throws Exception
     */
    public boolean validaLogin(Empleado objEmpleado) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean pase = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta "
                + "FROM empleado "
                + "WHERE username = ? AND password = ? ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objEmpleado.getUsername());
            this.objPreparedStatement.setString(2, objEmpleado.getPassword());

            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //variable que almacena la cantidad de registros encontrados
            int contador = 0;

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                contador = this.objResultSet.getInt("cuenta");
            }
            if (contador > 0) {
                pase = true;
            } else {
                this.mensaje = "Usuario o Contraseña inválidos";
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException- : " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return pase;
    }//fin metodo

    /**
     * Metodo que muestra los datos del Empleado logeado
     *
     * @param empleado
     * @return
     * @throws Exception
     */
    public Empleado empleadoLogeado(Empleado empleado) throws Exception {
        //se instancia el Objeto Empleado
        Empleado objEmpleado = new Empleado();
        //sentencia sql
        String sql = "SELECT e.*, tu.tipo_usuario, u.nombre_unidad "
                + "FROM empleado e, tipo_usuario tu, unidad u  "
                + "WHERE e.id_tipo_usuario = tu.id_tipo_usuario "
                + "AND e.id_unidad = u.id_unidad "
                + "AND e.username = ? AND e.password = ? ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se extrae la informacion desde el objeto ingresado por parametro
            this.objPreparedStatement.setString(1, empleado.getUsername());
            this.objPreparedStatement.setString(2, empleado.getPassword());

            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            while (this.objResultSet.next()) {
                objEmpleado.setIdEmpleado(this.objResultSet.getInt("id_empleado"));
                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_Paterno"));
                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_Materno"));
                objEmpleado.setTelefono(this.objResultSet.getString("telefono"));
                objEmpleado.setEmail(this.objResultSet.getString("email"));
                objEmpleado.setUsername(this.objResultSet.getString("username"));
                objEmpleado.setPassword(this.objResultSet.getString("password"));

                TipoUsuario objTipoUsuario = new TipoUsuario();
                objTipoUsuario.setTipoUsuario(this.objResultSet.getString("tipo_usuario"));
                objEmpleado.setObjTipoUsuario(objTipoUsuario);

                Unidad objUnidad = new Unidad();
                objUnidad.setUnidad(this.objResultSet.getString("nombre_unidad"));
                objEmpleado.setObjUnidad(objUnidad);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException- : " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objEmpleado;
    }//fin metodo

    /**
     * Metodo que permite validar si el Empleado existe en la base de datos
     *
     * @param objEmpleado
     * @return
     * @throws Exception
     */
    public boolean validaEmpleado(Empleado objEmpleado) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM empleado "
                + "WHERE nombre = ? "
                + "AND apellido_paterno = ?"
                + "AND apellido_materno = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objEmpleado.getNombre());
            this.objPreparedStatement.setString(2, objEmpleado.getApellidoPaterno());
            this.objPreparedStatement.setString(3, objEmpleado.getApellidoMaterno());

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
                this.mensaje = "-Error- Empleado ya existe = " + objEmpleado.getNombre() + " " + objEmpleado.getApellidoPaterno() + " " + objEmpleado.getApellidoMaterno();
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

    /**
     * Metodo que permite actualizar la password
     *
     * @param objEmpleado
     * @return
     */
    public int actualizarPassword(Empleado objEmpleado) {
        //sentencia sql
        String sql = "UPDATE empleado "
                + "SET password = ? "
                + "WHERE id_empleado = ? ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objEmpleado.getPassword());
            this.objPreparedStatement.setInt(2, objEmpleado.getIdEmpleado());
            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado la contraseña exitosamente";
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

    //::::: OPERACIONES CRUD :::::
    @Override
    public List listar() {
        //se trae la lista de registros del Objeto Empleado
        List<Empleado> listaEmpleados = new ArrayList<>();
        //sentencia sql
        String sql = "SELECT  e.id_empleado, e.nombre, e.apellido_paterno, "
                + "e.apellido_materno, e.telefono, e.email, e.fecha_creacion_registro, "
                + "e.fecha_modificacion_registro, e.username, t.tipo_usuario, u.nombre_unidad "
                + "FROM empleado e, tipo_usuario t, unidad u "
                + "WHERE e.id_tipo_usuario = t.id_tipo_usuario "
                + "AND e.id_unidad = u.id_unidad "
                + "ORDER BY e.id_empleado;";

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
                Empleado objEmpleado = new Empleado();
                objEmpleado.setIdEmpleado(this.objResultSet.getInt("id_empleado"));
                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_paterno"));
                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_materno"));
                objEmpleado.setTelefono(this.objResultSet.getString("telefono"));
                objEmpleado.setEmail(this.objResultSet.getString("email"));
                objEmpleado.setFechaCreacionRegistro(this.objResultSet.getDate("fecha_creacion_registro"));
                objEmpleado.setFechaModificacionRegistro(this.objResultSet.getDate("fecha_modificacion_registro"));
                objEmpleado.setUsername(this.objResultSet.getString("username"));

                //se instancia el objeto TipoUsuario
                TipoUsuario objTipoUsuario = new TipoUsuario();
                objTipoUsuario.setTipoUsuario(this.objResultSet.getString("tipo_usuario"));
                objEmpleado.setObjTipoUsuario(objTipoUsuario);

                //se instancia el objeto Unidad
                Unidad objUnidad = new Unidad();
                objUnidad.setUnidad(this.objResultSet.getString("nombre_unidad"));
                objEmpleado.setObjUnidad(objUnidad);

                //se agrega el objeto a la lista
                listaEmpleados.add(objEmpleado);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaEmpleados;
    }//fin metodo

    @Override
    public int agregar(Empleado objEmpleado) {
        //sentencia sql
        String sql = "INSERT INTO empleado (nombre,apellido_paterno,apellido_materno,telefono,email,fecha_creacion_registro,username,password,id_tipo_usuario,id_unidad) "
                + "VALUES (?,?,?,?,?,CURDATE(),?,?,?,?) ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setString(1, objEmpleado.getNombre());
            this.objPreparedStatement.setString(2, objEmpleado.getApellidoPaterno());
            this.objPreparedStatement.setString(3, objEmpleado.getApellidoMaterno());
            this.objPreparedStatement.setString(4, objEmpleado.getTelefono());
            this.objPreparedStatement.setString(5, objEmpleado.getEmail());
            this.objPreparedStatement.setString(6, objEmpleado.getUsername());
            this.objPreparedStatement.setString(7, objEmpleado.getPassword());
            this.objPreparedStatement.setInt(8, objEmpleado.getObjTipoUsuario().getIdTipoUsuario());
            this.objPreparedStatement.setInt(9, objEmpleado.getObjUnidad().getIdUnidad());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Empleado exitosamente = " + objEmpleado.getNombre() + " " + objEmpleado.getApellidoPaterno();
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
    public Empleado seleccionarPorId(int id) {
        //se instancia el objeto Empleado
        Empleado objEmpleado = new Empleado();
        //sentencia sql
        String sql = "SELECT  e.id_empleado, e.nombre, e.apellido_paterno, "
                + "e.apellido_materno, e.telefono, e.email, "
                + "e.username, t.id_tipo_usuario, u.id_unidad, "
                + "e.password "
                + "FROM empleado e, tipo_usuario t, unidad u "
                + "WHERE e.id_tipo_usuario = t.id_tipo_usuario "
                + "AND e.id_unidad = u.id_unidad "
                + "AND e.id_empleado = " + id + " ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                objEmpleado.setIdEmpleado(this.objResultSet.getInt("id_empleado"));
                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_paterno"));
                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_materno"));
                objEmpleado.setTelefono(this.objResultSet.getString("telefono"));
                objEmpleado.setEmail(this.objResultSet.getString("email"));
                objEmpleado.setUsername(this.objResultSet.getString("username"));

                //se instancia el objeto TipoUsuario
                TipoUsuario objTipoUsuario = new TipoUsuario();
                objTipoUsuario.setIdTipoUsuario(this.objResultSet.getInt("id_tipo_usuario"));
                objEmpleado.setObjTipoUsuario(objTipoUsuario);

                //se instancia el objeto Unidad
                Unidad objUnidad = new Unidad();
                objUnidad.setIdUnidad(this.objResultSet.getInt("id_unidad"));
                objEmpleado.setObjUnidad(objUnidad);
                
                objEmpleado.setPassword(this.objResultSet.getString("password"));
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objEmpleado;
    }//fin metodo

    @Override
    public int actualizar(Empleado objEmpleado) {
        //sentencia sql
        String sql = "UPDATE empleado "
                + "SET nombre = ?, "
                + "apellido_paterno = ?, "
                + "apellido_materno = ?, "
                + "telefono = ?, "
                + "email = ?, "
                + "fecha_modificacion_registro = CURDATE(), "
                + "username = ?, "
                + "id_tipo_usuario = ?, "
                + "id_unidad = ? "
                + "WHERE id_empleado = ? ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto
            this.objPreparedStatement.setString(1, objEmpleado.getNombre());
            this.objPreparedStatement.setString(2, objEmpleado.getApellidoPaterno());
            this.objPreparedStatement.setString(3, objEmpleado.getApellidoMaterno());
            this.objPreparedStatement.setString(4, objEmpleado.getTelefono());
            this.objPreparedStatement.setString(5, objEmpleado.getEmail());
            this.objPreparedStatement.setString(6, objEmpleado.getUsername());
            this.objPreparedStatement.setInt(7, objEmpleado.getObjTipoUsuario().getIdTipoUsuario());
            this.objPreparedStatement.setInt(8, objEmpleado.getObjUnidad().getIdUnidad());
            this.objPreparedStatement.setInt(9, objEmpleado.getIdEmpleado());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Empleado exitosamente = " + objEmpleado.getNombre() + " " + objEmpleado.getApellidoPaterno();
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
        //sentencia sql
        String sql = "DELETE FROM empleado WHERE id_empleado = " + id + " ";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Empleado exitosamente";
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
