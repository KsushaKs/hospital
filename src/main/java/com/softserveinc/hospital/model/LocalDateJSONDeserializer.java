package com.softserveinc.hospital.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by ksu on 03.04.16.
 */
public class LocalDateJSONDeserializer extends JsonDeserializer<LocalDate> {


    private static DateTimeFormatter formatter =
            DateTimeFormat.forPattern("dd-MM-yyyy");

    @Override
    public LocalDate deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) {
        try {
            return LocalDate.parse(jsonparser.getText(), formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
