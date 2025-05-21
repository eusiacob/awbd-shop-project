package com.unibuc.awbd.repository;

import com.unibuc.awbd.model.Favorite;
import com.unibuc.awbd.model.Product;
import com.unibuc.awbd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserAndProduct(User user, Product product);
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndProduct(User user, Product product);
}
