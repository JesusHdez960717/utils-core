/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

import jackson.JACKSON;
import java.io.File;
import java.io.IOException;
import testing.jackson.Person;

/**
 *
 * @author Yo
 */
public class TestingUtilsMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File act = new File(new File("").getAbsolutePath());

        File f = new File(act.getAbsolutePath() + "\\testing_jackson.JSON");
        JACKSON.write(f, new Person());

        Person load = JACKSON.read(f, Person.class);
        System.out.println(load);
    }
    
}
