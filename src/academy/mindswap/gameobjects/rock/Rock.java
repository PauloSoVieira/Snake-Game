package academy.mindswap.gameobjects.rock;

import academy.mindswap.field.Position;

import java.util.Random;

public class Rock {

    private int row;
    private int col;
    private Position position;

    public Rock(int col, int row) {
        Random random = new Random();
        this.col = random.nextInt(col);
        this.row = random.nextInt(row);
        position = new Position(this.col, this.row);

    }

    public Position getPosition() {
        return position;
    }
}
