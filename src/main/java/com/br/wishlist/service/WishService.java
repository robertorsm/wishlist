package com.br.wishlist.service;

import com.br.wishlist.model.Wish;
import com.br.wishlist.records.WishRecord;
import com.br.wishlist.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public List<WishRecord> getAllWishesByCustomerId(Long customerId) {
        return wishRepository.findAllByCustomerId(customerId).stream().map(Wish::documentToRecord).toList();
    }

    public void createWish(WishRecord wish) {
        wishRepository.save(wish.toDocument());
    }

    public void deleteWish(Long productId, Long customerId) {
        wishRepository.deleteByProductIdAndAndCustomerId(productId, customerId);
    }

    public Boolean existsByProductIdAndCustomerId(Long productId, Long customerId) {
        return wishRepository.existsByProductIdAndCustomerId(productId, customerId);
    }

}
