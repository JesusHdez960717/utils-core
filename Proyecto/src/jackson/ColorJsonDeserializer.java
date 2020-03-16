/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Yo
 */
public class ColorJsonDeserializer extends JsonDeserializer<Color> {

        @Override
        public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            TreeNode root = p.getCodec().readTree(p);
            int r = Integer.parseInt(((TextNode) root.get("r")).textValue());
            int g = Integer.parseInt(((TextNode) root.get("g")).textValue());
            int b = Integer.parseInt(((TextNode) root.get("b")).textValue());
            int a = Integer.parseInt(((TextNode) root.get("a")).textValue());
            return new Color(r, g, b, a);
        }
    }
