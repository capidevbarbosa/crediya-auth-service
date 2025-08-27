package co.com.crediya.model.usuario;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Usuario {
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private Long documento_identidad;
    private Long telefono;
    private Long id_rol;
    private Double salario_base;
}
