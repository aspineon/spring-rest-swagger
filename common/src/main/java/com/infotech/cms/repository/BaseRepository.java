package com.infotech.cms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * base repository for domain objects.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface BaseRepository<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    T findById(long id);
    T save(T entity);
    Long count();
}

