package com.infotech.cms.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * simple business object representing a audit log.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
@Entity
@Table(name="audit")
public class Audit extends BaseEntity {

    @NotEmpty
    @Column(name = "username", nullable = true, length = 50)
    private String username;

    @Column(name = "action", nullable = true, length = 50)
    private String action;

    @Column(name = "info", nullable = true, length = 2000)
    private String info;

    @Column(name = "result", nullable = true, length = 2000)
    private String result;

    @Column(name = "dt", nullable = false)
    private LocalDateTime dateTime;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("Audit[username:%s, action: %s, info:%s, dateTime:%s, result: %s]", username, action, info, dateTime, result);
    }
}
