package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByPrice_Currency(String currency);

    @Query(value = "SELECT * FROM PRODUCT WHERE TITLE=:param", nativeQuery = true)
    Product findDistinctByTitle(String param);
}
