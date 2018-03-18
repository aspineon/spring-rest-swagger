package com.infotech.cms.service.impl;

import com.infotech.accounting.domain.Account;
import com.infotech.accounting.domain.AccountType;
import com.infotech.accounting.domain.type.AccountState;
import com.infotech.accounting.repository.AccountRepository;
import com.infotech.accounting.repository.AccountTypeRepository;
import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.PanSequence;
import com.infotech.cms.domain.dto.CardAssignmentRequest;
import com.infotech.cms.domain.type.CardState;
import com.infotech.cms.domain.type.PersoState;
import com.infotech.cms.domain.type.PinState;
import com.infotech.cms.exception.*;
import com.infotech.cms.repository.CardHolderRepository;
import com.infotech.cms.repository.CardRepository;
import com.infotech.cms.repository.CardTypeRepository;
import com.infotech.cms.repository.PanSequenceRepository;
import com.infotech.cms.service.CardService;
import org.jpos.iso.ISOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * service implementation for Card domain object.
 *
 * @author Mohamamd Reza Alagheband
 */
@Service
public class CardServiceImpl extends BaseServiceImpl<Card> implements CardService {

    private final String PAN_PREFIX="PREFIX";
    public static final String CARD_BIN = "140620";
    public static final long PAN_MAX_SEQUENCE = 999999999;

    private final CardRepository cardRepository;
    private final CardHolderRepository cardHolderRepository;
    private final PanSequenceRepository panSequenceRepository;
    private final CardTypeRepository cardTypeRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public CardServiceImpl(CardRepository cardRepository,
                           CardHolderRepository cardHolderRepository,
                           PanSequenceRepository panSequenceRepository,
                           CardTypeRepository cardTypeRepository,
                           AccountTypeRepository accountTypeRepository,
                           AccountRepository accountRepository) {
        super(cardRepository);
        this.cardRepository = cardRepository;
        this.cardHolderRepository = cardHolderRepository;
        this.panSequenceRepository = panSequenceRepository;
        this.cardTypeRepository = cardTypeRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Card findByPan(String pan) {
        return cardRepository.findByPan(pan);
    }


    /**
     * Assign an existing card to a cardHolder
     * @param cardHolderId
     * @param pan
     * @return
     * @throws CardHolderNotFoundException
     * @throws CardNotFoundException
     * @throws PanNumberNotProvidedException
     * @throws CardNotAssignableException
     */
    @Override
    @Transactional
    public Card assignExistingCard(Long cardHolderId, String pan) throws CardHolderNotFoundException, CardNotFoundException, PanNumberNotProvidedException, CardNotAssignableException {

        if(pan==null || "".equals(pan.trim()))
        {
            throw new PanNumberNotProvidedException();
        }

        Card card  =Optional.ofNullable(cardRepository.findByPanWithCardHolder(pan))
                .map(c -> c)
                .orElseThrow(CardNotFoundException::new);

        CardHolder cardHolder =Optional.ofNullable(cardHolderRepository.findById(cardHolderId))
                .map(c -> c)
                .orElseThrow(CardHolderNotFoundException::new);
        if(!this.isCardAssignable(card)) throw new CardNotAssignableException();
        card.setCardHolder(cardHolder);
        cardRepository.save(card);
        return card;
    }

    /**
     * Assign a new card to the cardHolder
     * @param request client input data container to define a new card and assign
     * @return
     * @throws CardHolderNotFoundException
     * @throws CardNotFoundException
     * @throws CardNotAssignableException
     * @throws CardTypeNotFoundException
     * @throws AccountTypeNotFoundException
     */
    @Override
    @Transactional
    public Card assign(CardAssignmentRequest request)
            throws
            CardHolderNotFoundException, CardNotFoundException,
            CardNotAssignableException, CardTypeNotFoundException,
            AccountTypeNotFoundException, PanOverflowException {

        CardHolder cardHolder =Optional.ofNullable(cardHolderRepository.findById(request.getCardHolderId()))
        .map(c -> c)
        .orElseThrow(CardHolderNotFoundException::new);

        Card card = null;

        CardType cardType = Optional.ofNullable(cardTypeRepository.findById(request.getCardTypeId()))
                .map(c -> c)
                .orElseThrow(CardTypeNotFoundException::new);


        Account account=this.createNewAccount(request.getAccountTypeId(),request.getAccountNumber(),request.getInitBalance());

        Card newCard = new Card();
        newCard.setCardHolder(cardHolder);
        newCard.setPinState(PinState.INACTIVE);
        newCard.setTechnology(request.getTechnology());
        newCard.setAccount(account);
        newCard.setExpirationDate(request.getExpirationDate());
        newCard.setPan(generatePan());
        newCard.setPersoState(PersoState.NOT_PERSONALIZED);
        newCard.setCardState(CardState.ACTIVE);
        newCard.setCardType(cardType);
        newCard.setIssueDateTime(LocalDateTime.now());
        cardRepository.save(newCard);
        return newCard;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private Account createNewAccount(Long accountTypeId,String accountNumber , BigDecimal balance) throws AccountTypeNotFoundException {

        AccountType accountType = Optional.ofNullable(accountTypeRepository.findById(accountTypeId))
                .map(c -> c)
                .orElseThrow(AccountTypeNotFoundException::new);


        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setAccountState(AccountState.READY);
        account.setBalance(balance);
        accountRepository.save(account);

        return account;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private String generatePan() throws PanOverflowException {

        PanSequence sequence = new PanSequence();
        sequence = panSequenceRepository.save(sequence);
        if (sequence.getId() > PAN_MAX_SEQUENCE) {
            throw new PanOverflowException("pan sequence overflow");
        }
        String pan = CARD_BIN + String.format("%09d", sequence.getId());
        char luhn = ISOUtil.calcLUHN(pan);
        pan = pan + luhn;

        return pan;
    }

    private Boolean isCardAssignable(Card card){
        return (card.getCardHolder()!=null)?false:true;
    }


    @Override
    @Transactional
    public Card personalizeCard(String pan) throws CardNotFoundException {
        Card card  =Optional.ofNullable(cardRepository.findByPanWithCardHolder(pan))
                .map(c -> c)
                .orElseThrow(CardNotFoundException::new);

        card.setPersoState(PersoState.PERSONALIZED);
        card.setPersoDateTime(LocalDateTime.now());
        return card;
    }
}
