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
    public Optional<Oficina> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Oficina oficina) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
