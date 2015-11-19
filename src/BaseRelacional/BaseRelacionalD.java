package BaseRelacional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class BaseRelacionalD {
    
    Connection conexion;
    ResultSet rs;

    public static void main(String[] args) {
        
        BaseRelacionalD brd = new BaseRelacionalD().conectar();
        brd.tabla();
    }


    public void tabla() {
        try {

            PreparedStatement pS = conexion.prepareStatement("select productos.* from productos where prezo>?");
            pS.setInt(1, 5);
            rs = (ResultSet) pS.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + ", ");
                System.out.print(rsmd.getColumnTypeName(i) + ", ");
                System.out.println(rsmd.getColumnDisplaySize(i));
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("SQLException "+ ex);
        }
    }
   public BaseRelacionalD conectar() {
        try {
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";
            conexion = DriverManager.getConnection(BaseDeDatos, "hr", "hr");
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
   public Connection getConexion() {
        return conexion;
    }
   public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

}
