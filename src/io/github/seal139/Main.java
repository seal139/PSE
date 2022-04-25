/**
 * 
 */
package io.github.seal139;

import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Septian Pramana R
 *
 */
public class Main {

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");
            String input = s.nextLine();

            Postfix p = new Postfix(input);
            p.tokenize();
            System.out.println(p.toString());
        }
    }

}
