import java.io.File;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.LinkedList;

public class Part1 {

    public static void main(String[] args) throws Exception {

        LinkedList<Integer> inputs = InputReader.getInput("PATH TO INPUT TXT FILE");
        Comparator<Integer> order = Integer::compare;
        inputs.sort(order.reversed());
        System.out.println(solve(inputs, inputs.get(inputs.size() / 2)));

    }

    public static int solve(LinkedList<Integer> inputs, int avgValue) {
        int distanceToEachElement = 0;
        for (Integer number : inputs) {
            distanceToEachElement += Math.abs(avgValue - number);
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
