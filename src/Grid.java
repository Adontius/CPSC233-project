//contains different characteristics and actions of grids
public class Grid {				
    private int PositionNumber;
    private char symbol;

    public int getPositionNumber(){
        return PositionNumber;
    }

    public char getSymbol(){
        return symbol;
    }

    public void setPositionNumber(int p){
        PositionNumber = p;
    }

    public void setSymbol(char s){
        symbol = s;
    }
}
