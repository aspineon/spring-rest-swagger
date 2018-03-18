package com.infotech.cms.repository;

import com.infotech.cms.domain.CardType;

/**
 * repository for CardType domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface CardTypeRepository extends BaseRepository<CardType> {
    CardType findOneByName(String name);
}
