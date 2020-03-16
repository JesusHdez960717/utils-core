/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Yo
 */
public class ColorJsonSerializer extends JsonSerializer<Color> {

    @Override
    public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeFieldName("r");
        gen.writeString(String.valueOf(value.getRed()));

        gen.writeFieldName("g");
        gen.writeString(String.valueOf(value.getGreen()));

        gen.writeFieldName("b");
        gen.writeString(String.valueOf(value.getBlue()));

        gen.writeFieldName("a");
        gen.writeString(String.valueOf(value.getAlpha()));
        
        gen.writeEndObject();
    }
}
