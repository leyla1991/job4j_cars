package ru.job4j.cars.repository.brand;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HBBrandRepository implements BrandRepository {

    private CrudRepository crudRepository;

    @Override
    public Collection<Brand> findAll() {
        return crudRepository.query("FROM Brand", Brand.class);
    }
}
