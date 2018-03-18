package com.infotech.cms.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Byte To Hex
 *
 * @author Mohamamd Reza Alagheband
 */
public class ByteToHexStringSerializer extends JsonSerializer<Byte> {
    @Override
    public void serialize(Byte bt, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(String.format("%02x", new Object[]{Byte.valueOf(bt)}));
    }
}
