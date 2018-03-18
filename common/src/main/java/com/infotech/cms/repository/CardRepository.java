package com.infotech.cms.repository;

import com.infotech.cms.domain.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * repository for Card domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface CardRepository extends BaseRepository<Card> {

   @EntityGraph(attributePaths = {"cardType"})
   Card findByPan(String pan);

   Card findByPanWithCardHolder(String pan);
}
