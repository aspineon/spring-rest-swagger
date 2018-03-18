package com.infotech.cms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * holding sequence numbers used for PAN.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
@Entity
@Table(name = "pan_sequence")
public class PanSequence extends BaseEntity  {
}