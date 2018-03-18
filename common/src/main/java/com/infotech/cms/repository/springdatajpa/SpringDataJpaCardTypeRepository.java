package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardType;
import com.infotech.cms.repository.CardTypeRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for CardType repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface SpringDataJpaCardTypeRepository extends CardTypeRepository, Repository<CardType, Long> {
}
