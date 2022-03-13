package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 11-03-22
 * Time: 18:44
 */
public class Board {
    public static final String ROWS = "ABCDEFGHIJ";
    public static final int[] COLUMNS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final char[][] table = new char[10][10];

    public Board() {
        this.prepareBoard();
    }

    public boolean isValidCoord(String coord) {
        String[] coordArray = coord.split("");

        if (coordArray.length < 2 || coordArray.length > 3) {
            return false;
        }

        if (coordArray.length == 2) {
            if (!Board.ROWS.contains(coordArray[0]) ||
                Arrays.stream(Board.COLUMNS).noneMatch(v -> v == Integer.parseInt(coordArray[1]))) {
                return false;
            }
        }

        if (coordArray.length == 3) {
            if (!Board.ROWS.contains(coordArray[0]) ||
                Arrays.stream(Board.COLUMNS).noneMatch(v -> v == Integer.parseInt(coordArray[1])) ||
                !coordArray[2].equals("0")) {
                return false;
            }
        }

        return true;
    }

    public boolean isShipLengthRight(String coord1,
                                     String coord2,
                                     Ship ship) {

        return false;
    }

    public boolean isSpaceAvailable(String coord1,
                                    String coord2,
                                    Ship ship) {
        if (!isValidCoord(coord1) || !isValidCoord(coord2)) {
            return false;
        }

        String[] coordArray1 = coord1.split("");
        String[] coordArray2 = coord2.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());

        int row1 = Board.ROWS.indexOf(coordArray1[0]);
        int col1 = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 10;

        int row2 = Board.ROWS.indexOf(coordArray2[0]);
        int col2 = coordArray2.length == 2 ? columns.indexOf(Integer.parseInt(coordArray2[1])) : 10;

        if (row1 != row2 && col1 == col2) {
            if (row1 < row2) {
                for (int i = row1; i < row2; i++) {
                    if (table[i][col1] != '~') {
                        return false;
                    }
                }
            }

            if (row1 > row2) {
                for (int i = row2; i < row1; i++) {
                    if (table[i][col1] != '~') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void addShip(String c1,
                        String c2) {
        String[] c1Arr = c1.split("");
        String[] c2Arr = c2.split("");
    }

    private void prepareBoard() {
        for (char[] chars : table) {
            Arrays.fill(chars,
                        '~');
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3 4 5 6 7 8 9 10\n");

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                char c = table[row][column];

                if (column == 0) {
                    sb.append(ROWS.charAt(row));
                    sb.append(" ");
                    sb.append(c);
                    sb.append(" ");
                } else {
                    sb.append(c);
                    sb.append(" ");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
