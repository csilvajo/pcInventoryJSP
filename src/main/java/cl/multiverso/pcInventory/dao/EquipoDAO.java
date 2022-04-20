package cl.multiverso.pcInventory.dao;

import cl.multiverso.pcInventory.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase EquipoDAO, implementa la interface EquipoDAOInterface.
 *
 * @author Carlos(14-03-2021)
 */
public class EquipoDAO implements EquipoDAOInterface {

    Conexion objConexion = new Conexion();
    Connection objConnection;
    PreparedStatement objPreparedStatement;
    ResultSet objResultSet;
    int filas;
    private String mensaje;

    /**
     * Metodo que permite validar si el Equipo existe en la base de datos
     *
     * @param objEquipo
     * @return
     * @throws Exception
     */
    public boolean validaEquipo(Equipo objEquipo) throws Exception {
        //variable que retorna falso si no valida que el empleado existe
        boolean existe = false;
        //sentencia sql
        String sql = "SELECT count(*) cuenta FROM equipo "
                + "WHERE numero_serie = ? "
                + "AND id_tipo_equipo = ? "
                + "AND id_marca = ? "
                + "AND hostname = ?;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            this.objPreparedStatement.setString(1, objEquipo.getNumeroSerie());
            this.objPreparedStatement.setInt(2, objEquipo.getObjTipoEquipo().getIdTipoEquipo());
            this.objPreparedStatement.setInt(3, objEquipo.getObjMarca().getIdMarca());
            this.objPreparedStatement.setString(4, objEquipo.getHostname());

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
                this.mensaje = "-Error- El Equipo ya existe. Se sugiere comprobar Numero de Serie, Tipo de Equipo, Marca y Nombre del Equipo.";
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
        List<Equipo> listaEquipos = new ArrayList<>();
        //consulta sql 
        String sql = "SELECT e.id_equipo, t.tipo_equipo, e.numero_serie, e.fecha_creacion_registro, e.fecha_modificacion_registro, m.marca, "
                + "e.hostname, p.tipo_procesador, e.velocidad_procesador, r.tipo_ram, e.capacidad_ram, d.tipo_disco_duro, e.capacidad_disco_duro, "
                + "o.estado_operativo, em.nombre, em.apellido_paterno, em.apellido_materno, u.nombre_unidad "
                + "FROM equipo e, tipo_equipo t, marca m, tipo_procesador p, tipo_ram r, tipo_disco_duro d, estado_operativo o, empleado em, unidad u "
                + "WHERE e.id_tipo_equipo = t.id_tipo_equipo "
                + "AND e.id_marca = m.id_marca "
                + "AND e.id_tipo_procesador = p.id_tipo_procesador "
                + "AND e.id_tipo_ram = r.id_tipo_ram "
                + "AND e.id_tipo_disco_duro = d.id_tipo_disco_duro "
                + "AND e.id_estado_operativo = o.id_estado_operativo "
                + "AND e.id_empleado = em.id_empleado "
                + "AND em.id_unidad = u.id_unidad "
                + "ORDER BY u.nombre_unidad;";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta sql
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                //se instancia el objeto Equipo
                Equipo objEquipo = new Equipo();
                objEquipo.setIdEquipo(this.objResultSet.getInt("id_equipo"));

                //se instancia el objeto TipoEquipo
                TipoEquipo objTipoEquipo = new TipoEquipo();
                objTipoEquipo.setTipoEquipo(this.objResultSet.getString("tipo_equipo"));
                objEquipo.setObjTipoEquipo(objTipoEquipo);

                objEquipo.setNumeroSerie(this.objResultSet.getString("numero_serie"));
                objEquipo.setFechaCreacionRegistro(this.objResultSet.getDate("fecha_creacion_registro"));
                objEquipo.setFechaModificacionRegistro(this.objResultSet.getDate("fecha_modificacion_registro"));

                //se instancia el objeto Marca
                Marca objMarca = new Marca();
                objMarca.setMarca(this.objResultSet.getString("marca"));
                objEquipo.setObjMarca(objMarca);

                objEquipo.setHostname(this.objResultSet.getString("hostname"));

                //se instancia el objeto TipoProcesador
                TipoProcesador objTipoProcesador = new TipoProcesador();
                objTipoProcesador.setTipoProcesador(this.objResultSet.getString("tipo_procesador"));
                objEquipo.setObjTipoProcesador(objTipoProcesador);
                objEquipo.setVelocidadProcesador(this.objResultSet.getInt("velocidad_procesador"));

                //se instancia el objeto TipoRam
                TipoRam objTipoRam = new TipoRam();
                objTipoRam.setTipoRam(this.objResultSet.getString("tipo_ram"));
                objEquipo.setObjTipoRam(objTipoRam);
                objEquipo.setCapacidadRam(this.objResultSet.getInt("capacidad_ram"));

                //se instancia el objeto TipoDiscoDuro
                TipoDiscoDuro objTipoDiscoDuro = new TipoDiscoDuro();
                objTipoDiscoDuro.setTipoDiscoDuro(this.objResultSet.getString("tipo_disco_duro"));
                objEquipo.setObjTipoDiscoDuro(objTipoDiscoDuro);

                objEquipo.setCapacidadDiscoDuro(this.objResultSet.getInt("capacidad_disco_duro"));

                //se instancia el objeto EstadoOperativo
                EstadoOperativo objEstadoOperativo = new EstadoOperativo();
                objEstadoOperativo.setEstadoOperativo(this.objResultSet.getString("estado_operativo"));
                objEquipo.setObjEstadoOperativo(objEstadoOperativo);

                //se instancia el objeto Empleado
                Empleado objEmpleado = new Empleado();
                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_paterno"));
                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_materno"));

                //se instancia el objeto Unidad
                Unidad objUnidad = new Unidad();
                objUnidad.setUnidad(this.objResultSet.getString("nombre_unidad"));
                objEmpleado.setObjUnidad(objUnidad);

                objEquipo.setObjEmpleado(objEmpleado);

                //se agrega el objeto a la lista
                listaEquipos.add(objEquipo);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return listaEquipos;
    }//fin metodo

