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
    
    private int orderTime = 3000;
    private SimpleTimer orderTimer = new SimpleTimer();
    
    public Order() {
        tomatoSoupOrder.scale(width, height);
        mushroomSoupOrder.scale(width, height);
        onionSoupOrder.scale(width, height);
        
        createRandomOrder();
        orderTimer.mark();
    }
    
    public void act()
    {
        if (orderTimer.millisElapsed() > orderTime) {
            removeSelf();
        }
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
    }
}
