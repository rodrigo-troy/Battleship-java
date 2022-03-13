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

        Ship aircraftCarrier = new Ship(5);
        Ship battleship = new Ship(4);
        Ship submarine = new Ship(3);
        Ship cruiser = new Ship(3);
        Ship destroyer = new Ship(2);


        System.out.print("Enter the coordinates of the Aircraft Carrier (5 cells):");
        while (true) {
            String line = scanner.nextLine();

            String[] c = line.split(" ");

            if (c.length != 2) {
                System.out.println("Error! Bad parameters! Try again:");
                continue;
            }

            if (board.isInvalidCoord(c[0]) ||
                board.isInvalidCoord(c[1])) {
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
                                        c[1])) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            if (board.isTooCloseToAnotherShip(c[0],
                                              c[1])) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }

            board.addShip(c[0],
                          c[1],
                          aircraftCarrier);
        }

    }
}
