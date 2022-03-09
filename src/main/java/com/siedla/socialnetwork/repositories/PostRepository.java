package com.siedla.socialnetwork.repositories;

import com.siedla.socialnetwork.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Optional<List<Post>> findByUserId(Long id);

    List<Post> findAllByIdBetween(Long start, Long end);
}
