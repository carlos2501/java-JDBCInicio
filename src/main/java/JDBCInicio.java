import repos.ClienteRepo;
import repos.EmpleadoRepo;

import java.sql.*;
import java.util.Map;

public class JDBCInicio {
    public static void main(String[] args) throws SQLException {

        ClienteRepo repoCli = new ClienteRepo();
        EmpleadoRepo repoEmp = new EmpleadoRepo();


        System.out.println("-------------- Lista de clientes ------------");
        repoCli.listaDeClientes().forEach(System.out::println);
        System.out.println("-------------- Lista de empleados ------------");
        repoEmp.listaDeEmpleados().forEach(System.out::println);
        System.out.println("-------------- Lista de clientes con la oficina de ventas ------------");
        repoCli.listaDeClientesConOficina().forEach(System.out::println);
        System.out.println("-------------- Ficha de clientes con la oficina de ventas ------------");
        imprimirFichasClientes(repoCli.fichaClientesConOficina());
    }

    public static void imprimirFichasClientes(Map<Integer, Map<String, String>> fichas) {
        System.out.println("=== LISTADO DE CLIENTES CON OFICINA ASOCIADA ===");

        for (Map.Entry<Integer, Map<String, String>> entrada : fichas.entrySet()) {
            int codigoCliente = entrada.getKey();
            Map<String, String> detalles = entrada.getValue();

            System.out.println("\n▬ Cliente #" + codigoCliente);
            System.out.println("├─ Nombre: " + detalles.get("nombre_cliente"));
            System.out.println("├─ Teléfono: " + detalles.get("telefono"));
            System.out.println("├─ Representante: " + detalles.get("representante_ventas"));
            System.out.println("├─ Código Oficina: " + detalles.get("oficina"));
            System.out.println("└─ Código Postal: " + detalles.get("codigo_postal"));
        }

        System.out.println("\nTotal clientes mostrados: " + fichas.size());
    }
}
