import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Part1_refactored {
    public static void main(String[] args) throws Exception {
        ArrayList<String> inputList = new ArrayList<String>();
        File input = new File("INPUT TO TEXT FILE");
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
        System.out.println(Integer.parseInt(mostCommonBit,2)*Integer.parseInt(mostCommonBit.replace("1", "x").replace("0","1").replace("x", "0"),2));
    }

    public static boolean createMostCommonBit(ArrayList<String> inputLess, int index) {
        int countOne = 0;
        int countZero = 0;
        for (String number : inputLess) {
            if (number.charAt(index) == '1') { countOne++;} else {countZero++;}}
        return countOne > countZero;
    }
}
