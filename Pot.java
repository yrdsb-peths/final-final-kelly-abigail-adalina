import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pot extends HoldableObject
{
    public GreenfootImage[] tomatoSoup = new GreenfootImage[3];
    public GreenfootImage[] mushroomSoup = new GreenfootImage[3];
    public GreenfootImage[] onionSoup = new GreenfootImage[3];
    private GreenfootImage emptyPot = new GreenfootImage("images/emptyPot.PNG");
    
    int width = 60;
    int height = 105;
    
    private int numFoodInside;
    private String type;
    
    private int requiredCookingTime = 0;
    private int currentCookingTime = 0;
    
    private SimpleTimer cookingTimer = new SimpleTimer();
    public SuperStatBar cookingStatusBar;
    private Color green = new Color (56, 255, 119);
    private Color grey = new Color (112, 112, 112);
    
    private boolean finishedCooking = false;
    
    public Pot() {
        emptyPot.scale(width, height);
        numFoodInside = 0;
        for (int i=0; i<3; i++) {
            tomatoSoup[i] = new GreenfootImage ("images/tomatoSoup/tomatoSoup" + i + ".PNG");
            tomatoSoup[i].scale(width, height);
        }
        for (int i=0; i<3; i++) {
            mushroomSoup[i] = new GreenfootImage ("images/mushroomSoup/mushroomSoup" + i + ".PNG");
            mushroomSoup[i].scale(width, height);
        }
        for (int i=0; i<3; i++) {
            onionSoup[i] = new GreenfootImage ("images/onionSoup/onionSoup" + i + ".PNG");
            onionSoup[i].scale(width, height);
        }
        
        cookingTimer.mark();
        cookingStatusBar = new SuperStatBar(requiredCookingTime, currentCookingTime, this, 50, 10, 22, green, grey, true);
        cookingStatusBar.setToInvisible();
        
        setImage(emptyPot);
    }

    public void act()
    {
        // Add your action code here.
        super.act();
        if(getOneObjectAtOffset(0, 0, Counter.class) instanceof StoveCounter) {
            increaseCurrentCuttingTime();
        }
    }
    
    protected void addedToWorld(World w) {
        w.addObject(cookingStatusBar, getX(), getY() + 22);
    }
    
    private void increaseCurrentCuttingTime() {
        if(cookingTimer.millisElapsed() >= 5 && currentCookingTime < requiredCookingTime)currentCookingTime += 5;
        else return;
        cookingTimer.mark();
        
        cookingStatusBar.update(currentCookingTime);
    }
    
    public boolean hasFinishedCooking() {
        return currentCookingTime == requiredCookingTime && numFoodInside == 3;
    }
    
    /**
     * initialize all variables and timers to an empty pot
     */
    public void setToEmptyPotStatus() {
        requiredCookingTime = 0;
        currentCookingTime = 0;
        
        cookingStatusBar.setMaxVal (0);
        cookingStatusBar.update(currentCookingTime);
        cookingStatusBar.setToInvisible();
        
        numFoodInside = 0;
        type = null;
        setImage (emptyPot);
        cookingTimer.mark();
        
    }
    
    public void setNumFoodInside(int num) {
        numFoodInside = num;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public int getNumFoodInside() {
        return numFoodInside;
    }
    
    public void setRequiredCookingTime(int time) {
        requiredCookingTime = time;
    }
}
