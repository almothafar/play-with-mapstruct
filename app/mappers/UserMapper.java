package mappers;

import controllers.dto.UserDTO;
import models.User;
import models.users.FacebookUser;
import models.users.LocalUser;
import models.users.TwitterUser;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CentralConfig.class, uses = {AccountMapper.class})
public interface UserMapper {
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    LocalUser map(UserDTO.LocalUserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO.LocalUserDTO map(LocalUser user);

    List<UserDTO> map(List<User> users);

    FacebookUser map(UserDTO.FacebookUserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO.FacebookUserDTO map(FacebookUser user);

    TwitterUser map(UserDTO.TwitterUserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO.TwitterUserDTO map(TwitterUser user);

    default UserDTO map(User user) {
        if (user instanceof FacebookUser) {
            return this.map((FacebookUser) user);
        } else if (user instanceof TwitterUser) {
            return this.map((TwitterUser) user);
        } else {
            return this.map((LocalUser) user);
        }
    }

    @InheritInverseConfiguration
    default User map(UserDTO userDTO) {
        if (userDTO instanceof UserDTO.FacebookUserDTO) {
            return this.map((UserDTO.FacebookUserDTO) userDTO);
        } else if (userDTO instanceof UserDTO.TwitterUserDTO) {
            return this.map((UserDTO.TwitterUserDTO) userDTO);
        } else {
            return this.map((UserDTO.LocalUserDTO) userDTO);
        }
    }
}


