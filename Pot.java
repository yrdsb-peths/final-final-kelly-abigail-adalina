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
    
    public Pot() {
        emptyPot.scale(width, width);
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
    }

    public void act()
    {
        // Add your action code here.
        super.act();
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
}
