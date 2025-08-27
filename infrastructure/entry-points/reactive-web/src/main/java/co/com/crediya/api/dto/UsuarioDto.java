package co.com.crediya.api.dto;

public record UsuarioDto(Long id_usuario
        , String nombre
        , String apellido
        , String email
        , Long documento_identidad
        , Long telefono
        , Long id_rol
        , Double salario_base) {
}
