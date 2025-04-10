import repos.ClienteRepo;
import util.ConexionBD;

import java.sql.*;

public class JDBCInicio {
    public static void main(String[] args) throws SQLException {

        ClienteRepo repoCli = new ClienteRepo();

        System.out.println("-------------- Lista de clientes ------------");
        repoCli.listaDeClientes().forEach(System.out::println);
    }
}
