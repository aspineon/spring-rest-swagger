package com.infotech.cms.service.impl;

import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.PanSequence;
import com.infotech.cms.repository.CardTypeRepository;
import com.infotech.cms.repository.PanSequenceRepository;
import com.infotech.cms.service.CardTypeService;
import com.infotech.cms.service.PanSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * service implementation for PanSequence domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
@Service
public class PanSequenceServiceImpl extends BaseServiceImpl<PanSequence> implements PanSequenceService {

    private final PanSequenceRepository panSequenceRepository;

    @Autowired
    public PanSequenceServiceImpl(PanSequenceRepository panSequenceRepository) {
        super(panSequenceRepository);
        this.panSequenceRepository = panSequenceRepository;
    }
}
