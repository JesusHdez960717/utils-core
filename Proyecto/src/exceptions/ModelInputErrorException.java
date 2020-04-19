/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Yo
 */
public class ModelInputErrorException extends RuntimeException {

    public ModelInputErrorException() {
    }

    public ModelInputErrorException(String message) {
        super(message);
    }

}
