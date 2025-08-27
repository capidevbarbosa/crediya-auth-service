package co.com.crediya.r2dbc;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import co.com.crediya.r2dbc.entity.UsuarioEntity;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
    Usuario, UsuarioEntity, Long, MyReactiveRepository> implements UsuarioRepository {
    private static final Logger logger = LoggerFactory.getLogger(MyReactiveRepositoryAdapter.class);



    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Usuario.class));
    }

    @Override
    public Mono<Usuario> saveUsuario(Usuario usuario) {
        logger.info("Iniciando la creación del usuario " + usuario.toString());
        return save(usuario);
    }
}
