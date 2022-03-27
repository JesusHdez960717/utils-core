/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
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
package dev.root101.utils.others;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * Clase auxiliar para darle formato predefinido a un String.<\br>
 * Por ejemplo, se quiere convertir el String "12345679.123456" a dinero, la
 * transformacion seria el String "$123 456 789.12"
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class StringFormating {

    public static String formatToMoney(BigDecimal number, String moneda) {
        return "$ " + formatToMoney(number) + " " + moneda;
    }

    public static String formatToMoney(String number) {
        if (number.trim().isEmpty()) {
            number = "0";
        }
        return formatToMoney(new BigDecimal(number));
    }

    public static String formatToMoney(BigDecimal number) {
        BigDecimal rounded = number.setScale(2, RoundingMode.HALF_EVEN);//round to 2places

        StringTokenizer str = new StringTokenizer(rounded.toString(), ".");
        String integerPart = str.nextToken();

        String reverse = new StringBuilder(integerPart).reverse().toString();
        String split = reverse.replaceAll("...", "$0 ").trim();
        String integerFix = new StringBuilder(split).reverse().toString();

        String answ = integerFix + "." + str.nextToken().replaceAll("...", "$0 ").trim();
        return answ;
    }

}
