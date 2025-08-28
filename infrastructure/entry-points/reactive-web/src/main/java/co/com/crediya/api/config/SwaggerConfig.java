package co.com.crediya.api.config;

import co.com.crediya.api.Handler;
import co.com.crediya.api.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Crediya uath-service API",
                version = "1.0",
                description = "Documentación de la API de Gestion de usuarios para crediya"
        )
)
public class SwaggerConfig {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    produces = {"application/json"},
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "listenPOSTCrearUsuario",
                    operation = @Operation(
                            operationId = "crearUsuario",
                            summary = "Crea una un nuevo usuario en el sistema",
                            tags = {"Usuario"},
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Datos del nuevo Usuario",
                                    content = @Content(schema = @Schema(implementation = UsuarioDto.class))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Usuario creada con éxito",
                                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(type = "string", example = "Usuario creado exitosamente: 123"))
                                    ),
                                        @ApiResponse(
                                                responseCode = "400",
                                                description = "Error de validación",
                                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(type = "string", example = "{\n" +
                                                                "  \"errors\": {\n" +
                                                                "    \"email\": \"El email debe estar enun formato valido\"\n" +
                                                                "  },\n" +
                                                                "  \"status\": 400\n" +
                                                                "}"))
                                        ),
                                    @ApiResponse(
                                            responseCode = "500",
                                            description = "Error interno del servidor",
                                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(type = "string", example = "Error al crear la nueva solicitud\n" +
                                                            " Failed to obtain R2DBC Connection"))
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> swaggerRouter(Handler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/usuarios", handler::listenPOSTCrearUsuario)
                .build();
    }
}

