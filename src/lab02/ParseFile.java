/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author $Mark Hall
 */
public class ParseFile {

    private String[] array;

    public ParseFile() {

    }

    public ParseFile(String fileName) {

        try {
            Scanner sc = new Scanner(new File(fileName));
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            array = lines.toArray(new String[0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] createOutput(ArrayList<String> input) {

        ArrayList<String> output = new ArrayList<>();

        // Now, I make some sort of switch case or something inside of a for 
        // loop.  But first, we have to remember that there are 14 commands,
        // so there has to be 14 if or switch statements
        for (String textIn : input) {
            if (textIn.contains("NOP")) {
                output.add("00");
            } else if (textIn.contains("MOVIR0")) {
                // as it goes, there are only four pieces of binary.  So we
                // simply grab everything after 
                int i = textIn.lastIndexOf("MOVIR0") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("1" + hexStr.toUpperCase());
            } else if (textIn.contains("MOVIR1")) {
                int i = textIn.lastIndexOf("MOVIR1") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("2" + hexStr.toUpperCase());

            } else if (textIn.contains("DISPLAY")) {
                output.add("30");

            } else if (textIn.contains("MOVAR0")) {
                output.add("40");

            } else if (textIn.contains("MOVAR1")) {
                output.add("50");

            } else if (textIn.contains("ADD")) {
                output.add("60");

            } else if (textIn.contains("AND")) {
                output.add("70");

            } else if (textIn.contains("XOR")) {
                output.add("90");

            } else if (textIn.contains("OR")) {
                output.add("80");
                
            } else if (textIn.contains("MOVR0M")) {
                int i = textIn.lastIndexOf("MOVR0M") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("A" + hexStr.toUpperCase());

            } else if (textIn.contains("MOVR1M")) {
                int i = textIn.lastIndexOf("MOVR1M") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("B" + hexStr.toUpperCase());

            } else if (textIn.contains("MOVMR0")) {
                int i = textIn.lastIndexOf("MOVMR0") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("C" + hexStr.toUpperCase());

            } else if (textIn.contains("MOVMR1")) {
                int i = textIn.lastIndexOf("MOVMR1") + 7;
                int j = i + 4;
                String binary = new String();
                for (; i < j; i++) {
                    binary += textIn.charAt(i);
                }
                int decimal = Integer.parseInt(binary, 2);
                String hexStr = Integer.toString(decimal, 16);
                output.add("D" + hexStr.toUpperCase());
            }

        }

        array = output.toArray(new String[0]);
        return array;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

}
