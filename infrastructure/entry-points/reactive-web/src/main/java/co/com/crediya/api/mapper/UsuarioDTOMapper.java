package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.CreateUsuarioDto;
import co.com.crediya.api.dto.UsuarioDto;
import co.com.crediya.model.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    UsuarioDto toResponse(Usuario usuario);
    Usuario toModel(CreateUsuarioDto createUsuarioDto);
}
