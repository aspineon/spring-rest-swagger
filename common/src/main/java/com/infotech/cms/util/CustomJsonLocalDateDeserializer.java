package com.infotech.cms.util;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.infotech.util.LocalDateConverterFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Mohamamd Reza Alagheband
 */
public class CustomJsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Locale locale = LocaleContextHolder.getLocale();
        LocalDate date = null;

        try {
            date = LocalDateConverterFormatter.parse(jsonParser.getValueAsString(), locale, "yyMMdd");
            return date;
        } catch (ParseException var6) {
            throw new JsonParseException(var6.getMessage(), (JsonLocation)null);
        }
    }
}
