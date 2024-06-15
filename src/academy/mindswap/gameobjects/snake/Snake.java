package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.util.LinkedList;


public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 10;
    private Direction direction;
    private boolean alive;
    private LinkedList<Position> body;
    private Position firstPosition;
    private Position lastPosition;


    public Snake() {
        this.body = new LinkedList<>();

        this.direction = Direction.UP;
        this.alive = true;
        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            body.add(new Position(20 - i, 20));


        }

    }

    public void increaseSize() {

        body.addLast(lastPosition);


    }

    public void move(Direction direction) {


        if ((this.direction == Direction.UP && direction == Direction.DOWN) ||
                (this.direction == Direction.DOWN && direction == Direction.UP) ||
                (this.direction == Direction.LEFT && direction == Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && direction == Direction.LEFT)) {
            direction = this.direction;
        }


        switch (direction) {
            case UP:
                firstPosition = new Position(body.getFirst().getCol(), body.getFirst().getRow() - 1);
                this.direction = Direction.UP;
                break;
            case DOWN:
                firstPosition = new Position(body.getFirst().getCol(), body.getFirst().getRow() + 1);
                this.direction = Direction.DOWN;
                break;
            case LEFT:
                firstPosition = new Position(body.getFirst().getCol() - 1, body.getFirst().getRow());
                this.direction = Direction.LEFT;
                break;
            case RIGHT:
                firstPosition = new Position(body.getFirst().getCol() + 1, body.getFirst().getRow());
                this.direction = Direction.RIGHT;
                break;
        }
        body.addFirst(firstPosition);
        if (body.size() > SNAKE_INITIAL_SIZE) {
            lastPosition = body.removeLast();

        }

    }

    public void move() {
        move(direction);
    }

    public void die() {


        alive = false;


    }

    public boolean isAlive() {


        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Position getTail() {
        return body.getLast();
    }

    public LinkedList<Position> getFullSnake() {
        return body;
    }

    public int getSnakeSize() {
        return body.size();
    }


    public void addToFirst(Position position) {
        this.body.removeFirst();
        this.body.addFirst(position);
    }
}

