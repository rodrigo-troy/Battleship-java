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

    public boolean hitShip(String coord) {
        Position position = new Position(coord);

        if (table[position.getRow()][position.getColumn()] == 'O') {
            table[position.getRow()][position.getColumn()] = 'X';
            return true;
        } else {
            table[position.getRow()][position.getColumn()] = 'M';
            return false;
        }
    }

    public boolean isInvalidCoord(String coord) {
        String[] coordArray = coord.split("");

        if (coordArray.length < 2 || coordArray.length > 3) {
            return true;
        }

        if (coordArray.length == 2) {
            if (!Board.ROWS.contains(coordArray[0]) ||
                Arrays.stream(Board.COLUMNS).noneMatch(v -> v == Integer.parseInt(coordArray[1]))) {
                return true;
            }
        }

        if (coordArray.length == 3) {
            if (!Board.ROWS.contains(coordArray[0]) ||
                Arrays.stream(Board.COLUMNS).noneMatch(v -> v == Integer.parseInt(coordArray[1])) ||
                !coordArray[2].equals("0")) {
                return true;
            }
        }

        return false;
    }

    public boolean isShipLengthRight(String coord1,
                                     String coord2,
                                     Ship ship) {
        Position position1 = new Position(coord1);
        Position position2 = new Position(coord2);

        if (!position1.getRow().equals(position2.getRow()) && position1.getColumn().equals(position2.getColumn())) {
            return Math.abs(position1.getRow() - position2.getRow()) + 1 == ship.getSize();
        }

        if (position1.getRow().equals(position2.getRow()) && !position1.getColumn().equals(position2.getColumn())) {
            return Math.abs(position1.getColumn() - position2.getColumn()) + 1 == ship.getSize();
        }

        return false;
    }

    public boolean isSpaceAvailable(String coord1,
                                    String coord2) {
        String[] coordArray1 = coord1.split("");
        String[] coordArray2 = coord2.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());

        int row1 = Board.ROWS.indexOf(coordArray1[0]);
        int col1 = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 9;

        int row2 = Board.ROWS.indexOf(coordArray2[0]);
        int col2 = coordArray2.length == 2 ? columns.indexOf(Integer.parseInt(coordArray2[1])) : 9;

        if (row1 != row2 && col1 == col2) {
            if (row1 < row2) {
                for (int i = row1; i <= row2; i++) {
                    if (table[i][col1] != '~') {
                        return false;
                    }
                }
            }

            if (row1 > row2) {
                for (int i = row2; i <= row1; i++) {
                    if (table[i][col1] != '~') {
                        return false;
                    }
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                for (int i = col1; i <= col2; i++) {
                    if (table[row1][i] != '~') {
                        return false;
                    }
                }
            }

            if (col1 > col2) {
                for (int i = col2; i <= col1; i++) {
                    if (table[row1][i] != '~') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void addShip(String c1,
                        String c2) {
        String[] coordArray1 = c1.split("");
        String[] coordArray2 = c2.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());

        int row1 = Board.ROWS.indexOf(coordArray1[0]);
        int col1 = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 9;

        int row2 = Board.ROWS.indexOf(coordArray2[0]);
        int col2 = coordArray2.length == 2 ? columns.indexOf(Integer.parseInt(coordArray2[1])) : 9;

        if (row1 != row2 && col1 == col2) {
            if (row1 < row2) {
                for (int i = row1; i <= row2; i++) {
                    table[i][col1] = 'O';
                }
            }

            if (row1 > row2) {
                for (int i = row2; i <= row1; i++) {
                    table[i][col1] = 'O';
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                for (int i = col1; i <= col2; i++) {
                    table[row1][i] = 'O';
                }
            }

            if (col1 > col2) {
                for (int i = col2; i <= col1; i++) {
                    table[row1][i] = 'O';
                }
            }
        }
    }

    private void prepareBoard() {
        for (char[] chars : table) {
            Arrays.fill(chars,
                        '~');
        }
    }

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                char c = table[row][column];

                if (column == 0) {
                    System.out.printf("%s %s ",
                                      ROWS.charAt(row),
                                      c);
                } else {
                    System.out.printf("%s ",
                                      c);
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    public boolean isTooCloseToAnotherShip(String c1,
                                           String c2) {
        String[] coordArray1 = c1.split("");
        String[] coordArray2 = c2.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());

        int row1 = Board.ROWS.indexOf(coordArray1[0]);
        int col1 = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 9;

        int row2 = Board.ROWS.indexOf(coordArray2[0]);
        int col2 = coordArray2.length == 2 ? columns.indexOf(Integer.parseInt(coordArray2[1])) : 9;

        if (row1 != row2 && col1 == col2) {
            if (row1 < row2) {
                if (row1 - 1 >= 0) {
                    if (table[row1 - 1][col1] != '~') {
                        return true;
                    }
                }

                if (row2 + 1 < 10) {
                    if (table[row2 + 1][col1] != '~') {
                        return true;
                    }
                }

                if (col1 - 1 >= 0) {
                    for (int i = row1; i <= row2; i++) {
                        if (table[i][col1 - 1] != '~') {
                            return true;
                        }
                    }
                }

                if (col1 + 1 < 10) {
                    for (int i = row1; i <= row2; i++) {
                        if (table[i][col1 + 1] != '~') {
                            return true;
                        }
                    }
                }
            }

            if (row1 > row2) {
                if (row2 - 1 >= 0) {
                    if (table[row2 - 1][col1] != '~') {
                        return true;
                    }
                }

                if (row1 + 1 < 10) {
                    if (table[row1 + 1][col1] != '~') {
                        return true;
                    }
                }

                if (col1 - 1 >= 0) {
                    for (int i = row2; i <= row1; i++) {
                        if (table[i][col1 - 1] != '~') {
                            return true;
                        }
                    }
                }

                if (col1 + 1 < 10) {
                    for (int i = row2; i <= row1; i++) {
                        if (table[i][col1 + 1] != '~') {
                            return true;
                        }
                    }
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                if (col1 - 1 >= 0 && table[row1][col1 - 1] != '~') {
                    return true;
                }

                if (col2 + 1 < 10 && table[row1][col2 + 1] != '~') {
                    return true;
                }

                if (row1 - 1 >= 0) {
                    for (int i = col1; i <= col2; i++) {
                        if (table[row1 - 1][i] != '~') {
                            return true;
                        }
                    }
                }

                if (row1 + 1 < 10) {
                    for (int i = col1; i <= col2; i++) {
                        if (table[row1 + 1][i] != '~') {
                            return true;
                        }
                    }
                }
            }

            if (col1 > col2) {
                if (col2 - 1 >= 0 && table[row1][col2 - 1] != '~') {
                    return true;
                }

                if (col1 + 1 < 10 && table[row1][col1 + 1] != '~') {
                    return true;
                }

                if (row1 - 1 >= 0) {
                    for (int i = col2; i <= col1; i++) {
                        if (table[row1 - 1][i] != '~') {
                            return true;
                        }
                    }
                }

                if (row1 + 1 < 10) {
                    for (int i = col2; i <= col1; i++) {
                        if (table[row1 + 1][i] != '~') {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
