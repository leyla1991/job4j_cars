package ru.job4j.cars.repository.price;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

@Repository
@AllArgsConstructor
public class HBPriceHistoryRepository implements PriceHistoryRepository {

        private CrudRepository crudRepository;

    @Override
    public PriceHistory save(PriceHistory priceHistory) {
        crudRepository.run(session -> session.save(priceHistory));
        return priceHistory;
    }

    @Override
    public Collection<PriceHistory> findAll() {
        return crudRepository.query("FROM PriceHistory", PriceHistory.class);
    }
}
