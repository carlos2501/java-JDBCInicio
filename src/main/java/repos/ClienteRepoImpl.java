package repos;

import modelos.Cliente;
import modelos.Empleado;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepoImpl implements RepoCRUD<Cliente>{

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    };

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                clientes.add(cargarCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public Cliente buscarPorId(int id) {
        return null;
    }

    @Override
    public void guardar(Cliente cliente) {

    }

    @Override
    public void eliminar(Long id) {

    }

    private Cliente cargarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
        cliente.setNombreCliente(rs.getString("nombre_cliente"));
        cliente.setNombreContacto(rs.getString("nombre_contacto"));
        cliente.setApellidoContacto(rs.getString("apellido_contacto"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setFax(rs.getString("fax"));
        cliente.setLineaDireccion1(rs.getString("linea_direccion1"));
        cliente.setLineaDireccion2(rs.getString("linea_direccion2"));
        cliente.setCiudad(rs.getString("ciudad"));
        cliente.setRegion(rs.getString("region"));
        cliente.setPais(rs.getString("pais"));
        cliente.setCodigoPostal(rs.getString("codigo_postal"));
        cliente.setRepVentas(rs.getInt("rep_ventas"));
        cliente.setLimiteCredito(rs.getFloat("limite_credito"));
        return cliente;
    }
}
