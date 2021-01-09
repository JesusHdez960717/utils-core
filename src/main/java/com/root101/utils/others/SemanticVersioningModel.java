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
package com.root101.utils.others;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class SemanticVersioningModel implements Serializable, Comparable<SemanticVersioningModel> {

    private int mayor;
    private int minor;
    private int bug;
    private String extra = "";

    public SemanticVersioningModel() {
    }

    public SemanticVersioningModel(String version) {
        StringTokenizer l = new StringTokenizer(version, ".");
        mayor = Integer.parseInt(l.nextToken());
        minor = Integer.parseInt(l.nextToken());
        bug = Integer.parseInt(l.nextToken());
        while (l.hasMoreTokens()) {
            extra += ".";
            extra += l.nextToken();
        }
    }

    public SemanticVersioningModel(int mayor, int minor, int bug) {
        this.mayor = mayor;
        this.minor = minor;
        this.bug = bug;
    }

    public SemanticVersioningModel(int mayor, int minor, int bug, String extra) {
        this.mayor = mayor;
        this.minor = minor;
        this.bug = bug;
        this.extra = extra;
    }

    public int getMayor() {
        return mayor;
    }

    public void setMayor(int mayor) {
        this.mayor = mayor;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getBug() {
        return bug;
    }

    public void setBug(int bug) {
        this.bug = bug;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return mayor + "." + minor + "." + bug + extra;
    }

    @Override
    public int compareTo(SemanticVersioningModel o) {
        int compMayor = Integer.compare(mayor, o.mayor);
        int compMinor = Integer.compare(minor, o.minor);
        int compBug = Integer.compare(bug, o.bug);
        if (compMayor == 0) {
            if (compMinor == 0) {
                return compBug;
            } else {
                return compMinor;
            }
        } else {
            return compMayor;
        }
    }

    public static boolean isSemanticVersioning(String ver) {
        StringTokenizer l = new StringTokenizer(ver, ".");
        if (l.countTokens() < 3) {
            return false;
        }
        try {
            int mayor = Integer.parseInt(l.nextToken());
            int minor = Integer.parseInt(l.nextToken());
            int bug = Integer.parseInt(l.nextToken());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
