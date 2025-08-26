package co.com.crediya.api.dto;

import jakarta.validation.constraints.*;

public record CreateUsuarioDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe estar enun formato valido")
        String email,
        @NotNull(message = "El documento_identidad es obligatorio")
        Long documento_identidad,
        Long telefono,
        Long id_rol,
        @NotNull(message = "El salario base es obligatorio")
        @Min(value = 0, message = "El salario mínimo permitido es 1")
        @Max(value = 15000000, message = "El salario máximo permitido es 15,000,000")
        Double salario_base) {
}
