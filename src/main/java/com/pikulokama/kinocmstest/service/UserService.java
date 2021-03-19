package com.pikulokama.kinocmstest.service;

import com.pikulokama.kinocmstest.domain.User;
import com.pikulokama.kinocmstest.dto.form.UserCreateFormDto;

public interface UserService {

    void create(UserCreateFormDto userCreateFormDto, User authenticatedUser);

}
