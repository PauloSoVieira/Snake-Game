package academy.mindswap.field;

import java.util.Objects;

public class Position {
    private Position position;
    private int xCoords;
    private int yCoords;


    public Position(Position position) {
        this.position = position;
    }

    public Position(int xCoords, int yCoords) {
        {
            this.xCoords = xCoords;
            this.yCoords = yCoords;
        }
    }

    public void setYCoords(int yCoords) {
        this.yCoords = yCoords;
    }

    public void setXCoords(int xCoords) {
        this.xCoords = xCoords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return xCoords == position1.xCoords && yCoords == position1.yCoords && Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, xCoords, yCoords);
    }

    public int getCol() {
        return xCoords;
    }

    public int getRow() {
        return yCoords;
    }
}
