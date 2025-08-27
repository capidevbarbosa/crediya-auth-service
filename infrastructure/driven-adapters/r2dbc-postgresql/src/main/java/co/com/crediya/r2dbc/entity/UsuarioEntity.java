package co.com.crediya.r2dbc.entity;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("usuarios")
public class UsuarioEntity {
    @Id
    @Column("id_usuario")
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private Long documento_identidad;
    private Long telefono;
    private Long id_rol;
    private Double salario_base;
}
