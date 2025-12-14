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
        for (int i=0; i<3; i++) {
            tomatoSoup[i] = new GreenfootImage ("images/tomatoSoup/tomatoSoup" + i + ".PNG");
        }
        for (int i=0; i<3; i++) {
            mushroomSoup[i] = new GreenfootImage ("images/mushroomSoup/mushroomSoup" + i + ".PNG");
        }
        for (int i=0; i<3; i++) {
            onionSoup[i] = new GreenfootImage ("images/onionSoup/onionSoup" + i + ".PNG");
        }
    }
    /**
     * Act - do whatever the stoveCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkIfSelected(selectedStoveCounter, stoveCounter);
        
        Actor top = this.getObjectOnTop();
        
            
        if(top instanceof Tomato && !hasFoodOnTop)
        {
            addFood();
            hasFoodOnTop = true;
        }
        
        if(top == null)
        {
            hasFoodOnTop = false;
        }
    }
    protected void addedToWorld(World w)
    {
        pot = new Pot();
        pot.setImage(emptyPot);

        // add pot at the stove's position
        w.addObject(pot, getX(), getY());
        this.setObjectOnTop(pot);
    }
    private void addFood()
    {
        Actor top = this.getObjectOnTop();
        
        if(top instanceof Tomato && indexTomato < tomatoSoup.length - 1)
        {
            pot.setImage(tomatoSoup[indexTomato]);
            indexTomato ++;
        }
        
        if(top != null)
        {
            getWorld().removeObject(top);
        }
    }
}
