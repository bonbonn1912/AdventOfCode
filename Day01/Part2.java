package Day01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws Exception {

        File input = new File("PATH TO INPUT TXT FILE");
        InputStream readFile = new FileInputStream(input);
        List<Integer> numbers = new LinkedList<Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                int a = Integer.parseInt(line);
                numbers.add(a);

            }
        }

        int summ = 0;
        List<Integer> summs = new LinkedList<>();

        for (int i = 0; i < numbers.size() - 2; i++) {
            summ += numbers.get(i);
            summ += numbers.get(i + 1);
            summ += numbers.get(i + 2);
            System.out.println(summ);
            summs.add(summ);
            summ = 0;

        }
        int counter = 0;
        for (int i = 0; i < summs.size() - 1; i++) {
            if (summs.get(i) < summs.get(i + 1)) {
                counter++;
            }
        }

        readFile.close();
        System.out.println(counter);

    }
}
