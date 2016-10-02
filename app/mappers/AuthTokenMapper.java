package mappers;

import controllers.viewModels.AuthTokenDTO;
import models.AuthToken;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(config = CentralConfig.class, uses = {UserMapper.class})
public interface AuthTokenMapper {
    public static AuthTokenMapper INSTANCE = Mappers.getMapper(AuthTokenMapper.class);

    AuthToken map(AuthTokenDTO authTokenDTO);

    @InheritInverseConfiguration
    AuthTokenDTO map(AuthToken authToken);
}


