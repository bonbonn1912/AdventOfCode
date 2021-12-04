import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//package Day04;

public class Part2 {

    static Integer[][] lastBrett = new Integer[5][5];
    public static String currCom = "";

    public static void main(String[] args) throws Exception {

        List<String> drawnNumbers = new LinkedList<String>();
        File input = new File("PATH TO INPUT TXT FILE");
        File output = new File("PATH TO OUTPUT TXT FILE");
        List<String> binarys = new LinkedList<String>();
        FileWriter writer = new FileWriter(output);

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int linecount = 0;
            int partialCount = 0;

            while ((line = br.readLine()) != null) {
                if (linecount == 0) {
                    drawnNumbers = Arrays.asList(line.split(","));
                    linecount++;
                } else if (line.isEmpty()) {
                    // Do nothing
                } else {
                    writer.write(line.trim().replaceAll("\\s+", " ") + "\n");
                }
            }
            writer.close();
        }

        Scanner sc = new Scanner(new BufferedReader(new FileReader(output)));
        int rows = 5;
        int columns = 5;
        LinkedList<Integer[][]> board = new LinkedList<Integer[][]>();
        int maxCounter = 0;
        while (sc.hasNextLine()) {
            Integer[][] myArray = new Integer[rows][columns];
            if (maxCounter < 25) {
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
                maxCounter++;
            }
            board.add(myArray);
            maxCounter = 0;
        }
        int anlauf = 1;
        int lastnumber = 0;
        LinkedList<String> NewdrawnNumbers = new LinkedList<String>();

        for (String command : drawnNumbers) {
            NewdrawnNumbers.add(command);
        }
        while (board.size() > 1) {
            lastnumber = checkLastWinner(board, drawnNumbers);
            anlauf++;
        }
        System.out.println(board.size());

        for (Integer[][] brett : board) {
            for (int i = 0; i < brett.length; i++) {
                for (int j = 0; j < brett[i].length; j++) {
                    System.out.printf("%d ", brett[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        int last = drawnNumbers.indexOf(currCom) + 1;
        String lastCommand = drawnNumbers.get(last);
        int FinalNumber = 0;
        Integer[][] exitBrett = new Integer[5][5];
        for (Integer[][] brett : board) {
            exitBrett = handleLastWinner(brett, lastCommand);
            System.out.println(calculateRest(Integer.parseInt(lastCommand), exitBrett) * Integer.parseInt(lastCommand));
        }

    }

    public static Integer[][] handleLastWinner(Integer[][] brett, String Command) {
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett[i].length; j++) {
                if (!checkWinningCondition(brett)) {
                    if (brett[i][j] == Integer.parseInt(Command)) {
                        brett[i][j] = 1000;
                        return brett;
                    }
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public static int checkLastWinner(LinkedList<Integer[][]> board, List<String> drawnNumbers) {
        Integer[][] winningBoard = new Integer[5][5];
        for (String Command : drawnNumbers) {
            for (Integer[][] brett : board) {
                for (int i = 0; i < brett.length; i++) {
                    for (int j = 0; j < brett[i].length; j++) {
                        if (brett[i][j] == Integer.parseInt(Command)) {
                            currCom = Command;
                            brett[i][j] = 1000;
                            if (checkWinningCondition(brett)) {
                                deleteBrettFromList(brett, board, Command);
                                return Integer.parseInt(Command);
                            } else {
                                return Integer.parseInt(Command);
                            }

                        }
                    }

                }
            }
        }
        return 0;
    }

    public static void deleteBrettFromList(Integer[][] brett, LinkedList<Integer[][]> board, String Command) {
        lastBrett = brett;
        board.remove(brett);
    }

    public static boolean checkWinningCondition(Integer[][] brett) {
        int counter = 0;
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett[i].length; j++) {
                if (brett[i][j] == 1000) {
                    counter++;
                    if (counter == 5) {
                        return true;
                    }
                }
            }
            counter = 0;
        }
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett[i].length; j++) {
                if (brett[j][i] == 1000) {
                    counter++;
                    if (counter == 5) {
                        return true;
                    }
                }
            }
            counter = 0;
        }
        return false;
    }

    public static int calculateRest(int command, Integer[][] brett) {
        int summ = 0;
        for (int i = 0; i < brett.length; i++) {
            for (int j = 0; j < brett[i].length; j++) {
                if (brett[i][j] != 1000) {
                    summ += brett[i][j];
                }
            }
        }
        return summ;
    }
}
