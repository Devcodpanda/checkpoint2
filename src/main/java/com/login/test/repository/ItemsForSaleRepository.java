package com.login.test.repository;

import com.login.test.entity.ItemsForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsForSaleRepository extends JpaRepository<ItemsForSale, Long> {
}
