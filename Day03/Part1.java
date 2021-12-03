package Day03;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws Exception {

        int[] digits = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int[] binaryOutput = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int[] binaryReverse = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        char digit = '0';

        File input = new File("PATH TO INPUT TXT FILE");
        List<String> binarys = new LinkedList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                binarys.add(line);
            }
        }

        for (String number : binarys) {
            for (int i = 0; i < 12; i++) {
                if (number.charAt(i) == digit) {
                    digits[i] = digits[i] + 1;
                }
            }
        }

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] < (binarys.size() / 2)) {
                binaryOutput[i] = 1;
            }
        }

        int binaryOutputToDecimal = 0;
        int counter = 11;
        for (int bo : binaryOutput) {
            if (bo != 0) {
                binaryOutputToDecimal += pow(2, counter);
            }
            counter--;
        }

        for (int i = 0; i < binaryOutput.length; i++) {
            binaryReverse[i] -= binaryOutput[i];
        }

        int binaryReverseOutputToDecimal = 0;
        counter = 11;
        for (int bo : binaryReverse) {
            if (bo != 0) {
                binaryReverseOutputToDecimal += pow(2, counter);
            }
            counter--;
        }
        System.out.println(binaryOutputToDecimal * binaryReverseOutputToDecimal);
    }

    public static int pow(int base, int exponent) {
        int result = 1;
        while (exponent != 0) {
            result *= base;
            --exponent;
        }
        return result;
    }
}
