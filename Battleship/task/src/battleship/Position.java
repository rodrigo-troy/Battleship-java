package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 22-03-22
 * Time: 13:33
 */
public class Position {
    private Integer row;
    private Integer column;

    public Position(String coord) {
        this.processCoord(coord);
    }

    public Position(Integer row,
                    Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public boolean isSameRow(Position position) {
        return position.getRow().equals(this.getRow());
    }

    public boolean isSameColumn(Position position) {
        return position.getColumn().equals(this.getColumn());
    }

    private void processCoord(String coord) {
        String[] coordArray1 = coord.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());
        this.row = Board.ROWS.indexOf(coordArray1[0]);
        this.column = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 9;
    }

    @Override
    public String toString() {
        return "Pos{" +
               ", r=" + row +
               ", c=" + column +
               '}';
    }
}
