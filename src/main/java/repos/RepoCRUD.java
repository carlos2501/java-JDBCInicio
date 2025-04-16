package repos;

import java.util.List;

public interface RepoCRUD<T> {
    List<T> listarTodos();
    T buscarPorId(int id);
    void guardar(T t);
    void eliminar(Long id);
}
