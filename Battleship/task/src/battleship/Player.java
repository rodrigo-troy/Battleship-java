package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Battleship
 * User: rodrigotroy
 * Date: 23-03-22
 * Time: 15:26
 */
public class Player {
    private final String name;
    private final Board board;
    private List<Ship> shipList;

    public Player(String name) {
        this.board = new Board();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShipList() {
        if (shipList == null) {
            shipList = new ArrayList<>();
        }

        return shipList;
    }

    public Board getBoard() {
        return board;
    }

    public void addShip(Ship ship) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n\n",
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

        this.getShipList().add(ship);
        board.print(false);
    }

}
