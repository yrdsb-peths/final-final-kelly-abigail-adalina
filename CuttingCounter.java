import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class cuttingCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CuttingCounter extends Counter
{
    private GreenfootImage cuttingCounter = new GreenfootImage ("images/cuttingCounter.PNG");
    private GreenfootImage selectedCuttingCounter = new GreenfootImage ("images/selectedCuttingCounter.PNG");
    
    int width = 60;
    
    public CuttingCounter() {
        cuttingCounter.scale(width, width);
        selectedCuttingCounter.scale(width, width);
        setImage (cuttingCounter);
    }
    
    /**
     * Act - do whatever the cuttingCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
