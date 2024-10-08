package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        var session = sf.openSession();
        try {

            session.beginTransaction();
            session.createQuery("Update User SET password = :fPassword WHERE login = :fLogin")
                    .setParameter("fPassword", "new password")
                    .setParameter("fLogin", user.getLogin())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        var session = sf.openSession();
        try {

            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        List<User> users = new ArrayList<>();
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("FROM ru.job4j.cars.model.User");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        var session = sf.openSession();
        Optional<User> user = Optional.of(new User());
        try {
            session.beginTransaction();
            user = session.createQuery("FROM User as i WHERE i.id = :fId", User.class)
                    .setParameter("fId", userId).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        var session = sf.openSession();
        List<User> usersLogin = new ArrayList<>();
        try {
            session.beginTransaction();
            var query = (User) session.createQuery("FROM User as i WHERE i.login = :fLogin")
                    .setParameter("fLogin", key).getSingleResult();
            usersLogin.add(query);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return usersLogin;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        var session = sf.openSession();
        Optional<User> user = Optional.of(new User());
        try {

            session.beginTransaction();
            user =  session
                    .createQuery("FROM User as l WHERE l.login = :fLogin", User.class)
                    .setParameter("fLogin", login)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }
}