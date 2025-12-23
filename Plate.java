import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plate extends HoldableObject
{
    private GreenfootImage emptyPlate = new GreenfootImage ("images/emptyPlate.PNG");
    private GreenfootImage tomatoSoupPlate = new GreenfootImage ("images/tomatoSoupPlate.PNG");
    private GreenfootImage onionSoupPlate = new GreenfootImage ("images/onionSoupPlate.PNG");
    private GreenfootImage mushroomSoupPlate = new GreenfootImage ("images/mushroomSoupPlate.PNG");
    int width = 60;
    int height = 80;
    
    private boolean isEmpty;
    private String type;
    
    public Plate() {
        emptyPlate.scale(width, height);
        tomatoSoupPlate.scale(width, height);
        onionSoupPlate.scale(width, height);
        mushroomSoupPlate.scale(width, height);
        
        setImage(emptyPlate);
        isEmpty = true;
    }
    
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    public boolean isEmpty() {
        return isEmpty;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setImageToTomatoSoupPlate() {
        setImage (tomatoSoupPlate);
    }
    
    public void setImageToOnionSoupPlate() {
        setImage (onionSoupPlate);
    }
    
    public void setImageToMushroomSoupPlate() {
        setImage (mushroomSoupPlate);
    }
    
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
