import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws Exception {
        LinkedList<Integer> Lowpoints = new LinkedList<Integer>();

        int[][] map = InputReaderDay09.getMap();
        Field feld = new Field(map);
        int[][] mixedField = feld.returnMixedField(map);
        for (int i = 1; i < mixedField.length - 1; i++) {
            for (int j = 1; j < mixedField[i].length - 1; j++) {
                if (checkLowPoint(mixedField[i][j], mixedField[i - 1][j], mixedField[i][j - 1], mixedField[i + 1][j],
                        mixedField[i][j + 1])) {
                    Lowpoints.add(mixedField[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();

        int risklevel = 0;
        for (int lowpoint : Lowpoints) {
            lowpoint += 1;
            risklevel += lowpoint;
        }
        System.out.println(risklevel);
    }

    public static boolean checkLowPoint(int currentValue, int neigh1, int neigh2, int neigh3, int neigh4) {
        if (currentValue < neigh1 && currentValue < neigh2 && currentValue < neigh3 && currentValue < neigh4) {
            return true;
        }
        return false;
    }

}

class Field {

    int[][] bigField;

    Field(int[][] smallField) {
        bigField = new int[smallField.length + 2][smallField[0].length + 2];
        for (int i = 0; i < bigField.length; i++) {
            for (int j = 0; j < bigField[i].length; j++) {
                bigField[i][j] = 100;
            }
        }
    }

    public int[][] returnMixedField(int[][] smallField) {
        for (int i = 0; i < smallField.length; i++) {
            for (int j = 0; j < smallField[i].length; j++) {
                bigField[i + 1][j + 1] = smallField[i][j];
            }
        }

        return this.bigField;
    }

}

class InputReaderDay09 {

    public static int[][] getMap() throws Exception {
        int[][] map = Files.lines(Path.of("PATH TO INPUT TXT FILE"))
                .map(line -> line.codePoints().map(Character::getNumericValue).toArray())
                .toArray(int[][]::new);
        return map;
    }

}
