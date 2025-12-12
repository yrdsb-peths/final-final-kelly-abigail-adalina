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
    int offset = 15;
    int width = 60;
    
    public StoveCounter() {
        stoveCounter.scale(width, width);
        selectedStoveCounter.scale(width, width);
        emptyPot.scale(width, width);
        setImage (stoveCounter);
    }
    /**
     * Act - do whatever the stoveCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkIfSelected(selectedStoveCounter, stoveCounter);
    }
    
    protected void addedToWorld(World w)
    {
        Pot pot = new Pot();
        pot.setImage(emptyPot);

        // add pot at the stove's position
        w.addObject(pot, getX(), getY()-offset);
        this.setObjectOnTop(pot);
    }
}
