package com.root101.utils.jackson.serializer_deserializer.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class DateJsonSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeFieldName("dia");
        gen.writeString(String.valueOf(value.getDate()));

        gen.writeFieldName("mes");
        gen.writeString(String.valueOf(value.getMonth()));

        gen.writeFieldName("anno");
        gen.writeString(String.valueOf(value.getYear() + 1900));

        gen.writeEndObject();
    }
}
