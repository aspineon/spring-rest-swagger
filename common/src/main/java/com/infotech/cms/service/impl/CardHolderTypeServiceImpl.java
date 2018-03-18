package com.infotech.cms.service.impl;

import com.infotech.cms.domain.CardHolderType;
import com.infotech.cms.repository.CardHolderTypeRepository;
import com.infotech.cms.service.CardHolderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service impl for CardHolderType domain object
 * generated at 2017-04-09T16:42:10.247 by Infotech code generator */
@Service
public class CardHolderTypeServiceImpl extends BaseServiceImpl<CardHolderType> implements CardHolderTypeService {
    private final CardHolderTypeRepository cardHolderTypeRepository;

    @Autowired
    public CardHolderTypeServiceImpl(CardHolderTypeRepository cardHolderTypeRepository) {
        super(cardHolderTypeRepository);
        this.cardHolderTypeRepository = cardHolderTypeRepository;
    }
}
