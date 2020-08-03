package com.jhw.utils.others;

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
