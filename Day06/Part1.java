import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

    static int daysRemaining = 256;

    public static void main(String[] args) throws Exception {
        File input = new File("D:\\Coding\\CalenderCode\\inputday6.txt");
        LinkedList<Long> initialStates = new LinkedList<Long>();
        LinkedList<Lanternfish> fishes = new LinkedList<Lanternfish>();
        LinkedList<Long> daysToLife = new LinkedList<Long>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] tempinitials = line.split(",");
                for (String singleState : tempinitials) {
                    initialStates.add(Long.parseLong(singleState));
 

                }
            }
        }
       initialStates.forEach(nL -> daysToLife.add(nL));
     //  initialStates.forEach(n -> fishes.add(new Lanternfish(n)));
        long start = System.currentTimeMillis();
        System.out.println(decreaseLifetimeinList(daysToLife,256));
    // System.out.println(decreaseLifetime(fishes));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static int decreaseLifetimeinList(LinkedList<Long> inputList, int days){
        System.out.println("Days Remaining: " +days);
        System.out.println("Current Amount of fishes: " +inputList.size());
        if (days == 0) {
            return inputList.size();
        }
        LinkedList<Long> tempList = new LinkedList<Long>();
            while(days !=0){
                for(Long lifetime : inputList){
                    lifetime--;
                    if(lifetime == -1){
                        lifetime = 6L;
                        tempList.add(lifetime);
                        tempList.add(8L);    
                    }else{
                        tempList.add(lifetime);
                    }
                }
                days--;
               return decreaseLifetimeinList(tempList, days);
            }
            return 0;


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