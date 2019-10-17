
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
        PositionNumber = p;
    }

    public char setSymbol(char s){
        symbol = s;
    }

}

public class House extends Grid{
    private boolean hasOrder;

}

public class Obstacles{

}