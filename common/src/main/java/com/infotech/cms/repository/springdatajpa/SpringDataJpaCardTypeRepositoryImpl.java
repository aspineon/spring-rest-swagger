package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * custom implementation for CardType repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public class SpringDataJpaCardTypeRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<CardType> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT cardType from CardType cardType", CardType.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
