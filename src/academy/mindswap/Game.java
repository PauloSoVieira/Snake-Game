package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;
    private int cols;
    private int rows;
    private int count;
    private int record;

    public Game(int cols, int rows, int delay) {
        this.cols = cols;
        this.rows = rows;
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
        generateFruit();
    }

    public void start() throws InterruptedException {


        while (true) {
            if (!snake.isAlive()) {
                break;
            }
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
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
            }
        }
        snake.move();
    }

    private void checkCollisions() {


        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
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
                generateFruit();

            }
        }
    }
}
