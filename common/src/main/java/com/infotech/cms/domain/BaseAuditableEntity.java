package com.infotech.cms.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infotech.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Object Definition representing base auditable entity For ORM.
 * having id + auditing properties
 *
 * @author Mohamamd Reza Alagheband
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditableEntity extends BaseEntity {

    @JsonIgnore
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_created_by_user", nullable = true)
    protected User createdBy;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_date_time", nullable = true)
    protected LocalDateTime createdDateTime;

   @JsonIgnore
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_last_modified_by_user", nullable = true)
    protected User lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "last_modified_date_time", nullable = true)
    protected LocalDateTime lastModifiedDateTime;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }
        
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
}
