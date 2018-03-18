package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * custom implementation for CardHolder repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public class SpringDataJpaCardHolderRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<CardHolder> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT cardHolder from CardHolder cardHolder", CardHolder.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
