package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class HBPostRepository implements PostRepository {

    private CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        crudRepository.run(session -> session.save(post));
        return post;
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("FROM Post WHERE id = :fId", Post.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Post> findAll() {
        var query = """
                SELECT DISTINCT t
                FROM Post as t
                JOIN FETCH t.filePhotos
                ORDER BY t.id ASC
                """;
        return crudRepository.query(query, Post.class);
    }

    @Override
    public Collection<Post> findLastDay() {
        return crudRepository.query("FROM Post as i WHERE i.created >= :fCreated",
               Post.class,
               Map.of("fCreated", LocalDateTime.now().minusDays(1)));
    }

    @Override
    public Collection<Post> findPostWithPhoto() {
        return crudRepository.query("FROM Post i WHERE size(i.filePhotos) != 0",
                Post.class);
    }

    @Override
    public Collection<Post> findPostOneBrand(Brand brand) {
        return crudRepository.query("FROM Post i WHERE i.car.brand = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }
}
