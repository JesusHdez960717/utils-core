/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Yo
 */
public class JACKSON {

    private static ObjectMapper om;

    public static void write(File resultFile, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        if (om == null) {
            initObjectMapper();
        }
        om.writerWithDefaultPrettyPrinter().writeValue(resultFile, value);
    }

    public static <T extends Object> T read(File src, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        if (om == null) {
            initObjectMapper();
        }
        return om.readValue(src, valueType);
    }

    private static void initObjectMapper() {
        om = new ObjectMapper();

        SimpleModule colorModule = new SimpleModule("Color Module");
        colorModule.addSerializer(Color.class, new ColorJsonSerializer());
        colorModule.addDeserializer(Color.class, new ColorJsonDeserializer());

        om.registerModule(colorModule);
    }

    public static void registerModule(Module module) {
        om.registerModule(module);
    }
}
