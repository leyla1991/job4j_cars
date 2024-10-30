package ru.job4j.cars.repository.file;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.CrudRepository;

import static org.assertj.core.api.Assertions.assertThat;

class HBFileRepositoryTest {

    private static HBFileRepository hbFileRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        hbFileRepository = new HBFileRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSaveFile() {
        File file = new File();
        file.setFileName("name");
        hbFileRepository.save(file);
        assertThat(hbFileRepository.findById(file.getId()).get()).isEqualTo(file);
    }

    @Test
    public void whenFindByIdOneFile() {
        File file = new File();
        File file1 = new File();
        file.setFileName("name");
        file1.setFileName("test");
        hbFileRepository.save(file);
        hbFileRepository.save(file1);
        var result = hbFileRepository.findById(file.getId());
        assertThat(result.get()).isEqualTo(file);
    }

    @Test
    public void whenDeleteFile() {
        File file = new File();
        file.setFileName("name");
        hbFileRepository.save(file);
        hbFileRepository.deleteById(file.getId());
        assertThat(hbFileRepository.findById(file.getId())).isEmpty();
    }
}