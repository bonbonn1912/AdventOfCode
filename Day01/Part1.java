package Day01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws Exception {

        File input = new File("PATH TO INPUT TXT FILE");
        List<Integer> numbers = new LinkedList<Integer>();
        int counter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                Integer a = Integer.parseInt(line);
                numbers.add(a);
            }
        }

        readFile.close();

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                counter++;
            }
        }

        System.out.println(counter);

    }
}
