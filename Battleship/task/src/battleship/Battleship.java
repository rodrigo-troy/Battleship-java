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
    private final Player player1;
    private final Player player2;

    public Battleship() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
    }

    public void play() {
        this.placeShips(player1);
        System.out.println("Press Enter and pass the move to another player");
        Scanner s = new Scanner(System.in);
        s.nextLine();
        this.placeShips(player2);
        System.out.println("Press Enter and pass the move to another player");
        s.nextLine();

        boolean isPlayer1Turn = true;

        while (true) {
            Player opponent = isPlayer1Turn ? player2 : player1;

            opponent.getBoard().print(true);
            System.out.println("---------------------");
            if (isPlayer1Turn) {
                player1.getBoard().print(false);
            } else {
                player2.getBoard().print(false);
            }

            System.out.printf("%s, it's your turn:\n\n",
                              isPlayer1Turn ? player1.getName() : player2.getName());
            String shot = s.nextLine();

            if (opponent.getBoard().isInvalidCoord(shot)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            if (opponent.getBoard().hitShip(shot)) {
                boolean shipSank = false;
                for (Ship ship : opponent.getShipList()) {
                    if (ship.isSank()) {
                        System.out.println("You sank a ship! Specify a new target:");
                        opponent.getShipList().remove(ship);
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

            if (isGameOver(opponent)) {
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
