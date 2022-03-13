package battleship;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 12-03-22
 * Time: 15:42
 */
public class Battleship {
    private final Board board;

    public Battleship() {
        this.board = new Board();
    }


    public void play() {
        System.out.println(this.board.print());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the coordinates of the Aircraft Carrier (5 cells):");
        Ship aircraftCarrier = new Ship(5);

        while (true) {
            String line = scanner.nextLine();

            String[] c = line.split(" ");

            if (c.length != 2) {
                System.out.println("Error! Bad parameters! Try again:");
                continue;
            }

            if (!board.isValidCoord(c[0]) ||
                !board.isValidCoord(c[1])) {
                System.out.println("Error! Bad parameters! Try again:");
                continue;
            }

            if (!board.isShipLengthRight(c[0],
                                         c[1],
                                         aircraftCarrier)) {
                System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                continue;
            }

            if (!board.isSpaceAvailable(c[0],
                                        c[1],
                                        aircraftCarrier)) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
        }

    }
}
