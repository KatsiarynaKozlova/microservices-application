package by.me.mapper;

import by.me.dto.UserDTO;
import by.me.model.UserCredential;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(UserCredential userCredential);
    UserCredential toUserCredential(UserDTO userDTO);
}
