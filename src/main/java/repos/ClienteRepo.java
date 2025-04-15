package repos;

import modelos.Cliente;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteRepo {

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }
    
    public Cliente obtenerPorId(int id) throws SQLException {
        String qry ="SELECT * FROM Cliente WHERE codigo_cliente = ?";
        try (PreparedStatement stmt = obtenerConexion().prepareStatement(qry)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Cliente cliente = cargarCliente(rs);
        }
        return null;
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
    }
}

