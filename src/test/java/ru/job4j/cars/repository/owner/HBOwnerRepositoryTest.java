package ru.job4j.cars.repository.owner;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HBOwnerRepositoryTest {

    private static HBOwnerRepository hbOwnerRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        hbOwnerRepository = new HBOwnerRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSaveAndFindAll() {
        Owner owner = new Owner();
        owner.setNameOwner("name");

        Owner owner1 = new Owner();
        owner1.setNameOwner("name1");

        hbOwnerRepository.save(owner);
        hbOwnerRepository.save(owner1);

        var result = hbOwnerRepository.findAll();
        assertThat(result).isEqualTo(List.of(owner, owner1));
    }

}