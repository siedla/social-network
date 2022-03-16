package com.siedla.socialnetwork.repositories;

import com.siedla.socialnetwork.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
