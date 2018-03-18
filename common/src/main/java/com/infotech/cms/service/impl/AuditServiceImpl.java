package com.infotech.cms.service.impl;

import com.infotech.cms.domain.Audit;
import com.infotech.cms.repository.AuditRepository;
import com.infotech.cms.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service impl for Audit domain object
 * generated at 2016-11-06T16:04:08.758 by Infotech code generator */
@Service
public class AuditServiceImpl extends BaseServiceImpl<Audit> implements AuditService {
  private final AuditRepository auditRepository;

  @Autowired
  public AuditServiceImpl(AuditRepository auditRepository) {
    super(auditRepository);
    this.auditRepository = auditRepository;
  }
}
