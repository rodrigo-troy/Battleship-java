package battleship;

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
    private final Player player1;
    private final Player player2;

    public Battleship() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
    }

    public void play() {
        placeShips(player1);
        System.out.println("Press Enter and pass the move to another player");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        placeShips(player2);
        System.out.println("Press Enter and pass the move to another player");
        s.nextLine();

        boolean isPlayer1Turn = true;

        while (true) {
            Player player = isPlayer1Turn ? player1 : player2;
            Board board = isPlayer1Turn ? player2.getBoard() : player1.getBoard();
            List<Ship> shipList = isPlayer1Turn ? player2.getShipList() : player1.getShipList();

            board.print(true);
            System.out.println("---------------------");
            board.print(false);

            System.out.printf("%s, it's your turn:\n\n",
                              player.getName());
            String shot = s.nextLine();

            if (board.isInvalidCoord(shot)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            if (board.hitShip(shot)) {
                boolean shipSank = false;
                for (Ship ship : shipList) {
                    if (ship.isSank()) {
                        System.out.println("You sank a ship! Specify a new target:");
                        shipList.remove(ship);
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

            if (isGameOver(isPlayer1Turn ? player2 : player1)) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }

            isPlayer1Turn = !isPlayer1Turn;
            System.out.println("Press Enter and pass the move to another player");
            s.nextLine();
        }
    }

    private void placeShips(Player player) {
        System.out.printf("%s, place your ships on the game field\n\n",
                          player.getName());
        player.getBoard().print(true);

        player.addShip(new Ship("Aircraft Carrier",
                                5));
        player.addShip(new Ship("Battleship",
                                4));
        player.addShip(new Ship("Submarine",
                                3));
        player.addShip(new Ship("Cruiser",
                                3));
        player.addShip(new Ship("Destroyer",
                                2));
    }

    private boolean isGameOver(Player player) {
        for (Ship ship : player.getShipList()) {
            if (!ship.isSank()) {
                return false;
            }
        }

        return true;
    }
}
