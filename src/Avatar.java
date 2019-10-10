public class Avatar
{
    private int tipMoney; 
    public int strikeCount; //
    public int vertPos; //vertical position of avatar (y axis)
    public int horzPos; //horizontal position of avatar (x axis)
    public boolean pizzaDelivered;

    //Hello
    public Avatar()
    {
        //initializes avatar stats at zero
        tipMoney = 0; 
        strikeCount = 0;
        vertPos = 0;
        horzPos = 0; //sets  initial position of avatar to (0, 0)
        pizzaDelivered = false;
    }

    public void moveUp()
    {
        vertPos += 1;
    }

    public void moveDown()
    {
        vertPos -= 1;
    }

    public void moveRight()
    {
        horzPos += 1;
    }

    public void moveLeft()
    {
        horzPos -= 1;
    }

    public boolean deliverPizza()
    {
        pizzaDelivered = true;
        return pizzaDelivered;
    }

    public void receiveTip(int tipAmount)
    {
        tipMoney += tipAmount;
    }

    public void addStrike()
    {
        strikeCount += 1;
    }

}