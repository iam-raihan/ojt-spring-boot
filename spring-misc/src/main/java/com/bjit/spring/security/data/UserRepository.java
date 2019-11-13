package com.bjit.spring.security.data;

import com.bjit.spring.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
         extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
