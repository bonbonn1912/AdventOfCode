import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

public class Part2 {

    public static void main(String[] args) throws Exception {

        LinkedList<Integer> inputs = InputReader.getInput("INPUT TO TXT FILE");
        double averageValueInArray = inputs.stream().mapToInt(Integer::intValue).sum() / inputs.size();
        System.out.println(solve(inputs, (int) averageValueInArray));
        // Doesnt Work with the Text Input since the avg. is != x,0;
    }

    public static int solve(LinkedList<Integer> inputs, int avgValue) {
        int distanceToEachElement = 0;
        for (Integer number : inputs) {
            distanceToEachElement += (Math.abs(avgValue - number) * (Math.abs(number - avgValue) + 1)) / 2; // TY GAUßß                                                                                                     
        }
        return distanceToEachElement;
    }
}

class InputReader {

    public static LinkedList<Integer> getInput(String path) throws Exception {
        File inputFile = new File(path);
        FileInputStream inputStream = new FileInputStream(inputFile);
        int i;
        String InputTxt = "";
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        while ((i = inputStream.read()) != -1) {
            InputTxt += (char) i;
        }
        for (String sNumber : InputTxt.split(",")) {
            numbers.add(Integer.parseInt(sNumber));
        }
        inputStream.close();
        return numbers;
    }
}
