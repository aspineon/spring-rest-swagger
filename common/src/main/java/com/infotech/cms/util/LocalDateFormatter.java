package com.infotech.cms.util;

import com.infotech.util.LocalDateConverterFormatter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * converter/formatter for LocalDateTime.
 *
 * @author Mohamamd Reza Alagheband
 */
public class LocalDateFormatter implements Formatter<LocalDate> {



    @Override
    public LocalDate parse(String formatted, Locale locale) throws ParseException {
        return LocalDateConverterFormatter.parse(formatted, locale, "yyyy/MM/dd");
    }

    @Override
    public String print(LocalDate dateTime, Locale locale) {
        return LocalDateConverterFormatter.print(dateTime, locale, "yyyy/MM/dd");
    }
}
