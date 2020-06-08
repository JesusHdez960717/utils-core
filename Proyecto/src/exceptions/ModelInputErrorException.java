package exceptions;

import interfaces.Validable;
import java.util.ArrayList;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ModelInputErrorException extends IllegalArgumentException implements Validable {

    private ArrayList<IllegalArgumentException> all = new ArrayList<>();

    public ModelInputErrorException() {
    }

    public ModelInputErrorException(String message) {
        super(message);
    }

    public ModelInputErrorException(ArrayList<IllegalArgumentException> all) {
        this.all = all;
    }

    public void addException(IllegalArgumentException e) {
        all.add(e);
    }

    public void addException(String sms) {
        all.add(new ModelInputErrorException(sms));
    }

    @Override
    public boolean validate() {
        if (all.isEmpty()) {
            return true;
        } else {
            throw new ModelInputErrorException(all);
        }
    }

    @Override
    public String getMessage() {
        return all.isEmpty() ? super.getMessage() : all.get(0).getMessage();
    }

    public ArrayList<IllegalArgumentException> getAllExceptions() {
        return all;
    }

}
