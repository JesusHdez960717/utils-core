package com.jhw.utils.jackson.serializer_deserializer.local_date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode root = p.getCodec().readTree(p);
        return LocalDate.parse(((TextNode) root.get("date")).textValue());
    }
}
