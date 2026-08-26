package org.esfe.servicios.interfaces;

import org.esfe.modelos.Usuario;


import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorId(Integer id);

    Optional<Usuario> buscarPorEmail(String email);

    Usuario createOrEdit(Usuario usuario);

    void eliminarPorId(Integer id);

    boolean existeEmail(String email);
}
