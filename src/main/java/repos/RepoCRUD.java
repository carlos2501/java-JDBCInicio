package repos;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RepoCRUD<T> {
    List<T> listarTodos();
    Optional<T> buscarPorId(int id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id);
}
