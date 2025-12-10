import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        int originalX = this.getX();
        int originalY = this.getY();
        
        controlPlayer();
        
        if (getOneIntersectingObject(Counter.class) != null) {
            setLocation (originalX, originalY);
        }
    }
    
    public void controlPlayer() {
        if (Greenfoot.isKeyDown("left")) {
            move(-10);
        } else if (Greenfoot.isKeyDown("right")) {
            move(10);
        } else if (Greenfoot.isKeyDown("Up")) {
            setLocation (getX(), getY() - 10);
        } else if (Greenfoot.isKeyDown("Down")) {
            setLocation (getX(), getY() + 10);
        }
        
        if (Greenfoot.isKeyDown("a")) {
            if (getObjectsInRange(1, Counter.class) != null) {
                MyWorld world = (MyWorld) getWorld();
                world.getFood();
            }
        }
    }
}
