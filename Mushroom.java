import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Food
{
    private GreenfootImage mushroom = new GreenfootImage ("images/mushroom.PNG");
    public GreenfootImage choppedMushroom = new GreenfootImage ("images/choppedMushroom.PNG");
    int width = 80;
    int width2 = 60;
    
    public Mushroom() {
        mushroom.scale(width, width);
        choppedMushroom.scale(width2, width2);
        setImage (mushroom);
        
        this.setType("mushroom");
        cuttingTimer.mark();
        cuttingStatusBar = new SuperStatBar(300, 0, this, 50, 10, 22, green, grey, true);
        cuttingStatusBar.setToInvisible();
        
    }

    public void act()
    {
        // Add your action code here.
        super.act();
    }

}
