package co.istad.ite2mbankingapi.mapper;

import co.istad.ite2mbankingapi.domain.User;
import co.istad.ite2mbankingapi.feature.user.dto.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest request);
}
