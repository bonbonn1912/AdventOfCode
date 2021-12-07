import java.util.LinkedList;
import java.io.*;
import java.util.stream.*;
import java.util.*;
public class Part1 {

    public static void main(String[] args) throws Exception {
       
     List<Integer> allNumbers = StringInputReader.returnCommands();

     int biggestNummber = Collections.max(allNumbers);
     int smallestNummber = Collections.min(allNumbers);

     
     int maxfuel = 100000;
     int newfuel = 0;
     int bestNumber = 0;

     for(Integer number : allNumbers) {
         newfuel = 0;
        System.out.println("Äußere Zahl : " +number);
         for(Integer i : allNumbers) {
            System.out.println("Innere Zahl : " +i);
            double betrag = Math.sqrt((double) (Math.pow(i-number, 2)));
            System.out.println("Difference" +(int)betrag);
            newfuel += betrag;
            if(newfuel < maxfuel) {
                System.out.println("Für Nummber: " + number +" ist der Betrag : " + maxfuel);
                bestNumber  =number;
                maxfuel = newfuel;
            }
         }
         
         
     }


     System.out.println(newfuel);
     System.out.println(bestNumber);
    // System.out.println(biggestNummber);
      
    }
}

class StringInputReader {

   

    static List<Integer> returnCommands() throws Exception {
        File file = new File("D:\\Coding\\CalenderCode\\inputday7.txt");

        InputStream inputStream = null;
        List<Integer> ints = new LinkedList<Integer>();
        int i;
        char c;
        String text = "";

        try{
            inputStream = new FileInputStream(file);
            while ((i = inputStream.read()) != -1){
                c = (char)i;
                text += c;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ints = Arrays.stream(text.split(","))
          .map(Integer::parseInt)
          .collect(Collectors.toList());
         
            inputStream.close();
        }
        return ints;
    }

}
