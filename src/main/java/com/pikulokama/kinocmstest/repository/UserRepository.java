package com.pikulokama.kinocmstest.repository;

import com.pikulokama.kinocmstest.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByRole(User.UserRole role);

}
