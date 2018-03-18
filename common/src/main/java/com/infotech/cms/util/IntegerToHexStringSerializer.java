package com.infotech.cms.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Mohamamd Reza Alagheband
 */
public class IntegerToHexStringSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//        jsonGenerator.writeObject(Integer.toHexString(integer));
        jsonGenerator.writeObject(String.format("%08x",integer.intValue()));

    }
}
