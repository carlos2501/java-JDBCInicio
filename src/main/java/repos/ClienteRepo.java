package repos;

import modelos.Cliente;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ClienteRepo class provides methods for interacting with the Cliente database table.
 * It allows retrieving client information by ID, listing all clients, and updating client information.
 */
public class ClienteRepo {

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }
    
    public Cliente obtenerPorId(int id) throws SQLException {
        String qry ="SELECT * FROM Cliente WHERE codigo_cliente = ?";
        Cliente cliente;
        try (PreparedStatement stmt = obtenerConexion().prepareStatement(qry)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cliente = cargarCliente(rs);
        }
        return cliente;
    }

    public List<Cliente> listaDeClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = cargarCliente(rs);

                // Manejo de valores que pueden ser nulos
                int repVentas = rs.getInt("rep_ventas");
                if (rs.wasNull()) {
                    cliente.setRepVentas(null);
                } else {
                    cliente.setRepVentas(repVentas);
                }

                float limiteCredito = rs.getFloat("limite_credito");
                if (rs.wasNull()) {
                    cliente.setLimiteCredito(null);
                } else {
                    cliente.setLimiteCredito(limiteCredito);
                }

                clientes.add(cliente);
            }
        }
        return clientes;
    }

    /*
    public List<Cliente> listaDeClientesPorRegion(String region) throws SQLException {}
    */
    public List<String> listaDeClientesConOficina() {
        List<String> listaClientes = new ArrayList<>();
        String qry = """
                SELECT cl.codigo_cliente, cl.nombre_cliente, cl.telefono, CONCAT(e.nombre, ' ', e.apellido1) AS "Representante Ventas", o.codigo_oficina, o.codigo_postal
                FROM cliente cl
                    JOIN empleado e ON cl.rep_ventas = e.codigo_empleado
                    JOIN oficina o ON e.codigo_oficina = o.codigo_oficina
                """;
        try(Statement stmt = obtenerConexion().createStatement();
            ResultSet rs = stmt.executeQuery(qry)) {

            while (rs.next()) {
                listaClientes.add(
                        rs.getInt("codigo_cliente") + " - " + 
                        rs.getString("nombre_cliente") + " - " +
                        rs.getString("telefono") + " " + 
                        rs.getString("Representante Ventas") + " " + 
                        rs.getString("codigo_oficina") + " " + 
                        rs.getString("codigo_postal")
                );
                /*System.out.println(rs.getInt("codigo_cliente") + " - " +
                        rs.getString("nombre_cliente") + " - " +
                        rs.getString("telefono") + " " + rs.getString("Representante Ventas") + " " + rs.getString("codigo_oficina") + " " + rs.getString("codigo_postal"));
            */}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public Map<Integer, Map<String, String>> fichaClientesConOficina() {
        Map<Integer, Map<String, String>> listaFichasClientes = new HashMap<>();
        String qry = """
                SELECT cl.codigo_cliente, cl.nombre_cliente, cl.telefono, CONCAT(e.nombre, ' ', e.apellido1) AS "Representante Ventas", o.codigo_oficina, o.codigo_postal
                FROM cliente cl
                    JOIN empleado e ON cl.rep_ventas = e.codigo_empleado
                    JOIN oficina o ON e.codigo_oficina = o.codigo_oficina
                """;
        try(Statement stmt = obtenerConexion().createStatement();
            ResultSet rs = stmt.executeQuery(qry)) {

            while (rs.next()) {
                Map<String, String> ficha = new HashMap<>();
                ficha.put("nombre_cliente", rs.getString("nombre_cliente"));
                ficha.put("telefono", rs.getString("telefono"));
                ficha.put("representante_ventas", rs.getString("Representante Ventas"));
                ficha.put("oficina", rs.getString("codigo_oficina"));
                ficha.put("codigo_postal", rs.getString("codigo_postal"));
                listaFichasClientes.put(rs.getInt("codigo_cliente"), ficha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFichasClientes;
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


    /**
     * Actualiza un cliente en la base de datos con la información proporcionada.
     *
     * @param cliente El objeto Cliente con los datos actualizados a ser almacenados en la base de datos.
     */
    public void actualizarCliente(Cliente cliente) {
        String query = "UPDATE cliente SET " +
                "nombre_cliente = ?, " +
                "nombre_contacto = ?, " +
                "apellido_contacto = ?, " +
                "telefono = ?, " +
                "fax = ?, " +
                "linea_direccion1 = ?, " +
                "linea_direccion2 = ? " +
                "WHERE codigo_cliente = ?";
        try (PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, cliente.getNombreCliente());
            stmt.setString(2, cliente.getNombreContacto());
            stmt.setString(3, cliente.getApellidoContacto());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getFax());
            stmt.setString(6, cliente.getLineaDireccion1());
            stmt.setString(7, cliente.getLineaDireccion2());
            stmt.setInt(8, cliente.getCodigoCliente());
            System.out.println("Cliente actualizado correctamente en la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente", e);
        }
    }
}

