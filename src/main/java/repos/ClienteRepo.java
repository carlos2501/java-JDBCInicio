package repos;

import modelos.Cliente;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo {

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }

    public List<Cliente> listaDeClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
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


}

