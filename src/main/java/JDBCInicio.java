import util.ConexionBD;

import java.sql.*;

public class JDBCInicio {
    public static void main(String[] args) {
        try (Connection conex = ConexionBD.getConexion();
             Statement stmt = conex.createStatement();
             ){
            System.out.println("----------- List de Clientes -----------------");
            ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
            while(rs.next()){
                System.out.println(rs.getInt("codigo_cliente") + " | " + rs.getString("nombre_cliente"));
            }
            rs = stmt.executeQuery("SELECT * FROM empleado");
            System.out.println("-------------- Lista de Empleados ----------------");
            while(rs.next()){
                System.out.println(rs.getInt("codigo_empleado") + " | " + rs.getString("nombre"));
            }
            rs = stmt.executeQuery("SELECT * FROM oficina");
            System.out.println("-------------- Lista de Oficinas ----------------");
            while(rs.next()){
                System.out.println(rs.getString("codigo_oficina") + " | " + rs.getString("ciudad"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
