import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends HoldableObject
{
    private GreenfootImage mushroom = new GreenfootImage ("images/mushroom.PNG");
    private GreenfootImage choppedMushroom = new GreenfootImage ("images/choppedMushroom.PNG");
    int width = 80;
    int width2 = 60;
    
    public Mushroom() {
        mushroom.scale(width, width);
        choppedMushroom.scale(width2, width2);
        setImage (mushroom);
    }
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
    }
}
