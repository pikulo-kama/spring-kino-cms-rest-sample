package com.pikulokama.kinocmstest.controller.api;

import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.UserCreateFormDto;
import com.pikulokama.kinocmstest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public void register(@AuthenticationPrincipal User user,
                         @Validated UserCreateFormDto userCreateFormDto) {

        userService.create(userCreateFormDto, user);
    }

}
