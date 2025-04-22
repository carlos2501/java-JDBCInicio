package repos;

import entidades.Empleado;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepo {
    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    };

    /**
     * Método para mapear el resultado de la consulta SELECT * FROM empleado a una lista de objetos Empleado.
     */
    public List<Empleado> listaDeEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigoEmpleado(resultSet.getInt("codigo_empleado"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setApellido1(resultSet.getString("apellido1"));
                empleado.setApellido2(resultSet.getString("apellido2")); // Puede ser NULL
                empleado.setExtension(resultSet.getString("extension"));
                empleado.setEmail(resultSet.getString("email"));
                empleado.setCodigoOficina(resultSet.getString("codigo_oficina"));
                empleado.setCodigoJefe(resultSet.getObject("codigo_jefe") != null ? resultSet.getInt("codigo_jefe") : null); // Manejo de NULL
                empleado.setPuesto(resultSet.getString("puesto")); // Puede ser NULL

                empleados.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de errores, puedes mejorarlo con logs
        }

        return empleados;
    }

}
