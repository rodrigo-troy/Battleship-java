package battleship;

import java.util.ArrayList;
import java.util.List;
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
    private List<Ship> shipList;

    public Battleship() {
        this.board = new Board();
        this.shipList = new ArrayList<>();
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

        this.shipList.add(ship);
        this.board.print(false);
    }

    public void play() {
        this.board.print(false);

        this.addShip(new Ship("Aircraft Carrier",
                              5));
        this.addShip(new Ship("Battleship",
                              4));
        this.addShip(new Ship("Submarine",
                              3));
        this.addShip(new Ship("Cruiser",
                              3));
        this.addShip(new Ship("Destroyer",
                              2));

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
                boolean shipSank = false;
                for (Ship ship : this.shipList) {
                    if (ship.isSank()) {
                        System.out.println("You sank a ship! Specify a new target:");
                        this.shipList.remove(ship);
                        shipSank = true;
                        break;
                    }
                }

                if (!shipSank) {
                    System.out.println("You hit a ship! Try again:");
                }
            } else {
                System.out.println("You missed!");
            }

            this.board.print(true);

            if (isGameOver()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
        }
    }

    private boolean isGameOver() {
        for (Ship ship : this.shipList) {
            if (!ship.isSank()) {
                return false;
            }
        }

        return true;
    }
}
