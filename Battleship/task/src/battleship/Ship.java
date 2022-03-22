package battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 12-03-22
 * Time: 20:57
 */
public class Ship {
    private final int size;
    private final String name;
    private List<Cell> CellsUsed;

    public Ship(String name,
                int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public boolean isHit() {
        for (Cell cell : this.getCellsUsed()) {
            if (cell.getStatus().equals(CellStatus.HIT)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSank() {
        for (Cell cell : this.getCellsUsed()) {
            if (!cell.getStatus().equals(CellStatus.HIT)) {
                return false;
            }
        }

        return true;
    }

    public int getSize() {
        return size;
    }

    public void addCell(Cell Cell) {
        this.getCellsUsed().add(Cell);
    }

    private List<Cell> getCellsUsed() {
        if (CellsUsed == null) {
            CellsUsed = new ArrayList<>();
        }

        return CellsUsed;
    }

    @Override
    public String toString() {
        return "Ship{" +
               "size=" + size +
               ", CellUsed=" + CellsUsed +
               '}';
    }
}
