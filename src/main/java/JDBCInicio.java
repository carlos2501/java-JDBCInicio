import controladores.ClienteCtrl;
import entidades.Cliente;
import repos.EmpleadoRepo;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * Ejercicio:
 *  crear un menu inicial que se visualizará al ejecutar la aplicación.
 *  La cabecera del menu es "Bienvenido a la aplicación de jardineria" que se mostrara subrayado con "="
 *  Este menu tiene as opciones:
 *   1 - Crear nuevo cliente
 *   2 - Leer modificar cliente mostrando su ficha
 *   3 - Borrar cliente
 *   4 - Listar datos clientes
 *   5 - Listar clientes indicando su oficina
 *
 *   Indique la opción deseada:
 *
 * El código del menu se incluirá en un método independiente para facilitar su posterior modificación y será llamado
 * desde dentro del main.
 */
public class JDBCInicio {
    public static void main(String[] args) throws SQLException {

        ClienteCtrl clienteCtrl = new ClienteCtrl();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la aplicación de jardinería");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Leer cliente");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Borrar cliente");
        System.out.println("5. Listar clientes");
        System.out.println("6. Listar clientes indicando su oficina");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion del menu: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch(opcion){
            case 1:
                System.out.println("Opción no implementada");
                break;
            case 2:
                System.out.println("Indique el Id del cliente ");
                int idCliente = scanner.nextInt();
                clienteCtrl.leerCliente(idCliente);
                break;
            case 3:
                System.out.println("Opción no implementada");
                break;
            case 4:
                System.out.println("Opción no implementada");
                break;
            case 5:
                clienteCtrl.listarClientes();
                break;
            case 6:
                System.out.println("Opción no implementada");
                break;
            default:

                break;
        }






 /*
        ClienteCtrl clienteCtrl = new ClienteCtrl();

        clienteCtrl.listarClientes();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la aplicacion de jardineria");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Leer y modificar cliente");
        System.out.println("3. Borrar cliente");
        System.out.println("4. Listar clientes");
        System.out.println("5. Listar clientes indicando su oficina");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion del menu: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch(opcion){
            case 1:
                crearNuevoCliente(scanner, clienteRepository);
                break;
            case 2:
                leerYModificarCliente(scanner, clienteRepository);
                break;
            case 3:
                borrarCliente(scanner, clienteRepository);
                break;
            case 4:
                listarClientes(clienteRepository);
                break;
            case 5:
                listarClientesPorOficina(scanner, clienteRepository);
                break;
            default:
                break;
        }
*/
    }
}
