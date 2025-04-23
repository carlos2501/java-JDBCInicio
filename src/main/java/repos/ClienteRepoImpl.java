package repos;

import entidades.Cliente;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepoImpl implements RepoCRUD<Cliente>{

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement statement = obtenerConexion().createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(cargarCliente(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) throws SQLException {
        String qry = "SELECT * FROM cliente WHERE codigo_cliente = ?";
        try (PreparedStatement pstmt = obtenerConexion().prepareStatement(qry)) {
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if(rs.next()) {
                return Optional.of(cargarCliente(rs));
            } else {
                return Optional.empty();
            }
        }
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
