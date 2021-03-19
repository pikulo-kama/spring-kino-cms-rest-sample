package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.UserCreateFormDto;
import com.pikulokama.kinocmstest.exception.RestServiceException;
import com.pikulokama.kinocmstest.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Log4j2
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void create(UserCreateFormDto userCreateFormDto, User authenticatedUser) {

        if (userCreateFormDto.getRole().equals(User.UserRole.ROLE_ADMIN)) {

            if (isNull(authenticatedUser) || authenticatedUser.getRole().equals(User.UserRole.ROLE_USER.name())) {
                throw new RestServiceException("Permission denied. No suitable permissions to perform action");
            }
        }

        if (!userCreateFormDto.getPassword().equals(userCreateFormDto.getPasswordRepeat())) {
            throw new RestServiceException("Password doesn't match");
        }

        if (userRepository.existsByUsername(userCreateFormDto.getUsername())) {
            throw new RestServiceException("User with such username already exists");
        }

        User user = User.builder()
                .username(userCreateFormDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userCreateFormDto.getPassword()))
                .role(userCreateFormDto.getRole().name())
                .build();

        userRepository.save(user);

        log.info("User {} with {} rights was successfully registered", user.getUsername(), user.getRole());
    }

}
