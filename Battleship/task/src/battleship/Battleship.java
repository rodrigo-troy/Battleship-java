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

    private void addShip(Ship ship) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n",
                          ship.getName(),
                          ship.getSize());

        Scanner scanner = new Scanner(System.in);

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

            if (board.isShipLengthWrong(c[0],
                                        c[1],
                                        ship)) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n",
                                  ship.getName());
                continue;
            }

            if (board.isSpaceUnavailable(c[0],
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
                          ship);

            break;
        }

        this.board.print(false);
    }

    public void play() {
        this.board.print(false);

        Ship aircraftCarrier = new Ship("Aircraft Carrier",
                                        5);
        Ship battleship = new Ship("Battleship",
                                   4);
        Ship submarine = new Ship("Submarine",
                                  3);
        Ship cruiser = new Ship("Cruiser",
                                3);
        Ship destroyer = new Ship("Destroyer",
                                  2);

        this.addShip(aircraftCarrier);
        this.addShip(battleship);
        this.addShip(submarine);
        this.addShip(cruiser);
        this.addShip(destroyer);

        System.out.println("The game starts!");
        this.board.print(true);
        System.out.println("Take a shot!");


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String shot = scanner.nextLine();

            if (board.isInvalidCoord(shot)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            if (board.hitShip(shot)) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }

            this.board.print(false);
            break;
        }
    }
}
