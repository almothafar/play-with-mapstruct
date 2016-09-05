package mappers;

import controllers.viewModels.UserDTO;
import models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(config = CentralConfig.class, uses = {AccountMapper.class})
public interface UserMapper {
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User map(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO map(User user);

    List<User> map(ArrayList<UserDTO> userDTOs);

    List<UserDTO> map(List<User> users);
}


