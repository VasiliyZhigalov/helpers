package com.lastfeofan.helpers.repositories;

import com.lastfeofan.helpers.domain.Usr;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Usr, Integer> {
    List<Usr> findByName(String name);
}
