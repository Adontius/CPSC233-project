public class Avatar
{
    private int tipMoney; 
    public int strikeCount; //
    public int vertPos; //vertical position of avatar (y axis)
    public int horzPos; //horizontal position of avatar (x axis)

    private Avatar()
    {
        //initializes avatar stats at zero
        tipMoney = 0; 
        strikeCount = 0;
        vertPos = 0;
        horzPos = 0; //sets  initial position of avatar to (0, 0)

    }

    private void moveUp()
    {
        vertPos += 1;
    }

    private void moveDown()
    {
        vertPos -= 1;
    }

    private void moveRight()
    {
        horzPos += 1;
    }

    private void moveLeft()
    {
        horzPos -= 1;
    }

    
}