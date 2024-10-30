package ru.job4j.cars.repository.owner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HBOwnerRepository implements  OwnerRepository {

    private CrudRepository crudRepository;

    @Override
    public Owner save(Owner owner) {
        crudRepository.run(session -> session.save(owner));
        return owner;
    }

    @Override
    public Collection<Owner> findAll() {
        return crudRepository.query("FROM Owner", Owner.class);
    }
}
