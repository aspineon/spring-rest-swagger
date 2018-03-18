package com.infotech.cms.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.infotech.util.LocalDateConverterFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Mohamamd Reza Alagheband
 */
public class CustomJsonLocalDateSerializer extends JsonSerializer<LocalDate> {
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Locale locale = LocaleContextHolder.getLocale();
        String formattedDate = LocalDateConverterFormatter.print(date, locale, "yyMMdd");
        gen.writeString(formattedDate);
    }
}
