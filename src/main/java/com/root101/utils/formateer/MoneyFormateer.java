/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.utils.formateer;

import java.text.ParseException;
import java.util.StringTokenizer;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MoneyFormateer extends AbstractFormatter {

    public static MoneyFormateer from() {
        return new MoneyFormateer();
    }

    public static final String MIDDLE = " ";

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null) {
            return "";
        }
        return text.replace(MIDDLE, "").replace(",", ".");
    }

    @Override
    public String valueToString(Object object) throws ParseException {
        if (object == null) {
            return "";
        }
        String strInit = object.toString();
        if (strInit.trim().isEmpty()) {
            return "";
        }
        String initialString = strInit.replace(",", ".").replace(MIDDLE, "");

        StringTokenizer str = new StringTokenizer(initialString, ".");
        String integerPart = str.nextToken();

        String reverse = new StringBuilder(integerPart).reverse().toString();
        String split = reverse.replaceAll("...", "$0" + MIDDLE).trim();
        String integerFix = new StringBuilder(split).reverse().toString();

        String answ = integerFix;
        if (str.hasMoreTokens()) {
            answ += "." + str.nextToken().replaceAll("...", "$0" + MIDDLE).trim();;
        } else if (initialString.contains(".")) {
            answ += ".";
        }
        return answ;
    }

}
