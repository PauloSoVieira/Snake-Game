package academy.mindswap.field;

public class Position {
private  Position position;
private int xCoords;
private int yCoords;



    public Position(Position position) {
       this.position=position;
    }


    public Position(int xCoords,int yCoords) {{
        this.xCoords=xCoords;
        this.yCoords=yCoords;
    }
    }

    public int getCol() {
        return xCoords;
    }

    public int getRow() {
        return yCoords;
    }
}
