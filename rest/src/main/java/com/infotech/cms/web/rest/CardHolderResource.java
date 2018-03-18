package com.infotech.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.service.CardHolderService;
import com.infotech.cms.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing card holder data
 *
 * @author MohammadReza Alagheband
 */
@RestController
@RequestMapping(value = "/api")
public class CardHolderResource {

    private final Logger log = LoggerFactory.getLogger(CardHolderResource.class);

    @Autowired
    private CardHolderService cardHolderService;

    public CardHolderResource(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    /**
     * POST  /card-holders : Create a new cardHolder.
     *
     * @param cardHolder the cardHolder to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cardHolder, or with status 400 (Bad Request) if the cardHolder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/v1/card-holders")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_holder_create')")
    public ResponseEntity<CardHolder> createCardHolder(@Valid @RequestBody CardHolder cardHolder) throws URISyntaxException {

        if (cardHolder.getId() != null) {
            log.debug("CardHolderResource: createCardHolder: Unable to create a cardHolder with an id:  {}", cardHolder);
            return ResponseEntity.badRequest().body(null);
        }
        CardHolder result = cardHolderService.save(cardHolder);
        return ResponseEntity.created(new URI("/v1/api/card-holders/" + result.getId()))
                .body(result);
    }

    /**
     * PUT  /card-holders : Updates an existing cardHolder.
     *
     * @param cardHolder the cardHolder to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cardHolder,
     * or with status 400 (Bad Request) if the cardHolder is not valid,
     * or with status 500 (Internal Server Error) if the cardHolder couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/v1/card-holders")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_holder_update')")
    public ResponseEntity<CardHolder> updateCardHolder(@Valid @RequestBody CardHolder cardHolder)  {
        log.debug("REST request to update CardHolder : {}", cardHolder);
        if (cardHolder.getId() == null) {
            log.debug("CardHolderResource: updateCardHolder: Unable to create a cardHolder with an id:  {}", cardHolder);
            return ResponseEntity.badRequest().body(null);
        }
        CardHolder result = cardHolderService.save(cardHolder);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * GET  /card-holders : get all the cardHolders.
     *
     * @param pageable paging parameter to return data of the corresponding page
     * @return the ResponseEntity with status 200 (OK) and the list of cardHolders in body
     */
    @GetMapping("/v1/card-holders")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_holder_fetch')")
    public List<CardHolder> getAllCardHolders(Pageable pageable) {
        log.debug("REST request to get all CardHolders");
        return cardHolderService.findAll(pageable).getContent();
    }

    /**
     * GET  /card-holders/:id : get the "id" cardHolder.
     *
     * @param id the id of the cardHolder to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cardHolder, or with status 404 (Not Found)
     */
    @GetMapping("/v1/card-holders/{id}")
    @Timed
    @PreAuthorize("hasAnyAuthority('card_holder_fetch')")
    public ResponseEntity<CardHolder> getCardHolder(@PathVariable Long id) {
        CardHolder cardHolder = cardHolderService.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cardHolder));
    }




}
