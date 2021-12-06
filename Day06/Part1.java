import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

    static int daysRemaining = 80;

    public static void main(String[] args) throws Exception {
        File input = new File("PATH TO INPUT TXT FILE");
        LinkedList<Integer> initialStates = new LinkedList<Integer>();
        LinkedList<Lanternfish> fishes = new LinkedList<Lanternfish>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] tempinitials = line.split(",");
                for (String singleState : tempinitials) {
                    initialStates.add(Integer.parseInt(singleState));
                }
            }
        }
        initialStates.forEach(n -> fishes.add(new Lanternfish(n)));
        System.out.println(decreaseLifetime(fishes));
    }

    public static int decreaseLifetime(LinkedList<Lanternfish> fishes) {
        if (daysRemaining == 0) {
            return fishes.size();
        }
        LinkedList<Lanternfish> tempList = new LinkedList<Lanternfish>();
        while (daysRemaining != 0) {
            for (Lanternfish singleFish : fishes) {
                if (singleFish.decreaseLifeState()) {
                    tempList.add(singleFish);
                    tempList.add(new Lanternfish(8));
                } else {
                    tempList.add(singleFish);
                }
            }
            daysRemaining--;
            return decreaseLifetime(tempList);
        }
        return 0;
    }
}

class Lanternfish {
    private int lifeState;

    Lanternfish(int lifeState) {
        this.lifeState = lifeState;
    }

    public boolean decreaseLifeState() {
        this.lifeState--;
        if (this.lifeState < 0) {
            this.lifeState = 6;
            return true;
        }
        return false;
    }
}