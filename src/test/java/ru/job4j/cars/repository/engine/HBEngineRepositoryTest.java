package ru.job4j.cars.repository.engine;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HBEngineRepositoryTest {

    private static HBEngineRepository hbEngineRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        hbEngineRepository = new HBEngineRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSave() {
        Engine engine = new Engine();
        Engine engine1 = new Engine();

        engine.setNameEngine("test");
        engine1.setNameEngine("test1");

        hbEngineRepository.save(engine);
        hbEngineRepository.save(engine1);

        var result = hbEngineRepository.findAll();

        assertThat(result).isEqualTo(List.of(engine, engine1));
    }
}