package com.unibuc.awbd.service;

import com.unibuc.awbd.model.Favorite;
import com.unibuc.awbd.model.Product;
import com.unibuc.awbd.model.User;
import com.unibuc.awbd.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public boolean addToFavorites(User user, Product product) {
        if (!favoriteRepository.existsByUserAndProduct(user, product)) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProduct(product);
            favoriteRepository.save(favorite);
            return true;
        }
        return false;
    }

    public void removeFromFavorites(User user, Product product) {
        // Căutăm dacă există o înregistrare în tabela de favorite pentru acest user și produs
        Favorite favorite = favoriteRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));

        // Ștergem înregistrarea din favorite
        favoriteRepository.delete(favorite);
    }

    public List<Product> getFavorites(User user) {
        return favoriteRepository.findByUser(user).stream()
                .map(Favorite::getProduct)
                .collect(Collectors.toList());
    }
}
