package co.com.crediya.usecase.usuario.impl;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import co.com.crediya.usecase.usuario.IUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UsuarioUseCase implements IUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Mono<Usuario> saveUsuario(Usuario usuario) {
        return usuarioRepository.saveUsuario(usuario);
    }
}
