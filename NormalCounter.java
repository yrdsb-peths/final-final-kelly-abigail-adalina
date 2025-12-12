import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class normalCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalCounter extends Counter
{
    private GreenfootImage normalCounter = new GreenfootImage ("images/normalCounter.PNG");
    private GreenfootImage selectedNormalCounter = new GreenfootImage ("images/selectedNormalCounter.PNG");
    int width = 60;
    
    public NormalCounter() {
        normalCounter.scale(width, width);
        selectedNormalCounter.scale(width, width);
        setImage (normalCounter);
    }
    /**
     * Act - do whatever the normalCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // check if being selected, if so, change image to selectedNormalCounter
        checkIfSelected (selectedNormalCounter, normalCounter);
        
    }
}
