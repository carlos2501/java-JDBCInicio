package repos;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RepoCRUD<T> {
    List<T> listarTodos();
    Optional<T> buscarPorId(Integer id) throws SQLException;
    Integer guardar(T t) throws SQLException;
    void eliminar(Integer id);
}
