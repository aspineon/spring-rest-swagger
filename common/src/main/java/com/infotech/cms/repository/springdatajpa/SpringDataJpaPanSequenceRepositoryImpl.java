package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.PanSequence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * custom implementation for PanSequence repository.
 *
 * @author Mohamamd Reza Alagheband
 */
public class SpringDataJpaPanSequenceRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<PanSequence> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT panSequence from PanSequence panSequence", PanSequence.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
