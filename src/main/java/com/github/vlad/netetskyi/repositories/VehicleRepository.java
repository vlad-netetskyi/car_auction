package com.github.vlad.netetskyi.repositories;

import com.github.vlad.netetskyi.models.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
