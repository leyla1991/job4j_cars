package ru.job4j.cars.repository.user;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private static UserRepository userRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        userRepository = new UserRepository(new CrudRepository(sf));
        deletesUser();
    }

    public static void deletesUser() {
        userRepository.findAllOrderById().stream().map(User::getId).forEach(userRepository::delete);
    }

    @AfterEach
    public void deleteUser() {
        deletesUser();
    }

    @AfterAll
    static void afterAll() {
        sf.close();
    }

    @Test
    public void whenCreateUser() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        userRepository.create(user);
        assertThat(userRepository.findById(user.getId()).get()).isEqualTo(user);
    }

    @Test
    public void whenUpdateUser() {
        User user = new User("login", "password");
        userRepository.create(user);
        User replaceUser = new User(user.getId(), "use", "perer");
        userRepository.update(replaceUser);
        var result = userRepository.findById(user.getId()).get();
        assertThat(result.getLogin()).isEqualTo(replaceUser.getLogin());
    }

    @Test
    public void whenFindAllUser() {
        User user = new User("user", "pa");
        User user1 = new User("user1", "pas");
        User user2 = new User("user2", "pas");

        userRepository.create(user);
        userRepository.create(user1);
        userRepository.create(user2);

        var result = userRepository.findAllOrderById();
        assertThat(result).isEqualTo(List.of(user, user1, user2));
    }

    @Test
    public void whenFindAllUserByLogin() {
        User user = new User("logger", "pass");
        User user1 = new User("logger1", "pass1");
        User user2 = new User("logger2", "pass2");

        userRepository.create(user);
        userRepository.create(user1);
        userRepository.create(user2);

        var result = userRepository.findByLikeLogin("lo");
        assertThat(result).isEqualTo(List.of(user, user1, user2));
    }

    @Test
    public void whenFindByLogin() {
        User user = new User("logger", "pass");
        User user1 = new User("logger1", "pass1");
        User user2 = new User("logger2", "pass2");

        userRepository.create(user);
        userRepository.create(user1);
        userRepository.create(user2);

        var result = userRepository.findByLogin("logger");
        assertThat(result.get()).isEqualTo(user);
    }
}