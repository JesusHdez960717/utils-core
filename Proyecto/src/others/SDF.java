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

    public static String format(Date d) {
        return SDF.format(d);
    }

    public static Date parse(String s) throws ParseException {
        return SDF.parse(s);
    }

}
