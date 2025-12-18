import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Onion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Onion extends Food
{
    private GreenfootImage onion = new GreenfootImage("images/onion.PNG");
    private GreenfootImage choppedOnion = new GreenfootImage ("images/choppedOnion.PNG");
    int width = 80;
    int width2 = 60;
    
    public Onion() {
        onion.scale(width, width);
        choppedOnion.scale(width2, width2);
        setImage (onion);
        
        this.setType("onion");
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
