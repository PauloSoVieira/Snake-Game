package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.rock.Rock;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    private final int ROCKTIMERCHANGE = 50;
    private Snake snake;
    private Fruit fruit;
    private int delay;
    private int cols;
    private int rows;
    private List<Rock> rocks;
    private Rock rock;
    private int rockTimer;
    private GameStateEnum gameStateEnum;
    private Scanner scanner;
    private int count;
    private int record;


    public Game(int cols, int rows, int delay) {
        this.cols = cols;
        this.rows = rows;
        this.delay = delay;
        this.scanner = new Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {

        Field.init(cols, rows);
        snake = new Snake();
        rocks = new ArrayList<>();
        generateFruit();
        generateRock();
        rockTimer = 0;
        count = 0;

        gameStateEnum = GameStateEnum.RUNNING;
    }

    private void generateRock() {

        do {
            rock = new Rock(cols, rows);
        } while (snake.getFullSnake().contains(rock.getPosition()));
        rocks.add(rock);
        Field.drawRock(rock);

    }

    public void start() throws InterruptedException {


        while (gameStateEnum != GameStateEnum.GAME_OVER) {
            if (gameStateEnum == GameStateEnum.RUNNING) {
                Thread.sleep(delay);
                Field.clearTail(snake);
                moveSnake();
                checkCollisions();
                Field.drawSnake(snake);

                rockTimer++;
                if (rockTimer >= ROCKTIMERCHANGE) {
                    generateRock();
                    rockTimer = 0;
                }
            } else if (gameStateEnum == GameStateEnum.PAUSE) {

                handlePauseInput();
            }

        }
        System.out.println("Game Over. Wanna play again? (Y/N)");
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("Y")) {
            restartGame();
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private void restartGame() throws InterruptedException {
        initializeGame();
        start();
    }

    private void generateFruit() {

        do {
            fruit = new Fruit(cols, rows);
        } while (snake.getFullSnake().contains(fruit.getPosition()));
        Field.drawFruit(fruit);
        // System.out.println("rows = " + fruit.getPosition());
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
                case Enter:
                    pauseAndContinue();
                    return;
            }
        }
        snake.move();
    }

    private void pauseAndContinue() {
        if (gameStateEnum == GameStateEnum.RUNNING) {
            gameStateEnum = GameStateEnum.PAUSE;
        } else if (gameStateEnum == GameStateEnum.PAUSE) {
            gameStateEnum = GameStateEnum.RUNNING;

        }
    }

    private void handlePauseInput() {
        Key k = Field.readInput();
        if (k != null && k.getKind() == Key.Kind.Enter) {
            gameStateEnum = GameStateEnum.RUNNING;
        }
    }


    public void showRecord() {
        if (count > record) {
            record = count;
            System.out.println("New record : " + record);
        } else {
            System.out.println("Actual record its still " + record);
        }
    }

    private void checkCollisions() {


        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
                gameStateEnum = GameStateEnum.GAME_OVER;
                showRecord();
                return;
            }

            for (Rock rock : rocks) {
                if (snake.getHead().equals(rock.getPosition())) {
                    snake.die();

                    gameStateEnum = GameStateEnum.GAME_OVER;
                    showRecord();
                    return;
                }
            }


            if (snake.getHead().getCol() > Field.getWidth()) {
                snake.getHead().setXCoords(0);
            }
            if (snake.getHead().getCol() < 0) {
                snake.getHead().setXCoords(Field.getWidth());
            }

            if (snake.getHead().getRow() > Field.getHeight()) {
                snake.getHead().setYCoords(0);
            }
            if (snake.getHead().getRow() < 0) {
                snake.getHead().setYCoords(Field.getHeight());
            }




/*
            if (snake.getHead().getCol() < 1 || snake.getHead().getCol() >= cols - 1 || snake.getHead().getRow() < 1 || snake.getHead().getRow() >= rows - 1) {
                snake.die();
            }


 */

            if (snake.getHead().equals(fruit.getPosition())) {
                snake.increaseSize();
                count++;
                generateFruit();

            }
        }
    }
}
