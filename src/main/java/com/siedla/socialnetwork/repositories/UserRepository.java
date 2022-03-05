package com.siedla.socialnetwork.repositories;

import com.siedla.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
