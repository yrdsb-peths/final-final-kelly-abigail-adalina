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
    private GreenfootImage mushroomCounter = new GreenfootImage ("images/mushroomCounter.png");
    private GreenfootImage tomatoCounter = new GreenfootImage ("images/tomatoCounter.png");
    private GreenfootImage onionCounter = new GreenfootImage ("images/onionCounter.png");
    
    
    public FoodCounter(String type) {

        this.type = type;
        //set different images according to the vegtable type
        if (type.equals ("mushroom")) {
            setImage (mushroomCounter);
        } else if (type.equals ("tomato")) {
            setImage (tomatoCounter);
        } else if (type.equals ("onion")) {
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
    }
}
