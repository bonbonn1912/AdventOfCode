
import java.util.Comparator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Part2withImageOutput {

    public static void main(String[] args) throws Exception {

        Field meinFeld = new Field(1000, 1000);

        LinkedList<String[]> input = StringInputReader.returnCommands();
        for (String[] Singleinput : input) {
            int x1 = Integer.parseInt(Singleinput[0].split(",")[0]);
            int y1 = Integer.parseInt(Singleinput[0].split(",")[1]);
            int x2 = Integer.parseInt(Singleinput[1].split(",")[0]);
            int y2 = Integer.parseInt(Singleinput[1].split(",")[1]);

            for (Punkt singlePoint : getPointsBetween(x1, y1, x2, y2)) {

                meinFeld.increasePixel(singlePoint.getX(), singlePoint.getY());
            } 
        }
       meinFeld.writeImage("outputimage");
     Integer[][] test = new Integer[][]{
                    {1,0,0,1,1,1,1,0,0},
                    {0,0,1,1,1,1,1,0,0},
                    {0,1,1,0,1,1,1,0,0},
                    {1,1,0,0,1,1,1,0,0},
                    {0,0,0,0,1,1,1,0,0},
                    {0,0,0,0,1,1,1,0,0},
                    {0,0,0,0,1,1,1,0,0},
                    {0,0,0,0,1,1,1,0,0},
                    {0,0,0,1,1,1,1,1,0}};
        Field newField = new Field(test);
       // newField.writeImage("muster");

      // meinFeld.outputToText("dwqd");
                
      //  System.out.println(meinFeld.countNumbers());
      // System.out.println(meinFeld.getHighestNumber());
        
  


    }

    static LinkedList<Punkt> getPointsBetween(int x1, int y1, int x2, int y2) {
        LinkedList<Punkt> alleKoordinate = new LinkedList<Punkt>();
        if (x1 == x2) {
            int betrag = (int) Math.sqrt((double) (Math.pow(y2 - y1, 2)));
            int smallerNumber = Math.min(y2, y1);
            for (int i = 0; i < betrag + 1; i++) {
                alleKoordinate.add(new Punkt(x1, smallerNumber + i));
            }
        }
        if (y1 == y2) {
            int betrag = (int) Math.sqrt((double) (Math.pow(x2 - x1, 2)));
            int smallerNumber = Math.min(x2, x1);
            for (int i = 0; i < betrag + 1; i++) {
                alleKoordinate.add(new Punkt(smallerNumber + i, y1));
            }
        }
        if (x2 != x1 && y2 != y1) {
            int betrag = (int) Math.sqrt((double) (Math.pow(x2 - x1, 2)));

            if (y1 == Math.min(y2, y1)) {

                if (x1 == Math.min(x2, x1)) {
                    for (int i = 0; i < betrag + 1; i++) {
                        alleKoordinate.add(new Punkt(x1 + i, y1 + i));
                    }
                } else if (x2 == Math.min(x1, x2)) {
                    for (int i = 0; i < betrag + 1; i++) {
                        alleKoordinate.add(new Punkt(x1 - i, y1 + i));
                    }
                }

            }
            if (y2 == Math.min(y2, y1)) {

                if (x1 == Math.min(x2, x1)) {
                    for (int i = 0; i < betrag + 1; i++) {
                        alleKoordinate.add(new Punkt(x1 + i, y1 - i));
                    }
                } else if (x2 == Math.min(x1, x2)) {
                    for (int i = 0; i < betrag + 1; i++) {
                        alleKoordinate.add(new Punkt(x1 - i, y1 - i));
                    }
                }

            }

        }

        return alleKoordinate;

    }

}

class Punkt {
    private int x;
    private int y;

    Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}

class Field implements Comparator<Integer> {

    Integer[][] field;
    
    Field(Integer[][] field){
        this.field = field;
    }

    Field(int x, int y) {
        this.field = new Integer[x][y];
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                field[i][j] = 0;
            }
        }
    }

    public void increasePixel(int x, int y) {
        field[y][x]++;
    }

    public void printField() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                System.out.printf("%d ", this.field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

  /*  public int getHighestNumber(){
        IntStream stream = Arrays.stream(this.field).flatMapToInt(x -> Arrays.stream(x));
        return stream.max().getAsInt();
    } */

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? o1 : o2;	
    }
    
    




    public int countNumbers() {
        int counter = 0;
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[i].length; j++) {
                if (this.field[i][j] >= 2) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void outputToText(String Name) throws Exception {
        Integer[][] firstResizedInt = doOperation(this.field);
        String path = "D:\\Coding\\Java\\Übung\\" + Name + ".txt";
        PrintWriter writeToTxt = new PrintWriter(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        for(int i = 0; i < firstResizedInt.length; i++) {
            for(int j = 0; j < firstResizedInt.length; j++){
                String currentLine =  Integer.toString(firstResizedInt[i][j]);
                writer.append(currentLine);
            }
        }
    }

    public void writeImage(String Name) throws Exception {

        Integer[][] firstResizedInt = doOperation(this.field);

        String path = "D:\\Coding\\Java\\Übung\\" + Name + ".png";
        BufferedImage image = new BufferedImage(firstResizedInt.length, firstResizedInt.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < firstResizedInt .length; i++) {
            for (int j = 0; j < firstResizedInt [i].length; j++) {
               // pixelCount++;
              // System.out.println("Schreibe Pixel: " +pixelCount);
               // Color c = new Color(40 * firstResizedInt [i][j]+50, 40 * firstResizedInt [i][j], 40 * firstResizedInt [i][j]+50);
            //    int rgb = c.getRGB();
                image.setRGB(i, j , getColor(firstResizedInt[i][j]));

            }
        }

        File ImageFile = new File(path);
        try {
            // ImageIO.write(image, "png", ImageFile);
            ImageIO.write(image, "png", ImageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getColor(int valueAtPixel){
        Color c;
        switch(valueAtPixel){
            case 0: c = Color.BLACK; 
            break;
            case 1: c = Color.RED;
            break;
            case 2: c = Color.GREEN;
            break;
            case 3: c = Color.BLUE;
            break;
            case 4: c = Color.ORANGE;
            break;
            case 5: c = Color.YELLOW;
            break;
            default: c = Color.WHITE;
            break;
        }
        return c.getRGB();
    }

    public static Integer[][] doOperation(Integer[][] imageArray) {
        int multiplier = 32;
        Integer[][] newArray = new Integer[imageArray.length*multiplier][imageArray[0].length*multiplier];
    
        for(int i = 0; i < newArray.length; i++)
            for(int j = 0; j < newArray[0].length; j++) {
                newArray[i][j] = imageArray[i/multiplier][j/multiplier];
            }
        return newArray;
    }

    

   

}

class StringInputReader {

    static File input = new File("D:\\Coding\\CalenderCode\\inputday5.txt");

    static LinkedList<String[]> returnCommands() throws Exception {
        LinkedList<String[]> commands = new LinkedList<String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] validLine = line.replaceAll("\\s+", "").split("->");
                if (compareCoordinates(validLine)) {
                    commands.add(validLine);
                }

            }
        }
        return commands;
    }

    public static boolean compareCoordinates(String[] pairOf_xy) {
        int x1 = Integer.parseInt(pairOf_xy[0].split(",")[0]);
        int y1 = Integer.parseInt(pairOf_xy[0].split(",")[1]);
        int x2 = Integer.parseInt(pairOf_xy[1].split(",")[0]);
        int y2 = Integer.parseInt(pairOf_xy[1].split(",")[1]);
        return x1 == x2 || y1 == y2 || x2 > x1 || x2 < x1;
    }

}
