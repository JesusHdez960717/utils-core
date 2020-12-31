package com.root101.json.jackson.serializer_deserializer.local_date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String toString = p.getCodec().readValue(p, String.class).replace(" ", "");
        return LocalDate.parse(toString, LocalDateJsonSerializer.LOCAL_DATE_FORMATTER);
    }
}
