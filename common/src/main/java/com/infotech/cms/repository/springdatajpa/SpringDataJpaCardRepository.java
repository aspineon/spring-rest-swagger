package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.Card;
import com.infotech.cms.repository.CardRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * spring data jpa implementation for Card repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface SpringDataJpaCardRepository extends CardRepository, Repository<Card, Long> {

    @Query("SELECT card FROM Card card inner join fetch card.cardHolder ch where card.pan=:pan")
    @Override
    Card findByPanWithCardHolder(@Param("pan") String pan);
}
