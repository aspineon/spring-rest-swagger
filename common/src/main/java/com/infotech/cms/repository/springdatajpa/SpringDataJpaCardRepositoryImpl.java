package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * custom implementation for Card repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public class SpringDataJpaCardRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<Card> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT card from Card card", Card.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
