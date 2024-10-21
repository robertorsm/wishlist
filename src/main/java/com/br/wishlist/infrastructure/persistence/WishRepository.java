package com.br.wishlist.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends MongoRepository<WishEntity, String> {
    List<WishEntity> findAllByCustomerId(Long customerId);

    void deleteByProductIdAndCustomerId(Long productId, Long customerId);

    Boolean existsByProductIdAndCustomerId(Long productId, Long customerId);

    Integer countByCustomerId(Long customerId);
}
