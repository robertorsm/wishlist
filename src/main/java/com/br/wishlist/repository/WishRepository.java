package com.br.wishlist.repository;

import com.br.wishlist.model.Wish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends MongoRepository<Wish, Long> {

    List<Wish> findAllBy(Long id);
}
