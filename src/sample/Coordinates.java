package sample;

public class Coordinates {

    public int xCoordinate;
    public int yCoordinate;

    public Coordinates(int x, int y) {
        this.xCoordinate = translateCoordinate(x);
        this.yCoordinate = translateCoordinate(y);
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate(int x) {
        this.xCoordinate = translateCoordinate(x);
    }

    public void setYCoordinate(int y) {
        this.yCoordinate = translateCoordinate(y);
    }

    private int translateCoordinate(int coordinate) {
        if(((coordinate >= 3) && (coordinate <= 24)) || ((coordinate >= 376) && (coordinate <= 397)))
        {
            return 1;
        }

        else if ((coordinate > 24) && (coordinate <= 45) || ((coordinate >= 355) && (coordinate < 376)))
        {
            return 2;
        }
        else if ((coordinate > 45) && (coordinate <= 66) || ((coordinate >= 334) && (coordinate < 355)))
        {
            return 3;
        }
        else if ((coordinate > 66) && (coordinate <= 87) || ((coordinate >= 313) && (coordinate < 334)))
        {
            return 4;
        }
        else if ((coordinate > 87) && (coordinate <= 108) || ((coordinate >= 292) && (coordinate < 313)))
        {
            return 5;
        }
        else if ((coordinate > 108) && (coordinate <= 129) || ((coordinate >= 271) && (coordinate < 292)))
        {
            return 6;
        }
        else if ((coordinate > 129) && (coordinate <= 150) || ((coordinate >= 250) && (coordinate < 271)))
        {
            return 7;
        }
        else if ((coordinate > 150) && (coordinate <= 171) || ((coordinate >= 229) && (coordinate < 250)))
        {
            return 8;
        }
        else if ((coordinate > 171) && (coordinate <= 192) || ((coordinate >= 208) && (coordinate < 229)))
        {
            return 9;
        }

        else if ((coordinate > 192) && (coordinate <= 200) || ((coordinate >= 200) && (coordinate < 208)))
        {
            return 10;
        }

        else
        {
            return 0;
        }
    }
}
