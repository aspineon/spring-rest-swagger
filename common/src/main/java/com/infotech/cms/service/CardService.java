package com.infotech.cms.service;

import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.dto.CardAssignmentRequest;
import com.infotech.cms.exception.*;

/**
 * service interface for Card domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface CardService extends BaseService<Card> {
    Card findByPan(String pan);
    Card assign(CardAssignmentRequest request) throws CardHolderNotFoundException, CardNotFoundException, CardNotAssignableException, CardTypeNotFoundException, AccountTypeNotFoundException, PanOverflowException;
    Card assignExistingCard(Long cardHolderId, String pan) throws CardHolderNotFoundException, CardNotFoundException, PanNumberNotProvidedException, CardNotAssignableException;
    Card personalizeCard(String pan) throws CardNotFoundException;
}
