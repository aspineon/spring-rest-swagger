package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.CardHolderType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * custom implementation for CardHolderType repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public class SpringDataJpaCardHolderTypeTypeRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<CardHolderType> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT cardHolderType from CardHolderType cardHolderType", CardHolderType.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
