package ru.job4j.cars.repository.post;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.File;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.file.HBFileRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HBPostRepositoryTest {

    private static HBFileRepository hbFileRepository;
    private static HBPostRepository hbPostRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
         sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
         hbPostRepository = new HBPostRepository(new CrudRepository(sf));
         hbFileRepository = new HBFileRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSavePost() {
            Post post = new Post();
            post.setDescription("test1");
            hbPostRepository.save(post);
            var result = hbPostRepository.findById(post.getId());
            assertThat(result.get().getDescription()).isEqualTo(post.getDescription());
    }

    @Test
    public void whenFindAll() {
        Post post = new Post();
        post.setDescription("test1");
        Post post1 = new Post();
        post.setDescription("test2");
        hbPostRepository.save(post);
        hbPostRepository.save(post1);
        var result = hbPostRepository.findAll();
        assertThat(result).isEqualTo(List.of(post, post1));
    }

    @Test
    public void whenFindLastDay() {
        Post post = new Post();
        post.setDescription("test1");
        Post post1 = new Post();
        post.setDescription("test2");
        post1.setCreated(LocalDateTime.now().minusDays(5));
        hbPostRepository.save(post);
        hbPostRepository.save(post1);
        var result = hbPostRepository.findLastDay();
        assertThat(result).isEqualTo(List.of(post));
    }

    @Test
    public void whenFindPostWithPhoto() {
        Post post = new Post();
        Post post1 = new Post();
        File file = new File();
        file.setFileName("test1");
        file.setPostId(post);
        hbFileRepository.save(file);
        post.setFilePhotos(List.of(file));
        hbPostRepository.save(post);
        hbPostRepository.save(post1);
        var result = hbPostRepository.findPostWithPhoto();
        assertThat(result).isEqualTo(List.of(post));
    }

    @Test
    public void whenFindPostOneBrand() {
        Post post = new Post();
        Post post1 = new Post();
        Brand brand = new Brand();
        Brand brand1 = new Brand();
        brand.setBrandName("bmw");
        brand1.setBrandName("Mercedes");
        Car car = new Car();
        Car car1 = new Car();
        car.setBrand(brand);
        car1.setBrand(brand1);
        post.setCar(car);
        post1.setCar(car1);
        hbPostRepository.save(post);
        hbPostRepository.save(post1);
        var result = hbPostRepository.findPostOneBrand(brand);
        assertThat(result).isEqualTo(List.of(post));
    }

}