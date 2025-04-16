package repos;

import modelos.Oficina;

import java.util.List;

public class OficinaRepoImpl implements RepoCRUD<Oficina>{
    @Override
    public List<Oficina> listarTodos() {
        return List.of();
    }

    @Override
    public Oficina buscarPorId(int id) {
        return null;
    }

    @Override
    public void guardar(Oficina oficina) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
