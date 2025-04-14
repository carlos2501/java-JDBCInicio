import repos.ClienteRepo;
import repos.EmpleadoRepo;

import java.sql.*;

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
    }
}
