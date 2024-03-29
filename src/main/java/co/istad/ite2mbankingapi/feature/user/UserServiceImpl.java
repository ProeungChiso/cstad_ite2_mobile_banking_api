package co.istad.ite2mbankingapi.feature.user;

import co.istad.ite2mbankingapi.domain.Role;
import co.istad.ite2mbankingapi.domain.User;
import co.istad.ite2mbankingapi.feature.user.dto.RoleRepository;
import co.istad.ite2mbankingapi.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbankingapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User createNew(UserCreateRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByNationalCardId(request.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if (userRepository.existsByStudentIdCard(request.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }

        if (!request.password()
                .equals(request.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(request);
        user.setUuid(UUID.randomUUID().toString());
        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));
        roles.add(userRole);

        user.setRoles(roles);

        return userRepository.save(user);

    }
}