    @Override
    public int agregar(Equipo objEquipo) {
        //sentencia sql 
        String sql = "INSERT INTO equipo(id_tipo_equipo,numero_serie,fecha_creacion_registro,id_marca,hostname,id_tipo_procesador,velocidad_procesador,id_tipo_ram,capacidad_ram,id_tipo_disco_duro,capacidad_disco_duro,id_estado_operativo,id_sistema_operativo,id_empleado) "
                + "VALUES (?,?,CURDATE(),?,?,?,?,?,?,?,?,?,?,?);";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto 
            this.objPreparedStatement.setInt(1, objEquipo.getObjTipoEquipo().getIdTipoEquipo());
            this.objPreparedStatement.setString(2, objEquipo.getNumeroSerie());
            this.objPreparedStatement.setInt(3, objEquipo.getObjMarca().getIdMarca());
            this.objPreparedStatement.setString(4, objEquipo.getHostname());
            this.objPreparedStatement.setInt(5, objEquipo.getObjTipoProcesador().getIdTipoProcesador());
            this.objPreparedStatement.setInt(6, objEquipo.getVelocidadProcesador());
            this.objPreparedStatement.setInt(7, objEquipo.getObjTipoRam().getIdTipoRam());
            this.objPreparedStatement.setInt(8, objEquipo.getCapacidadRam());
            this.objPreparedStatement.setInt(9, objEquipo.getObjTipoDiscoDuro().getIdTipoDiscoDuro());
            this.objPreparedStatement.setInt(10, objEquipo.getCapacidadDiscoDuro());
            this.objPreparedStatement.setInt(11, objEquipo.getObjEstadoOperativo().getIdEstadoOperativo());
            this.objPreparedStatement.setInt(12, objEquipo.getObjSistemaOperativo().getIdSistemaOperativo());
            this.objPreparedStatement.setInt(13, objEquipo.getObjEmpleado().getIdEmpleado());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha ingresado el Equipo exitosamente = " + objEquipo.getHostname();
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
    public Equipo seleccionarPorId(int id) {
        //se instancia el objeto Equipo
        Equipo objEquipo = new Equipo();
        //sentencia sql
        String sql = "SELECT eo.id_estado_operativo, eo.estado_operativo, e.id_equipo, e.numero_serie, e.hostname, te.id_tipo_equipo, te.tipo_equipo, m.id_marca, m.marca, em.id_empleado, em.nombre, em.apellido_paterno, em.apellido_materno, "
                + "tp.id_tipo_procesador, tp.tipo_procesador, e.velocidad_procesador, tr.id_tipo_ram, tr.tipo_ram, e.capacidad_ram, td.id_tipo_disco_duro, td.tipo_disco_duro, e.capacidad_disco_duro, so.id_sistema_operativo, so.sistema_operativo	\n"
                + "FROM equipo e, estado_operativo eo, tipo_equipo te, marca m, empleado em, tipo_procesador tp, tipo_ram tr, tipo_disco_duro td, sistema_operativo so "
                + "WHERE e.id_estado_operativo = eo.id_estado_operativo "
                + "AND	e.id_tipo_equipo = te.id_tipo_equipo "
                + "AND e.id_marca = m.id_marca "
                + "AND e.id_empleado = em.id_empleado "
                + "AND e.id_tipo_procesador = tp.id_tipo_procesador "
                + "AND e.id_tipo_ram = tr.id_tipo_ram "
                + "AND e.id_tipo_disco_duro = td.id_tipo_disco_duro "
                + "AND e.id_sistema_operativo = so.id_sistema_operativo "
                + "AND e.id_equipo = " + id + ";";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la consulta sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la consulta
            this.objResultSet = this.objPreparedStatement.executeQuery();

            //se itera el objeto para obtener los valores
            while (this.objResultSet.next()) {
                //se instancia el objeto EstadoOperativo
                EstadoOperativo objEstadoOperativo = new EstadoOperativo();
                objEstadoOperativo.setIdEstadoOperativo(this.objResultSet.getInt("id_estado_operativo"));
                objEstadoOperativo.setEstadoOperativo(this.objResultSet.getString("estado_operativo"));
                objEquipo.setObjEstadoOperativo(objEstadoOperativo);

                objEquipo.setIdEquipo(this.objResultSet.getInt("id_equipo"));//id_equipo
                objEquipo.setNumeroSerie(this.objResultSet.getString("numero_serie"));
                objEquipo.setHostname(this.objResultSet.getString("hostname"));

                //se instancia el objeto TipoEquipo
                TipoEquipo objTipoEquipo = new TipoEquipo();
                objTipoEquipo.setIdTipoEquipo(this.objResultSet.getInt("id_tipo_equipo"));
                objTipoEquipo.setTipoEquipo(this.objResultSet.getString("tipo_equipo"));
                objEquipo.setObjTipoEquipo(objTipoEquipo);

                //se instancia el objeto Marca 
                Marca objMarca = new Marca();
                objMarca.setIdMarca(this.objResultSet.getInt("id_marca"));
                objMarca.setMarca(this.objResultSet.getString("marca"));
                objEquipo.setObjMarca(objMarca);
                
                //se instancia el objeto Empleado 
                Empleado objEmpleado = new Empleado();
                objEmpleado.setIdEmpleado(this.objResultSet.getInt("id_empleado"));
                objEmpleado.setNombre(this.objResultSet.getString("nombre"));
                objEmpleado.setApellidoPaterno(this.objResultSet.getString("apellido_paterno"));
                objEmpleado.setApellidoMaterno(this.objResultSet.getString("apellido_materno"));
                objEquipo.setObjEmpleado(objEmpleado);

                //se instancia el objeto TipoProcesador
                TipoProcesador objTipoProcesador = new TipoProcesador();
                objTipoProcesador.setIdTipoProcesador(this.objResultSet.getInt("id_tipo_procesador"));
                objTipoProcesador.setTipoProcesador(this.objResultSet.getString("tipo_procesador"));
                objEquipo.setObjTipoProcesador(objTipoProcesador);
                objEquipo.setVelocidadProcesador(this.objResultSet.getInt("velocidad_procesador"));

                //se instancia el objeto TipoRam 
                TipoRam objTipoRam = new TipoRam();
                objTipoRam.setIdTipoRam(this.objResultSet.getInt("id_tipo_ram"));
                objTipoRam.setTipoRam(this.objResultSet.getString("tipo_ram"));
                objEquipo.setObjTipoRam(objTipoRam);
                objEquipo.setCapacidadRam(this.objResultSet.getInt("capacidad_ram"));

                //se instancia el objeto TipoDiscoDuro
                TipoDiscoDuro objTipoDiscoDuro = new TipoDiscoDuro();
                objTipoDiscoDuro.setIdTipoDiscoDuro(this.objResultSet.getInt("id_tipo_disco_duro"));
                objTipoDiscoDuro.setTipoDiscoDuro(this.objResultSet.getString("tipo_disco_duro"));
                objEquipo.setObjTipoDiscoDuro(objTipoDiscoDuro);
                objEquipo.setCapacidadDiscoDuro(this.objResultSet.getInt("capacidad_disco_duro"));

                //se instancia el objeto SistemaOperativo
                SistemaOperativo objSO = new SistemaOperativo();
                objSO.setIdSistemaOperativo(this.objResultSet.getInt("id_sistema_operativo"));
                objSO.setSistemaOperativo(this.objResultSet.getString("sistema_operativo"));
                objEquipo.setObjSistemaOperativo(objSO);
            }
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos
            Conexion.close(this.objResultSet);
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return objEquipo;
    }//fin metodo

    @Override
    public int actualizar(Equipo objEquipo) {
        //sentencia sql
        String sql = "UPDATE equipo "
                + "SET fecha_modificacion_registro = CURDATE(),"
                + "id_estado_operativo = ?, "//1
                + "hostname = ?, "//2
                + "id_empleado = ?, "//3
                + "id_tipo_procesador = ?, "//4
                + "velocidad_procesador = ?, "//5
                + "id_tipo_ram = ?, "//6
                + "capacidad_ram = ?, "//7
                + "id_tipo_disco_duro = ?, "//8
                + "capacidad_disco_duro = ?, "//9
                + "id_sistema_operativo = ? "//10
                + "WHERE id_equipo =  ?";//11

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);

            //se agrega la informacion al objeto
            this.objPreparedStatement.setInt(1, objEquipo.getObjEstadoOperativo().getIdEstadoOperativo());
            this.objPreparedStatement.setString(2, objEquipo.getHostname());            
            this.objPreparedStatement.setInt(3, objEquipo.getObjEmpleado().getIdEmpleado());
            this.objPreparedStatement.setInt(4, objEquipo.getObjTipoProcesador().getIdTipoProcesador());
            this.objPreparedStatement.setInt(5, objEquipo.getVelocidadProcesador());
            this.objPreparedStatement.setInt(6, objEquipo.getObjTipoRam().getIdTipoRam());
            this.objPreparedStatement.setInt(7, objEquipo.getCapacidadRam());
            this.objPreparedStatement.setInt(8, objEquipo.getObjTipoDiscoDuro().getIdTipoDiscoDuro());
            this.objPreparedStatement.setInt(9, objEquipo.getCapacidadDiscoDuro());
            this.objPreparedStatement.setInt(10, objEquipo.getObjSistemaOperativo().getIdSistemaOperativo());
            this.objPreparedStatement.setInt(11, objEquipo.getIdEquipo());

            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha actualizado el Equipo (" + objEquipo.getHostname() + "), exitosamente ";
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
        String sql = "DELETE FROM equipo WHERE id_equipo = " + id + ";";

        try {
            //se efectua la conexion a la base de datos
            this.objConnection = this.objConexion.Conexion();
            //se preprara la sentencia sql
            this.objPreparedStatement = this.objConnection.prepareStatement(sql);
            //se ejecuta la sentencia sql
            this.filas = this.objPreparedStatement.executeUpdate();
            //dependiendo del resultado del executeUpdate, se envia mensaje
            if (this.filas > 0) {
                this.mensaje = ":) Se ha eliminado el Equipo exitosamente";
            } 
        } catch (SQLException ex) {
            this.mensaje = "Error -SQLException-: " + ex.getMessage();
        } finally {
            //se cierran los objetos de conexion a la base de datos que estan abiertos            
            Conexion.close(this.objPreparedStatement);
            Conexion.close(this.objConnection);
        }
        return this.filas;
    }//fin metdodo

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}//fin clase
