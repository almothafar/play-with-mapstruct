package mappers;

import controllers.dto.AccountDTO;
import models.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(config = CentralConfig.class, uses = {UserMapper.class})
public interface AccountMapper {
    public static AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(target = "users", ignore = true)
    })
    Account map(AccountDTO accountDTO);

    @InheritInverseConfiguration
    AccountDTO map(Account account);

    List<Account> map(ArrayList<AccountDTO> accountDTOs);

    List<AccountDTO> map(List<Account> accounts);
}


