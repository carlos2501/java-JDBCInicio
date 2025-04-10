package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url="jdbc:mysql://localhost:3306/jardineria";
    private static String usu= "root";
    private static String clave="carlospdl%";

    private static Connection conex;

    public static Connection getConexion() throws SQLException {
        if(conex == null) {
            conex = DriverManager.getConnection(url, usu, clave);
        }
        return conex;
    }

}
