import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isBeingHeld;

    public Food () {
        isBeingHeld = true;
    }
    public void act()
    {
        moveWithPlayer();
        
    }
    
    public void moveWithPlayer() {
        MyWorld world = (MyWorld) getWorld();
        if (isBeingHeld) {
            setLocation (world.player.getX(), world.player.getY());
        }
    }
}
