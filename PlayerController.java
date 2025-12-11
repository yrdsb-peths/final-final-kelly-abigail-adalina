import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends Actor
{
    //constant
    private static final int SPEED = 20;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PlayerController () {
        setImage ("images/emptyController.png");
    }
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
            move(-SPEED);
        } else if (Greenfoot.isKeyDown("right")) {
            move(SPEED);
        } else if (Greenfoot.isKeyDown("Up")) {
            setLocation (getX(), getY() - SPEED);
        } else if (Greenfoot.isKeyDown("Down")) {
            setLocation (getX(), getY() + SPEED);
        }
        
        if (Greenfoot.isKeyDown("a")) {
            if (getObjectsInRange(1, Counter.class) != null) {
                MyWorld world = (MyWorld) getWorld();
                world.getFood();
            }
        }
    }
}
