package com.siedla.socialnetwork.repositories;

import com.siedla.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
    LinkedList<User> findUsersByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByFirstName(String firstName);
    User findUserById(Long id);

}
