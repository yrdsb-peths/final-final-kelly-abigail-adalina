import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HoldableObject extends Actor
{
    private boolean isBeingHeld;

    public HoldableObject () {
    }
    
    public void act()
    {
        if (isBeingHeld) moveWithPlayer();
    }
    
    public void moveWithPlayer() {
        MyWorld world = (MyWorld) getWorld();
        setLocation (world.player.getX(), world.player.getY());
    }
    
    public void setIsBeingHeld(boolean isBeingHeld) {
        this.isBeingHeld = isBeingHeld;
    }
    
    public boolean getIsBeingHeld() {
        return isBeingHeld;
    }
    
    public String getClassType(String classType) {
        return classType;
    }
}
