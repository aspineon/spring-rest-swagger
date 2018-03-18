package com.infotech.cms.repository.springdatajpa;

import com.infotech.cms.domain.Audit;
import com.infotech.cms.repository.AuditRepository;
import org.springframework.data.repository.Repository;

/**
 * spring data jpa repository impl for Audit repository
 * generated at 2016-11-06T16:04:08.757 by Infotech code generator */
public interface SpringDataJpaAuditRepository extends AuditRepository, Repository<Audit, Long> {
}
