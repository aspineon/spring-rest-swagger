package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.repository.CardHolderRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for CardHolder repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface SpringDataJpaCardHolderRepository extends CardHolderRepository, Repository<CardHolder, Long> {

    /*
    @Override
    @Query("SELECT cardHolder FROM CardHolder cardHolder LEFT JOIN FETCH cardHolder.cards WHERE cardHolder.id = :id")
    CardHolder findById(@Param("id") long id);
    */
}
