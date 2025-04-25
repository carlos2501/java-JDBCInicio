package controladores;


import entidades.Cliente;
import repos.ClienteRepoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClienteCtrl {

    private final ClienteRepoImpl clienteRepo = new ClienteRepoImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void listarClientes(){
        List<Cliente> listaClientes = clienteRepo.listarTodos();
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }

    public Integer crearNuevoCliente() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        System.out.println("Ingrese el nombre del cliente: ");
        cliente.setNombreCliente(scanner.nextLine());
        System.out.println("Ingrese el nombre de contacto del cliente: ");
        cliente.setNombreContacto(scanner.nextLine());
        System.out.println("Ingrese el apellido de contacto del cliente: ");
        cliente.setApellidoContacto(scanner.nextLine());
        System.out.println("Ingrese el telefono del cliente: ");
        cliente.setTelefono(scanner.nextLine());
        System.out.println("Ingrese el fax del cliente: ");
        cliente.setFax(scanner.nextLine());
        System.out.println("Ingrese el linea direccion 1 del cliente: ");
        cliente.setLineaDireccion1(scanner.nextLine());
        System.out.println("Ingrese el linea direccion 2 del cliente: ");
        cliente.setLineaDireccion2(scanner.nextLine());
        System.out.println("Ingrese la ciudad del cliente: ");
        cliente.setCiudad(scanner.nextLine());
        System.out.println("Ingrese la region del cliente: ");
        cliente.setRegion(scanner.nextLine());
        System.out.println("Ingrese el pais del cliente: ");
        cliente.setPais(scanner.nextLine());
        System.out.println("Ingrese el codigo postal del cliente: ");
        cliente.setCodigoPostal(scanner.nextLine());
        System.out.println("Ingrese el limite de credito del cliente: ");
        cliente.setLimiteCredito(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Ingrese el codigo de empleado del cliente: ");
        cliente.setRepVentas(scanner.nextInt());
        scanner.nextLine();
        return clienteRepo.guardar(cliente);
    }

    public void leerCliente(int idCliente) throws SQLException {
        Optional<Cliente> cliente = clienteRepo.buscarPorId(idCliente);
        if(cliente.isPresent()){
            Cliente cli = cliente.get();
            System.out.printf("\nNombre [%s] Dirección1 [%s] Dirección 2 [%s] Ciudad [%s] CP [%s]", cli.getNombreCliente(),
                    cli.getLineaDireccion1(), cli.getLineaDireccion2(), cli.getCiudad(), cli.getCodigoPostal());
        } else {
            System.out.println("No existe el cliente con el código de cliente " + idCliente);
        }
    }

    public void modificarCliente(int idCliente) throws SQLException {
        // 1 leo el cliente por su id
        Optional<Cliente> cliente = clienteRepo.buscarPorId(idCliente);
        if (cliente.isPresent()){
            // 2 presento los datos del cliente con cada campo numerado para poder preguntar qué campo quiero cambiar
            Cliente cli = cliente.get();

            // Opción A: fácil y larga pero más eficiente
            // long inicio = System.currentTimeMillis(); des-comentar para medir tiempos de ejecución
            System.out.println("0 - Terminar las modificaciones");
            System.out.println(cli.toFicha());
            // System.out.println("Con toString. Duración: " + (System.currentTimeMillis() - inicio) +" ms.");

            // Opción B: corta pero compleja mediante reflexión y menos eficiente en tiempo de ejecución
            /*
            inicio = System.currentTimeMillis();
            Field[] campos = cliente.get().getClass().getDeclaredFields();
            int i = 1;
            for (Field campo : campos) {
                campo.setAccessible(true);
                System.out.println(i++ + " - " + campo.getName() + ": " + campo.get(cli));
            }
            System.out.println("Con reflexión. Duración: " + (System.currentTimeMillis() - inicio) + " ms.");
             */

            // Creamos un bucle para que conteste un nro. de campo válido a deje de modificar
            boolean flag = true;
            while (flag) {
                // 3 preguntar qué campo quiere cambiar
                System.out.println("Dígame el numero del campo que desea modificar:");
                Scanner scanner = new Scanner(System.in);
                int campo = scanner.nextInt();
                scanner.nextLine();
                if (campo != 0) {
                    // 4 preguntar el nuevo valor
                    System.out.println("Que valor le desea dar al campo?");
                    String valor = scanner.nextLine();
                    // 5 asignar el nuevo valor al campo
                    switch (campo) {
                        case 1:
                            System.out.println("El código de cliente no se puede modificar");
                            break;
                        case 2:
                            cli.setNombreCliente(valor);
                            break;
                        case 3:
                            cli.setNombreContacto(valor);
                            break;
                        case 4:
                            cli.setApellidoContacto(valor);
                            break;
                        case 5:
                            cli.setTelefono(valor);
                            break;
                        case 6:
                            cli.setFax(valor);
                            break;
                        case 7:
                            cli.setLineaDireccion1(valor);
                            break;
                        case 8:
                            cli.setLineaDireccion2(valor);
                            break;
                        case 9:
                            cli.setCiudad(valor);
                            break;
                        case 10:
                            cli.setRegion(valor);
                            break;
                        case 11:
                            cli.setPais(valor);
                            break;
                        case 12:
                            cli.setCodigoPostal(valor);
                            break;
                        case 13:
                            cli.setRepVentas(Integer.valueOf(valor));
                            break;
                        case 14:
                            cli.setLimiteCredito(Float.valueOf(valor));
                            break;
                        default:
                            System.out.println("Ese campo no existe.");
                    }
                } else {
                    flag = false;
                }

            }
            // repetir el bucle 3 a 5 hasta que el usuario no quiera modificar más campos

            // guardar el cliente modificado
            clienteRepo.guardar(cli);

        } else {
            System.out.println("No existe el cliente con el código de cliente " + idCliente);
        }
    }

    public void borrarCliente(Integer idCliente) {
        System.out.println("\n--- BORRAR CLIENTE ---");
        try {
            // 1. Obtener datos del cliente
            Optional<Cliente> clienteOpt = clienteRepo.buscarPorId(idCliente);

            if (clienteOpt.isEmpty()) {
                System.out.println("❌ Cliente no encontrado");
                return;
            }

            // 2. Mostrar información del cliente y pedimos confirmación de borrado
            System.out.println("\n⚠️ DATOS DEL CLIENTE A BORRAR ⚠️");
            System.out.println("Nombre: " + clienteOpt.get().getNombreCliente());
            System.out.println("Teléfono: " + clienteOpt.get().getTelefono());
            System.out.println("----------------------------------");
            System.out.print("¿Confirmar borrado? (S/N): ");
            String confirmacion = scanner.nextLine();

            // 3. Procesar respuesta
            if (confirmacion.equalsIgnoreCase("S")) {
                clienteRepo.eliminar(idCliente);
                System.out.println("✅ Cliente borrado correctamente");
            } else {
                System.out.println("❌ Operación cancelada");
            }

        } catch (Exception e) {
            System.err.println("Error al borrar cliente: " + e.getMessage());
        }
    }
}
