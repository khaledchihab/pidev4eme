package com.example.PlateformeMobilite.Services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class HashSetDeserializer extends JsonDeserializer<HashSet<String>> {
    @Override
    public HashSet<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String value = jsonParser.readValueAs(String.class);
        String[] elements = value.replaceAll("[\\[\\]]", "").split(",\\s*");
        return new HashSet<>(Arrays.asList(elements));}
}
