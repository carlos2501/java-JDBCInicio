package controladores;


import entidades.Cliente;
import repos.ClienteRepoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClienteCtrl {

    private final ClienteRepoImpl clienteRepo = new ClienteRepoImpl();

    public void listarClientes(){
        List<Cliente> listaClientes = clienteRepo.listarTodos();
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }
    public void crearNuevoCliente(){
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
        clienteRepo.guardar(cliente);
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

}
