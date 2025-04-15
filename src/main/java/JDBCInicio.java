import modelos.Cliente;
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
        Cliente cliente = repoCli.obtenerPorId(id);
        // modificar los campos
        /* Ejercicio.
            Añadir el código necesario para presentar en consola los datos del cliente (cada uno en una línea) numerando
            cada campo de forma que el usuario pueda saber qué campo quiere modificar.

            Una vez visualizados los datos, el sistema pedirá al usuario que indique que el número del campo que desea
            modificar y le preguntará el nuevo valor.
         */
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("1. Código Cliente: " + cliente.getCodigoCliente());
            System.out.println("2. Nombre Cliente: " + cliente.getNombreCliente());
            System.out.println("3. Nombre Contacto: " + cliente.getNombreContacto());
            System.out.println("4. Apellido Contacto: " + cliente.getApellidoContacto());
            System.out.println("5. Teléfono: " + cliente.getTelefono());
            System.out.println("6. Fax: " + cliente.getFax());
            System.out.println("7. Línea Dirección 1: " + cliente.getLineaDireccion1());
            System.out.println("8. Línea Dirección 2: " + cliente.getLineaDireccion2());
            System.out.println("\nIndique el número del campo que desea modificar: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            System.out.println("Introduzca el nuevo valor: ");
            String nuevoValor = sc.nextLine();
            switch (opcion) {
                case 1:
                    cliente.setCodigoCliente(Integer.parseInt(nuevoValor));
                    break;
                case 2:
                    cliente.setNombreCliente(nuevoValor);
                    break;
                case 3:
                    cliente.setNombreContacto(nuevoValor);
                    break;
                case 4:
                    cliente.setApellidoContacto(nuevoValor);
                    break;
                case 5:
                    cliente.setTelefono(nuevoValor);
                    break;
                case 6:
                    cliente.setFax(nuevoValor);
                    break;
                case 7:
                    cliente.setLineaDireccion1(nuevoValor);
                    break;
                case 8:
                    cliente.setLineaDireccion2(nuevoValor);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            repoCli.actualizarCliente(cliente);
            System.out.println("\nCliente modificado:");
            System.out.println("1. Código Cliente: " + cliente.getCodigoCliente());
            System.out.println("2. Nombre Cliente: " + cliente.getNombreCliente());
            System.out.println("3. Nombre Contacto: " + cliente.getNombreContacto());
            System.out.println("4. Apellido Contacto: " + cliente.getApellidoContacto());
            System.out.println("5. Teléfono: " + cliente.getTelefono());
            System.out.println("6. Fax: " + cliente.getFax());
            System.out.println("7. Línea Dirección 1: " + cliente.getLineaDireccion1());
            System.out.println("8. Línea Dirección 2: " + cliente.getLineaDireccion2());
        } else {
            System.out.println("No se encontró ningún cliente con ese código.");
        }

        // grabar los nuevos datos
        /* Ejercicio.
            Envíar el cliente modificado al repositorio para grabar la modificación en la BBDD.
         */
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
