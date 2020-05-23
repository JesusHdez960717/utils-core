package others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
@Data
public class SDF {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat SDF_MONTH = new SimpleDateFormat("MMMM/yyyy");
    public static final SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat SDF_HMS = new SimpleDateFormat("hh:mm:ss");

    public static String format(Date d) {
        return SDF.format(d);
    }

    public static Date parse(String s) throws ParseException {
        return SDF.parse(s);
    }

    public static String formatMonth(Date d) {
        return SDF_MONTH.format(d);
    }

    public static Date parseMonth(String s) throws ParseException {
        return SDF_MONTH.parse(s);
    }

    public static String formatYear(Date d) {
        return SDF_YEAR.format(d);
    }

    public static Date parseYear(String s) throws ParseException {
        return SDF_YEAR.parse(s);
    }

    public static String formatHMS(Date d) {
        return SDF_HMS.format(d);
    }

    public static Date parseHMS(String s) throws ParseException {
        return SDF_HMS.parse(s);
    }
}
