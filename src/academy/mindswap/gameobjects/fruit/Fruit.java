package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

import java.util.Random;

public class Fruit {
    private int col;
    private int row;
    private Position position;

    public Fruit(int col, int row) {
        Random random = new Random();
        this.col = random.nextInt(col);
        this.row = random.nextInt(row);
        this.position = new Position(this.col, this.row);
    }


    public Position getPosition() {
        return position;
    }
}
