package co.com.crediya.usecase.usuario;

import co.com.crediya.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public interface IUsuarioUseCase {
    Mono<Usuario> saveUsuario(Usuario usuario);
}
