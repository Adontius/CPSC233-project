
public class Grid {
    private int PositionNumber;
    private char symbol;

    public int getPositionNumber(){
        return PositionNumber;
    }

    public char getSymbol(){
        return symbol;
    }

    public int setPositionNumber(int p){
        int PositionNumber = p;
    }

    public char setSymbol(char s){
        char symbol = 's';
    }
}

public class House extends Grid{
    private boolean order;

    public House(boolean order) {
        order = true;
    }

    public boolean hasOrder(){
        return this.order;
    }

    public boolean setOrder(boolean order){
        return this.order = order;
    }

}

public class Obstacles{
    private boolean activated;
    private int obstacles;

    public boolean checkObstacles(){
        return this.activated;
    }

    public boolean setObstacles(boolean obstacles){
        return this.activated = activated;
    }
}