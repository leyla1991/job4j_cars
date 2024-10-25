package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HBPostRepository implements PostRepository {

    private CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        crudRepository.run(session -> session.save(post));
        return post;
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("FROM Post as i  JOIN FETCH i.user WHERE i.id = :fId", Post.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Post> findAll() {
        return crudRepository.query("FROM Post as i JOIN FETCH i.user", Post.class);
    }
}
