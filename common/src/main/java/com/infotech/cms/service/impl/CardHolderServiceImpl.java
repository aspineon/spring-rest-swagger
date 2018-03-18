package com.infotech.cms.service.impl;

import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.repository.CardHolderRepository;
import com.infotech.cms.service.CardHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * service implementation for CardHolder domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
@Service
public class CardHolderServiceImpl extends BaseServiceImpl<CardHolder> implements CardHolderService {

    private final CardHolderRepository cardHolderRepository;

    @Autowired
    public CardHolderServiceImpl(CardHolderRepository cardHolderRepository) {
        super(cardHolderRepository);
        this.cardHolderRepository = cardHolderRepository;
    }
}
