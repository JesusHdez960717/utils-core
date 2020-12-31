package com.root101.utils.jackson.serializer_deserializer.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode root = p.getCodec().readTree(p);
        int d = Integer.parseInt(((TextNode) root.get("dia")).textValue());
        int m = Integer.parseInt(((TextNode) root.get("mes")).textValue());
        int a = Integer.parseInt(((TextNode) root.get("anno")).textValue()) - 1900;
        return new Date(a, m, d);
    }
}
