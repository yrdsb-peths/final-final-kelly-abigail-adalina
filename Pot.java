import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pot extends HoldableObject
{
    private GreenfootImage[] tomatoSoup = new GreenfootImage[3];
    private GreenfootImage[] mushroomSoup = new GreenfootImage[3];
    private GreenfootImage[] onionSoup = new GreenfootImage[3];
    private GreenfootImage emptyPot = new GreenfootImage("images/emptyPot.PNG");
    
    int width = 60;
    
    public Pot() {
        emptyPot.scale(width, width);
        for (int i=0; i<3; i++) {
            tomatoSoup[i] = new GreenfootImage ("images/tomatoSoup/tomatoSoup" + i + ".PNG");
            tomatoSoup[i].scale(width, width);
        }
        for (int i=0; i<3; i++) {
            mushroomSoup[i] = new GreenfootImage ("images/mushroomSoup/mushroomSoup" + i + ".PNG");
            mushroomSoup[i].scale(width, width);
        }
        for (int i=0; i<3; i++) {
            onionSoup[i] = new GreenfootImage ("images/onionSoup/onionSoup" + i + ".PNG");
            onionSoup[i].scale(width, width);
        }
    }

    public void act()
    {
        // Add your action code here.
        super.act();
    }
}
