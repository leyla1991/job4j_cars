package ru.job4j.cars.repository.price;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
class HBPriceHistoryRepositoryTest {

    private static HBPriceHistoryRepository hbPriceHistoryRepository;
    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        hbPriceHistoryRepository = new HBPriceHistoryRepository(new CrudRepository(sf));
    }

    @Test
    public void whenSaveAndFindAll() {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setAfter(2L);
        priceHistory.setAfter(3L);
        hbPriceHistoryRepository.save(priceHistory);
        var result = hbPriceHistoryRepository.findAll();
        assertThat(result).isEqualTo(List.of(priceHistory));
    }
}