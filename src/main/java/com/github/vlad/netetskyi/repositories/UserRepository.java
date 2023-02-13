package com.github.vlad.netetskyi.repositories;

import com.github.vlad.netetskyi.services.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
