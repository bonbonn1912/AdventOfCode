package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Part1 {

    public static int horizontalPosition = 0;
    public static int depth = 0;

    public static void main(String[] args) throws Exception {

        File input = new File("PATH TO INPUT TXT FILE");
        List<String> inputCommands = new LinkedList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {

                inputCommands.add(line);

            }
        }

        for (String command : inputCommands) {

            switch (command.length()) {

                case 4:
                    changePosition("UP", Integer.parseInt(command.substring(command.length() - 1)));
                    break;
                case 6:
                    changePosition("DOWN", Integer.parseInt(command.substring(command.length() - 1)));
                    break;
                case 9:
                    changePosition("FORWARD", Integer.parseInt(command.substring(command.length() - 1)));
                    break;
                default:

            }
        }
        System.out.println(depth * horizontalPosition);

    }

    public static void changePosition(String command, int multiplier) {
        switch (command) {
            case "UP":
                depth = depth - multiplier;
                break;
            case "DOWN":
                depth = depth + multiplier;
                break;
            case "FORWARD":
                horizontalPosition = horizontalPosition + multiplier;
        }

    }

}
