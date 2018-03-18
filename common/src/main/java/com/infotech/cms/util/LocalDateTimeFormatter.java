package com.infotech.cms.util;

import com.infotech.util.LocalDateTimeConverterFormatter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * converter/formatter for LocalDateTime.
 *
 * @author Mohamamd Reza Alagheband
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {


    @Override
    public LocalDateTime parse(String formatted, Locale locale) throws ParseException {

        return LocalDateTimeConverterFormatter.parse(formatted, locale, "yyyy/MM/dd HH:mm:ss");
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {

        return LocalDateTimeConverterFormatter.print(dateTime, locale, "yyyy/MM/dd HH:mm:ss");
    }
}
