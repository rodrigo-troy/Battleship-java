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
    private List<Position> positionsUsed;

    public Ship(String name,
                int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<Position> getPositionsUsed() {
        if (positionsUsed == null) {
            positionsUsed = new ArrayList<>();
        }

        return positionsUsed;
    }

    @Override
    public String toString() {
        return "Ship{" +
               "size=" + size +
               ", positionUsed=" + positionsUsed +
               '}';
    }
}
