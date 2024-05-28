package com.example.merchantmanagement.repository;

import com.example.merchantmanagement.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    void deleteByShopName(String shopName);

    Optional<Merchant> findByShopName(String shopName);
}
