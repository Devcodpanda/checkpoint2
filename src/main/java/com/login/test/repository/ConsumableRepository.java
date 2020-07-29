package com.login.test.repository;

import com.login.test.entity.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
}
