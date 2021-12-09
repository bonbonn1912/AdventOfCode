import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static void main(String args[]) {
        String s = "";
        File inputFile = new File("PATH TO INPUT TXT FILE");
        Scanner scan;
        List<List<Field>> graph = new LinkedList<List<Field>>();
        List<Field> row = new LinkedList<Field>();
        int y = 0;
        try {
            scan = new Scanner(inputFile);
            while (scan.hasNext()) {
                s = String.valueOf(scan.nextLine());
                for (int x = 0; x < s.length(); x++) {
                    row.add(new Field(x, y, s.charAt(x)));
                }
                graph.add(row);
                row = new LinkedList<Field>();
                y++;
            }
            System.out.println(part2(graph));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int part1(List<List<Field>> graph) {
        int sum = 0;
        for (List<Field> row : graph) {
            for (Field s : row) {
                sum += s.getLowPoints(graph);
            }
        }
        return sum;
    }

    public static int part2(List<List<Field>> graph) {
        List<Integer> basinsSum = new LinkedList<Integer>();
        List<Field> lowPoints = new LinkedList<Field>();
        for (List<Field> row : graph) {
            for (Field s : row) {
                if (s.getLowPoints(graph) == 0)
                    lowPoints.add(s);
            }
        }
        int num = 0;
        for (Field s : lowPoints) {// gets all the basins size
            num = s.getBasin(s.getX(), s.getY(), graph);
            basinsSum.add(num);
        }
        Collections.sort(basinsSum, Collections.reverseOrder());
        return basinsSum.get(0) * basinsSum.get(1) * basinsSum.get(2);
    }
}

class Field {
    private int x;
    int  y;
    int value;

    Field(int x1, int y1, char val) {
        this.x = x1;
        this.y = y1;
        this.value = Integer.parseInt(String.valueOf(val));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public int getLowPoints(List<List<Field>> graph) {
        if (this.value == 9)
            return 0;
        int counter = 0;
        if (this.y == 0 || this.value < graph.get(this.y - 1).get(this.x).getValue())
            counter++;

        if (this.y == graph.size() - 1 || this.value < graph.get(this.y + 1).get(this.x).getValue())
            counter++;

        if (this.x == 0 || this.value < graph.get(this.y).get(this.x - 1).getValue())
            counter++;
        if (this.x >= graph.get(this.y).size() - 1 || this.value < graph.get(this.y).get(this.x + 1).getValue())
            counter++;
        if (counter == 4)
            return this.value + 1;
        return 0;
    }

    int getBasin(int row, int col, List<List<Field>> graph) {
        if (col < 0 || row < 0 || col >= graph.get(0).size() || row >= graph.size()
                || graph.get(row).get(col).getValue() == 9)
            return 0;
        graph.get(row).get(col).setValue(9);
        return 1 + getBasin(row + 1, col, graph) + getBasin(row - 1, col, graph) + getBasin(row, col - 1, graph)
                + getBasin(row, col + 1, graph);
    }

}