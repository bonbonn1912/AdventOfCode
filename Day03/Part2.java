package Day03;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Part2 {

    public static char digit = '0';
    public static char digit1 = '1';
    public static LinkedList<String> number1List = new LinkedList<String>();
    public static LinkedList<String> number2List = new LinkedList<String>();
    public static LinkedList<String> toBeRemoved1 = new LinkedList<String>();
    public static LinkedList<String> toBeRemoved2 = new LinkedList<String>();

    public static void main(String[] args) throws Exception {
        File input = new File("PATH TO INPUT TXT FILE");

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                number1List.add(line);
                number2List.add(line);
            }
        }
        System.out.println(createNumber(number1List, toBeRemoved1, 1) * createNumber(number2List, toBeRemoved2, 2));
    }
    public static int createNumber(LinkedList<String> inputList1, LinkedList<String> toBeRemoved1, int digit) {
        int i = 0;
        while (inputList1.size() != 1) {
            if (digit == 1) {
                char mostCommonBit = createMostCommonBit(inputList1, i);
                createUselessInputs(inputList1, mostCommonBit, i, toBeRemoved1);
                removeEntries(inputList1, toBeRemoved1);
            } else {
                char LessCommonBit = createLessCommonBit(inputList1, i);
                createUselessInputs(inputList1, LessCommonBit, i, toBeRemoved1);
                removeEntries(inputList1, toBeRemoved1);
            }
            i++;
        }
        int decimal = 0;
        for (String rest : inputList1) {
            decimal = Integer.parseInt(rest, 2);
        }
        return decimal;
    }
    public static char createMostCommonBit(LinkedList<String> inputMost, int index) {
        int countOne = 0;
        int countZero = 0;
        for (String number : inputMost) {
            if (number.charAt(index) != digit) {
                countOne++;
            } else {
                countZero++;
            }
        }
        if (countOne == countZero) {
            return '1';
        }
        if (countOne > inputMost.size() / 2) {
            return '1';
        } else {
            return '0';
        }
    }
    public static char createLessCommonBit(LinkedList<String> inputLess, int index) {
        int countOne = 0;
        int countZero = 0;
        for (String number : inputLess) {
            if (number.charAt(index) != digit1) {
                countZero++;
            } else {
                countOne++;
            }
        }
        if (countOne == countZero) {
            return '0';
        }
        if (countOne > inputLess.size() / 2) {
            return '0';
        } else {
            return '1';
        }
    }
    public static void createUselessInputs(LinkedList<String> input, char starting, int position,
            LinkedList<String> removalList) {
        for (String entry : input) {
            if (entry.charAt(position) != starting) {
                removalList.add(entry);
            }
        }
    }
    public static void removeEntries(LinkedList<String> List, LinkedList<String> toBeRemoved) {
        for (String input : toBeRemoved) {
            List.remove(input);
        }
    }
}
