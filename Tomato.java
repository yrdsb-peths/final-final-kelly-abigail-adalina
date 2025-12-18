import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tomato here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tomato extends Food
{    
    private GreenfootImage tomato = new GreenfootImage ("images/tomato.PNG");
    private GreenfootImage choppedTomato = new GreenfootImage ("images/choppedTomato.PNG");
    int width = 80;
    int width2 = 60;
    
    public Tomato() {
        tomato.scale(width, width);
        choppedTomato.scale(width2, width2);
        setImage (tomato);
        
        this.setType("tomato");
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
