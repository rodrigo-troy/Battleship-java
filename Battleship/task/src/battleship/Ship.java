package battleship;

import java.util.Arrays;

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
    private final String[] positionUsed;

    public Ship(String name,
                int size) {
        this.name = name;
        this.size = size;
        this.positionUsed = new String[size];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String[] getPositionUsed() {
        return positionUsed;
    }

    @Override
    public String toString() {
        return "Ship{" +
               "size=" + size +
               ", positionUsed=" + Arrays.toString(positionUsed) +
               '}';
    }
}
