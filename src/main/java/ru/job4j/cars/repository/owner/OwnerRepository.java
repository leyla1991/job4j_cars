package ru.job4j.cars.repository.owner;

import ru.job4j.cars.model.Owner;

import java.util.Collection;

public interface OwnerRepository {

    Owner save(Owner owner);

    Collection<Owner> findAll();
}
