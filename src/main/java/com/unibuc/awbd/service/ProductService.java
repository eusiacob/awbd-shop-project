package com.unibuc.awbd.service;

import com.unibuc.awbd.model.Product;
import com.unibuc.awbd.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        logger.info("Deleting product with id: {}", id);
    }

    public void saveProductWithDistributors(Product product) {
        logger.info("Saving product with name: {}", product.getName());
        productRepository.save(product);
    }
}
