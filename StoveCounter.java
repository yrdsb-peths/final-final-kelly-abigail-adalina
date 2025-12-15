import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class stoveCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoveCounter extends Counter
{
    private GreenfootImage stoveCounter = new GreenfootImage ("images/stoveCounter.PNG");
    private GreenfootImage selectedStoveCounter = new GreenfootImage ("images/selectedStoveCounter.PNG");
    private GreenfootImage emptyPot = new GreenfootImage("images/emptyPot.PNG");
    
    int width = 60;
    int height = 105;
    
    public StoveCounter() {
        stoveCounter.scale(width, width);
        selectedStoveCounter.scale(width, width);
        emptyPot.scale(width, height);
        setImage (stoveCounter);
        
    }
    
    public void act()
    {
        checkIfSelected(selectedStoveCounter, stoveCounter);
        
    }
    protected void addedToWorld(World w)
    {
        Pot pot = new Pot();
        pot.setImage(emptyPot);

        // add pot at the stove's position
        w.addObject(pot, getX(), getY());
        this.setObjectOnTop(pot);
    }
    
}
