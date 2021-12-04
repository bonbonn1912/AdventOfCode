import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Part2_refactored {

    public static void main(String[] args) throws Exception {
     ArrayList<String> inputList = new ArrayList<String>();
        File input = new File("INPUT TO TEXT FILE");

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputList.add(line);
            }
        }
        System.out.println(Integer.parseInt(removeToLast(inputList,0,true),2)*Integer.parseInt(removeToLast(inputList,0,false),2));
    }
    public static String removeToLast(ArrayList<String> liste, int index_, boolean mostBits) {
        if (liste.size() != 1) {
            if (createMostCommonBit(liste, index_)) {
                ArrayList<String> temp2 = new ArrayList<String>(
                        liste.stream().filter(n -> mostBits ? n.charAt(index_) == '1' : n.charAt(index_) == '0').collect(Collectors.toList()));
                return removeToLast(temp2, index_ + 1, mostBits);
            } else {
                ArrayList<String> temp2 = new ArrayList<String>(liste.stream().filter(n -> mostBits ? n.charAt(index_) == '0' : n.charAt(index_) == '1').collect(Collectors.toList()));
                return removeToLast(temp2, index_ + 1, mostBits);
            }
        } else {
            return liste.get(0);
        }
    }
    public static boolean createMostCommonBit(ArrayList<String> inputLess, int index) {
        int countOne = 0;
        int countZero = 0;
        for (String number : inputLess) {
            if (number.charAt(index) == '1') {
                countOne++;
            } else {
                countZero++;
            }
        }
        return countOne > countZero || countOne == countZero;
    }
    
}
