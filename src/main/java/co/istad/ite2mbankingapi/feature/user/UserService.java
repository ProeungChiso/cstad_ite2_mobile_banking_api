package co.istad.ite2mbankingapi.feature.user;

import co.istad.ite2mbankingapi.domain.User;
import co.istad.ite2mbankingapi.feature.user.dto.UserCreateRequest;

public interface UserService {
    User createNew(UserCreateRequest request);
}
