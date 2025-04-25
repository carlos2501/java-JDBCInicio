package repos;

import entidades.Oficina;

import java.util.List;
import java.util.Optional;

public class OficinaRepoImpl implements RepoCRUD<Oficina>{
    @Override
    public List<Oficina> listarTodos() {
        return List.of();
    }

    @Override
    public Optional<Oficina> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Integer guardar(Oficina oficina) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }
}
