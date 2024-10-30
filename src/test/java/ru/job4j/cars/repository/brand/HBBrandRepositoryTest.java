package ru.job4j.cars.repository.brand;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HBBrandRepositoryTest {

    private static SessionFactory sf;
    private static HBBrandRepository hbBrandRepository;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        hbBrandRepository = new HBBrandRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSaveAndFindAllBrand() {
        Brand brand = new Brand(2, "wow", "wer");
        Brand brand1 = new Brand(1, "bmw", "fd");
        hbBrandRepository.save(brand);
        hbBrandRepository.save(brand1);
        var result = hbBrandRepository.findAll();
        assertThat(result).isEqualTo(List.of(brand, brand1));
    }
}