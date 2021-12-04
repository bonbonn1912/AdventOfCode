import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Part1_refactored {
    public static void main(String[] args) throws Exception {
        ArrayList<String> inputList = new ArrayList<String>();
        File input = new File("INPUT TO TXT FILE");
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputList.add(line);
            }
        }
        String mostCommonBit = "";
        for (int i = 0; i < inputList.get(0).length(); i++) {
            if (createMostCommonBit(inputList, i)) {
                mostCommonBit += "1";
            } else {
                mostCommonBit += "0";
            }
        }
        System.out.println(calcuteProduct(mostCommonBit));
    }

    public static int calcuteProduct(String binary_part1) {
        String inversed = "";
        for (char c : binary_part1.toCharArray()) {
            if (c == '1') { inversed += '0'; } else {inversed += '1';} }
        return (Integer.parseInt(inversed, 2) * Integer.parseInt(binary_part1, 2));
    }

    public static boolean createMostCommonBit(ArrayList<String> inputLess, int index) {
        int countOne = 0;
        int countZero = 0;
        for (String number : inputLess) {
            if (number.charAt(index) == '1') { countOne++;} else {countZero++;}}
        return countOne > countZero;
    }
}
