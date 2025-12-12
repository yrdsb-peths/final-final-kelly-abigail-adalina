import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tomato here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tomato extends HoldableObject
{    
    private GreenfootImage tomato = new GreenfootImage ("images/tomato.PNG");
    private GreenfootImage choppedTomato = new GreenfootImage ("images/choppedTomato.PNG");
    int width = 80;
    int width2 = 60;
    
    public Tomato() {
        tomato.scale(width, width);
        choppedTomato.scale(width2, width2);
        setImage (tomato);
    }
    /**
     * Act - do whatever the Tomato wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
    }
}
