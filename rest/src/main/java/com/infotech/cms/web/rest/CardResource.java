package com.infotech.cms.web.rest;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.dto.CardAssignmentRequest;
import com.infotech.cms.domain.dto.CardInfoResponse;
import com.infotech.cms.exception.*;
import com.infotech.cms.service.CardService;
import com.infotech.cms.web.rest.dto.BaseResponse;
import com.infotech.cms.web.rest.mapper.CardMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing card operation
 *
 * @author MohammadReza Alagheband
 */
@RestController
@RequestMapping(value = "/api")
public class CardResource {

    private final Logger log = LoggerFactory.getLogger(CardResource.class);

    private CardService cardService;
    private CardMapper cardMapper;

    @Autowired
    public CardResource(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    /**
     * GET  /cards/{pan} : get a card information based on pan value.
     *
     * @param pan pan for card
     * @return the ResponseEntity with status 200 (OK) and the list of operations in body
     */
    @GetMapping("/v1/cards/{pan}")
    @Timed
    @ExceptionMetered
    @PreAuthorize("hasAnyAuthority('card_fetch')")
    public ResponseEntity<CardInfoResponse> findCardByPan(@PathVariable String pan) throws CardNotFoundException {

        CardInfoResponse dto=Optional.ofNullable(cardService.findByPan(pan)).
                map(c -> cardMapper.createCardInfoResponse(c))
                .orElseThrow(CardNotFoundException::new);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * POST  /cards/assign/cardholder : Assign a card to a cardHolder.
     *
     * @param request object containing card holder id and card id(a the card already defined)
     * @return the ResponseEntity with status 200 (OK) and with body the assigned card.
     */
    @PostMapping("/v1/cards/assign")
    @Timed
    @ExceptionMetered
    @PreAuthorize("hasAnyAuthority('card_assignment')")
    public ResponseEntity<Card> cardAssignment(@Valid @RequestBody CardAssignmentRequest request) throws CardHolderNotFoundException,
            CardNotAssignableException,
            CardTypeNotFoundException,
            AccountTypeNotFoundException,
            CardNotFoundException, MethodArgumentBindingValidationException, PanOverflowException {

        return new ResponseEntity<>(cardService.assign(request), HttpStatus.OK);
    }

    /** GET /cards/assign/cardholder : Assign an existing card to a cardHolder.
     *
     * @param cardHolderId
     * @param pan
     * @return
     * @throws CardHolderNotFoundException
     * @throws CardHolderNotFoundException
     * @throws CardNotAssignableException
     * @throws CardTypeNotFoundException
     * @throws AccountTypeNotFoundException
     * @throws CardNotFoundException
     * @throws PanNumberNotProvidedException
     */
    @GetMapping("/v1/cards/assign/{cardHolderId}/{pan}")
    @Timed
    @ExceptionMetered
    @PreAuthorize("hasAnyAuthority('card_assignment')")
    public ResponseEntity<Card> cardAssignment(@PathVariable("cardHolderId") Optional<Long> cardHolderId,
                                               @PathVariable("pan") Optional<String> pan) throws CardHolderNotFoundException,
            CardNotAssignableException,
            CardTypeNotFoundException,
            AccountTypeNotFoundException,
            CardNotFoundException, PanNumberNotProvidedException {
        if(!pan.isPresent() || !cardHolderId.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cardService.assignExistingCard(cardHolderId.get(),pan.get()), HttpStatus.OK);
    }


    /**
     * POST  /cards : Create a new card.
     *
     * @param card the cardHolder to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cardHolder, or with status 400 (Bad Request) if the card has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/v1/cards")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_create')")
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) throws URISyntaxException {

        if (card.getId() != null) {
            log.debug("Card: createCard: Unable to create a card with an id:  {}", card);
            return ResponseEntity.badRequest().body(null);
        }
        Card result = cardService.save(card);
        return ResponseEntity.created(new URI("/v1/api/cards/" + result.getId()))
                .body(result);
    }


    /**
     * GET  /cards/perso/{pan} : card personalization command to update card status and timing.
     *
     * @param pan card pan number to personalized
     * @return the ResponseEntity with status 201 (Created) and with body the new cardHolder, or with status 400 (Bad Request) if the pan not provided
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @GetMapping("/v1/cards/perso/{pan}")
    @Timed
    @ExceptionMetered
    @PreAuthorize("hasAnyAuthority('card_perso')")
    public ResponseEntity<BaseResponse> cardPersonalization(@PathVariable("pan") Optional<String> pan)
            throws CardNotFoundException, PanNumberNotProvidedException {

        if (!pan.isPresent()) {
            throw new PanNumberNotProvidedException();
        }
        Card result = cardService.personalizeCard(pan.get());

        BaseResponse response = new BaseResponse();
        response.setMessage(String.format("card with pan: %s personalized.",pan.get()));
        response.setCode(BaseResponse.SUCCESSFUL);
        response.setSuccess(true);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().body(response);
    }


    /**
     * GET  /v1/cards : get all the cards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of operations in body
     */
    @GetMapping("/v1/cards")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_fetch')")
    public ResponseEntity<List<CardInfoResponse>> getAllCards(Pageable pageable) {
        log.debug("REST request to get a page of Operations");

      List<CardInfoResponse> response=  cardService.findAll(pageable).getContent().stream()
                .map(card -> cardMapper.createCardInfoResponse(card))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
