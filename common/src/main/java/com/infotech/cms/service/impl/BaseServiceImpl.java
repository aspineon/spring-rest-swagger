package com.infotech.cms.service.impl;

import com.infotech.cms.service.BaseService;
import com.infotech.cms.repository.BaseRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * base service implementation for domain objects.
 *
 * @author Mohamamd Reza Alagheband
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected final BaseRepository<T> repository;

    public BaseServiceImpl(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(long id) {
        return repository.findById(id);
    }
}
