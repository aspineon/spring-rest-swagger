package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.PanSequence;
import com.infotech.cms.repository.CardTypeRepository;
import com.infotech.cms.repository.PanSequenceRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for PanSequence repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface SpringDataJpaPanSequenceRepository extends PanSequenceRepository, Repository<PanSequence, Long> {
}
