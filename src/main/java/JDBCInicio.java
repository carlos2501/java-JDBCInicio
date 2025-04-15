import repos.ClienteRepo;
import repos.EmpleadoRepo;

import java.sql.*;
import java.util.Map;
import java.util.Scanner;

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
        System.out.println("-------------- Modificamos un cliente ------------");
        // leemos los datos del cliente 7 (por ejemplo)
        Scanner sc = new Scanner(System.in);
        System.out.println("Código del cliente a editar: ");
        int id = sc.nextInt();
        repoCli.obtenerPorId(id);
        // modificar los campos

        // grabar los nuevos datos
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
