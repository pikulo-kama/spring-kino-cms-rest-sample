package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.UserCreateFormDto;
import com.pikulokama.kinocmstest.exception.RestServiceException;
import com.pikulokama.kinocmstest.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Log4j2
@Service
public class UserServiceImpl implements UserService, UserDetailsService, AdminUserInitializer {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${admin.username}")
    private String defaultAdminUsername;

    @Value("${admin.password}")
    private String defaultAdminPassword;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public void create(UserCreateFormDto userCreateFormDto, User authenticatedUser) {

        if (userCreateFormDto.getRole().equals(User.UserRole.ROLE_ADMIN)) {

            if (isNull(authenticatedUser) || authenticatedUser.getRole().equals(User.UserRole.ROLE_USER)) {
                throw new RestServiceException("В доступе отказано. У пользователя нет соответственных прав.");
            }
        }

        if (!userCreateFormDto.getPassword().equals(userCreateFormDto.getPasswordRepeat())) {
            throw new RestServiceException("Пароли не совпадают");
        }

        if (userRepository.existsByUsername(userCreateFormDto.getUsername())) {
            throw new RestServiceException("Пользователь с даным именем уже существует");
        }

        User user = User.builder()
                .username(userCreateFormDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userCreateFormDto.getPassword()))
                .role(userCreateFormDto.getRole())
                .build();

        userRepository.save(user);

        log.info("User {} with {} rights was successfully registered", user.getUsername(), user.getRole());
    }

    @Override
    public void createAdminIfNotExists() {

        if (userRepository.existsByRole(User.UserRole.ROLE_ADMIN)) {
            log.info("There are still admins in system. Default admin creation cancelled");
            return;
        }

        log.info("No admin in system. Creating one....");

        User admin = User.builder()
                .username(defaultAdminUsername)
                .password(bCryptPasswordEncoder.encode(defaultAdminPassword))
                .role(User.UserRole.ROLE_ADMIN)
                .build();

        userRepository.save(admin);
        log.info("Admin account successfully created");
    }
}
