import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Part1 {

    static int[][] field = new int[1000][1000];

    public static void main(String[] args) throws Exception {
        File input = new File("INPUT TO TEXT FILE");
        List<String> numbers = new LinkedList<String>();
        fillField();
        try (
                BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] validLine = line.replaceAll("\\s+", "").split("->");
                if (compareCoordinates(validLine)) {
                    addLineToCommands(validLine);
                }
                ;
            }
        }
        System.out.println(countNumberTwos());
    }

    public static int countNumberTwos() {
        int count = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void fillField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = 0;
            }
        }
    }

    public static void addLineToCommands(String[] validLine) {
        int x1 = Integer.parseInt(validLine[0].split(",")[0]);
        int y1 = Integer.parseInt(validLine[0].split(",")[1]);
        int x2 = Integer.parseInt(validLine[1].split(",")[0]);
        int y2 = Integer.parseInt(validLine[1].split(",")[1]);

        if (x1 == x2) {
            if (y2 > y1) {
                drawLine(y2 - y1, x1, y1, true);
            } else {
                drawLine(y1 - y2, x2, y2, true);
            }

        } else if (y1 == y2) {
            if (x2 > x1) {
                drawLine(x2 - x1, y1, x1, false);
            } else {
                drawLine(x1 - x2, y2, x2, false);
            }
        }
    }

    public static void drawLine(int delta, int y_startCoordinate, int x_startCoordinate, boolean isVertikal) {
        double betrag = Math.sqrt((double) (Math.pow(delta, 2)));
        for (int i = 0; i < betrag + 1; i++) {
            if (isVertikal) {
                System.out.println("Vertikale Linie");
                field[i + x_startCoordinate][y_startCoordinate]++;
            } else {
                System.out.println("Horizontale Linie");
                field[y_startCoordinate][i + x_startCoordinate]++;
            }
        }
    }

    public static boolean compareCoordinates(String[] pairOf_xy) {
        int x1 = Integer.parseInt(pairOf_xy[0].split(",")[0]);
        int y1 = Integer.parseInt(pairOf_xy[0].split(",")[1]);
        int x2 = Integer.parseInt(pairOf_xy[1].split(",")[0]);
        int y2 = Integer.parseInt(pairOf_xy[1].split(",")[1]);
        return x1 == x2 || y1 == y2;
    }
}
