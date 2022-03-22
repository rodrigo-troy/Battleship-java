package battleship;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 22-03-22
 * Time: 14:45
 */
public enum CellStatus {
    HIT('X'),
    USED('O'),
    EMPTY('~'),
    MISSED('M');

    private final char value;

    CellStatus(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Status{" +
               "value=" + value +
               '}';
    }
}
