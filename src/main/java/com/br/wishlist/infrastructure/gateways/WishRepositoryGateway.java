package com.br.wishlist.infrastructure.gateways;

import com.br.wishlist.application.gateways.WishGateway;
import com.br.wishlist.domain.entity.Wish;
import com.br.wishlist.infrastructure.persistence.WishEntity;
import com.br.wishlist.infrastructure.persistence.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishRepositoryGateway implements WishGateway {
    private final WishRepository wishRepository;
    private final WishEntityMapper wishEntityMapper;

    @Override
    public List<Wish> getAllWishesByCustomerId(Long customerId) {
        return wishRepository.findAllByCustomerId(customerId).stream()
                .map(wishEntityMapper::documentToDomain).toList();
    }

    @Override
    public Wish createWish(Wish wishDomain) {
        WishEntity wishEntity = wishEntityMapper.toDocument(wishDomain);
        return wishEntityMapper.documentToDomain(wishRepository.save(wishEntity));
    }

    @Override
    public void deleteWish(Long productId, Long customerId) {
        wishRepository.deleteByProductIdAndCustomerId(productId, customerId);
    }

    @Override
    public Boolean existsWishByProductIdAndCustomerId(Long productId, Long customerId) {
        return wishRepository.existsByProductIdAndCustomerId(productId, customerId);
    }

    @Override
    public Integer countByCustomerId(Long customerId) {
        return wishRepository.countByCustomerId(customerId);
    }

}
