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
    public Optional<Cliente> buscarPorId(Integer id) throws SQLException {
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
    public Integer guardar(Cliente cliente) throws SQLException {
        String qry;
        String qryIns ="""
                INSERT INTO cliente (
                    nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, linea_direccion1,
                    linea_direccion2, ciudad, region, pais, codigo_postal, rep_ventas, limite_credito, codigo_Cliente)
                VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                """;
        String qryUpd ="""
            UPDATE cliente SET nombre_cliente = ?, nombre_contacto = ?, apellido_contacto = ?, telefono = ?, 
                fax = ?, linea_direccion1 = ?, linea_direccion2 = ?, ciudad = ?, region = ?, pais = ?, 
                codigo_postal = ?, rep_ventas = ?, limite_credito = ? 
            WHERE codigo_cliente = ?
            """;
        // Para saber si hay que actualizar o insertar, miramos si existe el código del cliente
        if (cliente.getCodigoCliente() > 0) {
            qry = qryUpd;
        } else {
            qry = qryIns;
            cliente.setCodigoCliente(getUltimoCodigoCliente());
        }
        try (PreparedStatement pstmt = obtenerConexion().prepareStatement(qry)) {
            pstmt.setString(1, cliente.getNombreCliente());
            pstmt.setString(2, cliente.getNombreContacto());
            pstmt.setString(3, cliente.getApellidoContacto());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getFax());
            pstmt.setString(6, cliente.getLineaDireccion1());
            pstmt.setString(7, cliente.getLineaDireccion2());
            pstmt.setString(8, cliente.getCiudad());
            pstmt.setString(9, cliente.getRegion());
            pstmt.setString(10, cliente.getPais());
            pstmt.setString(11, cliente.getCodigoPostal());
            pstmt.setInt(12, cliente.getRepVentas());
            pstmt.setFloat(13, cliente.getLimiteCredito());
            pstmt.setInt(14, cliente.getCodigoCliente());
            pstmt.executeUpdate();

        }
        return cliente.getCodigoCliente();
    }

    @Override
    public void eliminar(Integer id) {
        String qry ="DELETE FROM cliente WHERE codigo_cliente = ?";
        try (PreparedStatement pstmt = obtenerConexion().prepareStatement(qry)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    
    private Integer getUltimoCodigoCliente() throws SQLException {
        String qry = "SELECT MAX(codigo_cliente) FROM cliente";
        try (Statement statement = obtenerConexion().createStatement();
             ResultSet rs = statement.executeQuery(qry)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            } else {
                return 1;
            }
        }
    }
}
