package com.infotech.cms.service.impl;

import com.infotech.cms.domain.CardType;
import com.infotech.cms.repository.CardTypeRepository;
import com.infotech.cms.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * service implementation for CardType domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
@Service
public class CardTypeServiceImpl extends BaseServiceImpl<CardType> implements CardTypeService {

    private final CardTypeRepository cardTypeRepository;

    @Autowired
    public CardTypeServiceImpl(CardTypeRepository cardTypeRepository) {
        super(cardTypeRepository);
        this.cardTypeRepository = cardTypeRepository;
    }
}
