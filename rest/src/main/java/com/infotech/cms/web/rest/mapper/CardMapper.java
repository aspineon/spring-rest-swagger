package com.infotech.cms.web.rest.mapper;

import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.dto.CardInfoResponse;
import org.mapstruct.Mapper;

/**
 * A Mappar to create dto based on Card Entity
 * @author Mohammad Reza Alagheband
 */
@Mapper(componentModel = "spring")
public interface CardMapper {
    CardInfoResponse createCardInfoResponse(Card card);
}
