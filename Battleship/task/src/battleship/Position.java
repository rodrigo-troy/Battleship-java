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
    private final String coord;
    private Integer row;
    private Integer column;

    public Position(String coord) {
        this.coord = coord;
    }

    public Integer getRow() {
        if (row == null) {
            this.processCoord();
        }

        return row;
    }

    public Integer getColumn() {
        if (column == null) {
            this.processCoord();
        }

        return column;
    }

    private void processCoord() {
        String[] coordArray1 = this.coord.split("");

        List<Integer> columns = Arrays.stream(Board.COLUMNS).boxed().collect(Collectors.toList());
        this.row = Board.ROWS.indexOf(coordArray1[0]);
        this.column = coordArray1.length == 2 ? columns.indexOf(Integer.parseInt(coordArray1[1])) : 9;
    }
}
