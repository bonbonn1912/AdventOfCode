import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws Exception {
        Long[] lifetimes = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };
        int days = 256;
        Long finalcount = 0L;
        File input = new File("PATH TO INPUT TXT FILE");
        try (
                BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] tempinitials = line.split(",");
                for (String singleState : tempinitials) {
                    lifetimes[Integer.parseInt(singleState)]++;
                }
            }
        }

        for (int i = 0; i < days; i++) {

            long fishesWithLifeTime0 = lifetimes[0];
            for (int j = 0; j < lifetimes.length-1; j++) {
                lifetimes[j] = lifetimes[j + 1];
            }
            lifetimes[6] += fishesWithLifeTime0;
            lifetimes[8] = fishesWithLifeTime0;

        }
        for (long value : lifetimes) {
            finalcount += value;
        }
        System.out.println(finalcount);

    }
}
