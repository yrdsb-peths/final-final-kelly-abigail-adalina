import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Order here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Order extends Actor
{
    private GreenfootImage tomatoSoupOrder = new GreenfootImage ("images/tomatoSoupOrder.PNG");
    private GreenfootImage mushroomSoupOrder = new GreenfootImage ("images/mushroomSoupOrder.PNG");
    private GreenfootImage onionSoupOrder = new GreenfootImage ("images/onionSoupOrder.PNG");
    private int width = 120;
    private int height = 90;
    
    private int orderTime = 30000; //30 seconds
    private SimpleTimer orderTimer = new SimpleTimer();
    private SuperStatBar countDownBar;
    Color blue = new Color (0, 227, 216);
    Color grey = new Color (112, 112, 112);
    Color yellow = new Color (227, 204, 0);
    Color red = new Color (196, 23, 0);
    
    public Order() {
        tomatoSoupOrder.scale(width, height);
        mushroomSoupOrder.scale(width, height);
        onionSoupOrder.scale(width, height);
        
        createRandomOrder();
        orderTimer.mark();
        
        countDownBar = new SuperStatBar(orderTime, orderTime, this, 110, 5, -40, blue, grey);
    }
    
    public void act()
    {
        updateCountDownBar();
        if (orderTimer.millisElapsed() > orderTime) {
            removeSelf();
        }
    }
    
    protected void addedToWorld(World w) {
        w.addObject(countDownBar, getX(), getY() - 30);
    }
    
    private void createRandomOrder() {
        int num = Greenfoot.getRandomNumber(3);
        if (num == 0) setImage(tomatoSoupOrder);
        else if (num == 1) setImage(mushroomSoupOrder);
        else if (num == 2) setImage (onionSoupOrder);
    }
    
    private void removeSelf() {
        MyWorld w = (MyWorld) getWorld();
    
        // Remove from array
        for (int i = 0; i < w.soupOrders.length; i++) {
            if (w.soupOrders[i] == this) {
                w.soupOrders[i] = null;
                break;
            }
        }
    
        w.removeObject(this);
        w.removeObject(countDownBar);
    }
    
    private void updateCountDownBar() {
        int elapsed = orderTimer.millisElapsed();
        int remainingTime = orderTime - elapsed;
        countDownBar.update (remainingTime);
        
        if (remainingTime < 5000) {
            countDownBar.setColors(red, grey);
        } else if (remainingTime < 10000) {
            countDownBar.setColors(yellow, grey);
        } else {
            countDownBar.setColors(blue, grey);
        }
    }
}
