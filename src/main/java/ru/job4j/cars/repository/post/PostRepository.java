package ru.job4j.cars.repository.post;

import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(int id);

    Collection<Post> findAll();
}