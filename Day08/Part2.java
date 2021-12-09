import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

public class Part2 {

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

    public static int checkIfNumberIsUnique(String[] wholeOutputforOneLine){
        int valueForThisLine = 0;
        LinkedList<String> noUnique = new LinkedList<String>();
        final int NumberFour = 4;
        final int NumberOne = 2;
        final int NumberSeven = 3;
        final int NumberEight = 7;
        
        for(String singleOutput : wholeOutputforOneLine){
            switch(singleOutput.length()) {
                case NumberFour: valueForThisLine += 4;
                case NumberOne: valueForThisLine +=  1;
                case NumberSeven: valueForThisLine += 7;
                case NumberEight: valueForThisLine += 8;
                default: noUnique.add(singleOutput);
            }
        }    

         // Two segements: 1
        // Three segements : 7
        // Four segments : 4
        // Five segments : 5,2,3
        // Six segments : 6, 9, 0
        // Seven segments : 8, 

        /*
            Difference between 5, 2 & 3
            2 & 3 : Four same segments
            2 & 5 : Three same segments
            3 & 5 : Four Same segments;
        */
        for()
       switch (singleDigit.length()){
           
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
