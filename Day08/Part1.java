import java.io.File;
import java.io.FileInputStream;

public class Part1 {

    public static void main(String [] args) throws Exception{


        String input = InputReaderDay08.InputReader("D:\\Coding\\CalenderCode\\inputday8.txt");

        String[] singeLines = input.split("\n");
        String[] outputDigits = new String[10];
        int counter = 0;
        for(String Line : singeLines){
            outputDigits = Line.split("\\|")[1].split("\\s+");
            for(String digit : outputDigits){
                if(checkIfNumberIsUnique(digit)) counter++;
            }
        }
        System.out.println(counter);
    }

    public static boolean checkIfNumberIsUnique(String singleDigit){
        final int four = 4;
        final int one = 2;
        final int seven = 3;
        final int eight = 7;

       switch (singleDigit.length()){
           case four: return true;
           case one: return true;
           case seven: return true;
           case eight: return true;
           default: return false;
       }
    }

}

class InputReaderDay08{

    public static String InputReader(String path) throws Exception{
        File inputFile = new File(path);
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        int i;
        String InputTxt = "";
        while ((i = fileInputStream.read()) != -1){
            InputTxt += (char)i;
        }
        return InputTxt;
    }

}
