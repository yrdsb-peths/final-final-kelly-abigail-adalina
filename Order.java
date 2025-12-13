import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Order here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Order extends Actor
{
    /**
     * Act - do whatever the Order wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int timer = 0;
    
    public void incrementTimer()
    {
        timer++;
    }
    
    public int getTimer()
    {
        return timer;
    }
    
}
