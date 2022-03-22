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
    private final Cell[][] table = new Cell[10][10];

    public Board() {
        this.prepareBoard();
    }

    public boolean hitShip(String coord) {
        Cell Cell = new Cell(coord);

        if (table[Cell.getRow()][Cell.getColumn()].getStatus().equals(CellStatus.USED)) {
            table[Cell.getRow()][Cell.getColumn()].setStatus(CellStatus.HIT);
            return true;
        } else {
            table[Cell.getRow()][Cell.getColumn()].setStatus(CellStatus.MISSED);
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
            return !Board.ROWS.contains(coordArray[0]) ||
                   Arrays.stream(Board.COLUMNS).noneMatch(v -> v == Integer.parseInt(coordArray[1])) ||
                   !coordArray[2].equals("0");
        }

        return false;
    }

    public boolean isShipLengthWrong(String coord1,
                                     String coord2,
                                     Ship ship) {
        Cell Cell1 = new Cell(coord1);
        Cell Cell2 = new Cell(coord2);

        if (!Cell1.getRow().equals(Cell2.getRow()) && Cell1.getColumn().equals(Cell2.getColumn())) {
            return Math.abs(Cell1.getRow() - Cell2.getRow()) + 1 != ship.getSize();
        }

        if (Cell1.getRow().equals(Cell2.getRow()) && !Cell1.getColumn().equals(Cell2.getColumn())) {
            return Math.abs(Cell1.getColumn() - Cell2.getColumn()) + 1 != ship.getSize();
        }

        return true;
    }

    public boolean isSpaceUnavailable(String coord1,
                                      String coord2) {
        Cell Cell1 = new Cell(coord1);
        Cell Cell2 = new Cell(coord2);

        int row1 = Cell1.getRow();
        int col1 = Cell1.getColumn();

        int row2 = Cell2.getRow();
        int col2 = Cell2.getColumn();

        if (row1 != row2 && col1 == col2) {
            if (row1 < row2) {
                for (int i = row1; i <= row2; i++) {
                    if (!table[i][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }
            }

            if (row1 > row2) {
                for (int i = row2; i <= row1; i++) {
                    if (!table[i][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                for (int i = col1; i <= col2; i++) {
                    if (!table[row1][i].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }
            }

            if (col1 > col2) {
                for (int i = col2; i <= col1; i++) {
                    if (!table[row1][i].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void addShip(String coord1,
                        String coord2,
                        Ship ship) {
        Cell Cell1 = new Cell(coord1);
        Cell Cell2 = new Cell(coord2);

        int row1 = Cell1.getRow();
        int col1 = Cell1.getColumn();

        int row2 = Cell2.getRow();
        int col2 = Cell2.getColumn();

        if (!Cell1.isSameRow(Cell2) &&
            Cell1.isSameColumn(Cell2)) {
            if (row1 < row2) {
                for (int i = row1; i <= row2; i++) {
                    table[i][col1].setStatus(CellStatus.USED);
                    ship.addCell(table[i][col1]);
                }
            }

            if (row1 > row2) {
                for (int i = row2; i <= row1; i++) {
                    table[i][col1].setStatus(CellStatus.USED);
                    ship.addCell(table[i][col1]);
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                for (int i = col1; i <= col2; i++) {
                    table[row1][i].setStatus(CellStatus.USED);
                    ship.addCell(table[row1][i]);
                }
            }

            if (col1 > col2) {
                for (int i = col2; i <= col1; i++) {
                    table[row1][i].setStatus(CellStatus.USED);
                    ship.addCell(table[row1][i]);
                }
            }
        }
    }

    private void prepareBoard() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = new Cell(row,
                                           col);
            }
        }
    }

    public void print(boolean hideShips) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                char c = table[row][column].getStatus().getValue();
                c = hideShips && c == 'O' ? '~' : c;

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
                    if (!table[row1 - 1][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }

                if (row2 + 1 < 10) {
                    if (!table[row2 + 1][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }

                if (col1 - 1 >= 0) {
                    for (int i = row1; i <= row2; i++) {
                        if (!table[i][col1 - 1].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }

                if (col1 + 1 < 10) {
                    for (int i = row1; i <= row2; i++) {
                        if (!table[i][col1 + 1].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }
            }

            if (row1 > row2) {
                if (row2 - 1 >= 0) {
                    if (!table[row2 - 1][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }

                if (row1 + 1 < 10) {
                    if (!table[row1 + 1][col1].getStatus().equals(CellStatus.EMPTY)) {
                        return true;
                    }
                }

                if (col1 - 1 >= 0) {
                    for (int i = row2; i <= row1; i++) {
                        if (!table[i][col1 - 1].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }

                if (col1 + 1 < 10) {
                    for (int i = row2; i <= row1; i++) {
                        if (!table[i][col1 + 1].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }
            }
        }

        if (row1 == row2 && col1 != col2) {
            if (col1 < col2) {
                if (col1 - 1 >= 0 && !table[row1][col1 - 1].getStatus().equals(CellStatus.EMPTY)) {
                    return true;
                }

                if (col2 + 1 < 10 && !table[row1][col2 + 1].getStatus().equals(CellStatus.EMPTY)) {
                    return true;
                }

                if (row1 - 1 >= 0) {
                    for (int i = col1; i <= col2; i++) {
                        if (!table[row1 - 1][i].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }

                if (row1 + 1 < 10) {
                    for (int i = col1; i <= col2; i++) {
                        if (!table[row1 + 1][i].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }
            }

            if (col1 > col2) {
                if (col2 - 1 >= 0 && !table[row1][col2 - 1].getStatus().equals(CellStatus.EMPTY)) {
                    return true;
                }

                if (col1 + 1 < 10 && !table[row1][col1 + 1].getStatus().equals(CellStatus.EMPTY)) {
                    return true;
                }

                if (row1 - 1 >= 0) {
                    for (int i = col2; i <= col1; i++) {
                        if (!table[row1 - 1][i].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }

                if (row1 + 1 < 10) {
                    for (int i = col2; i <= col1; i++) {
                        if (!table[row1 + 1][i].getStatus().equals(CellStatus.EMPTY)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
