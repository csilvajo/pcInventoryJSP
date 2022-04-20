package cl.multiverso.pcInventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Conexion, establece la conexion con la base de datos.
 *
 * @author Carlos (09-03-2021)
 */
public class Conexion {

    //variables de conexion
    private Connection objConnection;
    private static final String URL = "jdbc:mysql://localhost:3306/pcinventory?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "admin";

    /**
     * Metodo que permite establecer la conexion con la base de datos
     *
     * @return el objeto de conexion
     */
    public Connection Conexion() {
        try {
            //driver de la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            this.objConnection = java.sql.DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Error conexion : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.objConnection;
    }//fin metodo

    /**
     * Metodo que cierra el objeto ResultSet
     *
     * @param objResultSet
     */
    public static void close(ResultSet objResultSet) {
        try {
            if (objResultSet != null) {
                if (objResultSet.isClosed() != true) {
                    objResultSet.close();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//fin metodo

    /**
     * Metodo que cierra el objeto PreparedStatement
     *
     * @param objPreparedStatement
     */
    public static void close(PreparedStatement objPreparedStatement) {
        try {
            if (objPreparedStatement != null) {
                if (objPreparedStatement.isClosed() != true) {
                    objPreparedStatement.close();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//fin metodo

    /**
     * Metodo que cierra el objeto de conexion a la BD
     *
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                if (conn.isClosed() != true) {
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//fin metodo

}//fin clase
