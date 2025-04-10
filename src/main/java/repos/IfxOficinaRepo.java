package repos;

import modelos.Oficina;

import java.util.List;

public interface IfxOficinaRepo {
    List<Oficina> listaDeOficinas();
    Oficina obtenerPorCodigo(int codigoOficina);
}
