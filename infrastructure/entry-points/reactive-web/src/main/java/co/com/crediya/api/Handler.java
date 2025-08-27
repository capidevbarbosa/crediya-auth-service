package co.com.crediya.api;

import co.com.crediya.api.dto.CreateUsuarioDto;
import co.com.crediya.api.mapper.UsuarioDTOMapper;
import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.usecase.usuario.IUsuarioUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Handler {
    private final IUsuarioUseCase useCase;
    private final UsuarioDTOMapper mapper;
    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(CreateUsuarioDto.class)
                .flatMap(dto -> {
                    // 1. Validar el DTO
                    Set<ConstraintViolation<CreateUsuarioDto>> violations = validator.validate(dto);

                    if (!violations.isEmpty()) {
                        // 2. Transformar errores en un JSON amigable
                        Map<String, String> errors = violations.stream()
                                .collect(Collectors.toMap(
                                        v -> v.getPropertyPath().toString(),
                                        ConstraintViolation::getMessage
                                ));

                        return ServerResponse.badRequest()
                                .bodyValue(Map.of(
                                        "status", HttpStatus.BAD_REQUEST.value(),
                                        "errors", errors
                                ));
                    }


                    Usuario usuario = mapper.toModel(dto);

                    return useCase.saveUsuario(usuario)  // Aquí ya devuelves un Mono<Usuario>
                            .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED)
                                    .bodyValue("Usuario creado exitosamente: " + saved.getId_usuario()))
                            .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .bodyValue("Error al crear el usuario: " + e.getMessage()));
                });
    }
}
