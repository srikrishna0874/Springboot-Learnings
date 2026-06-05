package com.codingshuttle.jpa_learnings;

import com.codingshuttle.jpa_learnings.entities.ProductEntity;
import com.codingshuttle.jpa_learnings.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaLearningsApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("nestle234")
                .title("Nestle Chocolate")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12)
                .build();

        ProductEntity savedProductEntity = productRepository.save(productEntity);
        System.out.println(savedProductEntity);
    }

    @Test
    void getRepository() {
//        List<ProductEntity> productEntityList =
//                productRepository.findByCreatedAtAfter(LocalDateTime.of(2024, 1, 1, 0, 0, 0));
//
//        List<ProductEntity> productEntityList =
//                productRepository.findByQuantityGreaterThanAndPriceLessThan(12, 123.45);

        List<ProductEntity> productEntityList = productRepository.findByTitleContainingIgnoreCase("Ne", null);

        System.out.println(productEntityList);
    }

    @Test
    void getSingleRepository() {
        Optional<ProductEntity> productEntity =
                productRepository.findByTitleAndPrice("Nestle Chocolate", BigDecimal.valueOf(123.45));

        productEntity.ifPresent(System.out::println);
    }

}
