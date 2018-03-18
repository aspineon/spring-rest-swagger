package com.infotech.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * base service interface for domain objects.
 *
 * @author Mohamamd Reza Alagheband
 */
public interface BaseService<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    T findById(long id);
    T save(T entity);
    long getCount();
}
