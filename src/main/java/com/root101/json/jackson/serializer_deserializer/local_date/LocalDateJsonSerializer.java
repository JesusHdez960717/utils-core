package com.root101.json.jackson.serializer_deserializer.local_date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {

    //Formato por defecto, si se cambia a otro da error
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;//DateTimeFormatter.ofPattern("uuuu-MM-dd");

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(LOCAL_DATE_FORMATTER.format(value));
    }
}
