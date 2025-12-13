import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class foodCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FoodCounter extends Counter
{
    //variables
    private String type;
    private GreenfootImage mushroomCounter = new GreenfootImage ("images/mushroomCounter.PNG");
    private GreenfootImage tomatoCounter = new GreenfootImage ("images/tomatoCounter.PNG");
    private GreenfootImage onionCounter = new GreenfootImage ("images/onionCounter.PNG");
    private GreenfootImage selectedMushroomCounter = new GreenfootImage ("images/selectedMushroomCounter.PNG");
    private GreenfootImage selectedTomatoCounter = new GreenfootImage ("images/selectedTomatoCounter.PNG");
    private GreenfootImage selectedOnionCounter = new GreenfootImage ("images/selectedOnionCounter.PNG");
    
    int width = 60;
    
    
    public FoodCounter(String type) {

        this.type = type;
        //set different images according to the vegtable type
        if (type.equals ("mushroom")) {
            mushroomCounter.scale(width, width);
            selectedMushroomCounter.scale(width, width);
            setImage (mushroomCounter);
        } else if (type.equals ("tomato")) {
            tomatoCounter.scale(width, width);
            selectedTomatoCounter.scale(width, width);
            setImage (tomatoCounter);
        } else if (type.equals ("onion")) {
            onionCounter.scale(width, width);
            selectedOnionCounter.scale(width, width);
            setImage (onionCounter);
        }
    }
    /**
     * Act - do whatever the foodCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if (type.equals ("mushroom")) {
            checkIfSelected (selectedMushroomCounter, mushroomCounter);
        } else if (type.equals ("tomato")) {
            checkIfSelected (selectedTomatoCounter, tomatoCounter);
        } else if (type.equals ("onion")) {
            checkIfSelected (selectedOnionCounter, onionCounter);
        }
    }
    
    public String getType() {
        return type;
    }
}
