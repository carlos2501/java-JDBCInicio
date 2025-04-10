package repos;

import modelos.Oficina;

import java.util.List;

public class OficnaRepo implements IfxOficinaRepo{
    @Override
    public List<Oficina> listaDeOficinas() {
        return List.of();
    }

    @Override
    public Oficina obtenerPorCodigo(int codigoOficina) {
        return null;
    }
}
