import repos.ClienteRepo;
import repos.EmpleadoRepo;
import repos.IfxOficinaRepo;
import repos.OficnaRepo;
import util.ConexionBD;

import java.sql.*;

public class JDBCInicio {
    public static void main(String[] args) throws SQLException {

        ClienteRepo repoCli = new ClienteRepo();
        EmpleadoRepo repoEmp = new EmpleadoRepo();
        IfxOficinaRepo repoOficina = new OficnaRepo();

        System.out.println("-------------- Lista de clientes ------------");
        repoCli.listaDeClientes().forEach(System.out::println);
        System.out.println("-------------- Lista de empleados ------------");
        repoEmp.listaDeEmpleados().forEach(System.out::println);
        //System.out.println("-------------- Lista de oficinas ------------");
        //repoOficina.listaDeOficinas().forEach(System.out::println);
    }
}
