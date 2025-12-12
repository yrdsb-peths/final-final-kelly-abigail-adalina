import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerController extends SuperSmoothMover
{
    //constant
    private static final int SPEED = 20;
    //variables
    private GreenfootImage controller = new GreenfootImage ("images/emptyController.PNG");
    int width = 60;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PlayerController () {
        controller.scale(width,width);
        setImage (controller);
    }
    public void act()
    {
        controlPlayer();
    }
    
    /**
     * use arrows to move the player
     * "a" to get or place down holdable objects
     */
    public void controlPlayer() {
        int newX = getX();
        int newY = getY();
    
        if (Greenfoot.isKeyDown("left"))  newX -= SPEED;
        if (Greenfoot.isKeyDown("right")) newX += SPEED;
        if (Greenfoot.isKeyDown("up"))    newY -= SPEED;
        if (Greenfoot.isKeyDown("down"))  newY += SPEED;
    
        // Check BEFORE moving
        if (!willCollide(newX, newY)) {
            setLocation(newX, newY);
        }
    }

    private boolean willCollide(int nextX, int nextY) {

        // Temporarily move to next position
        int oldX = getX();
        int oldY = getY();
        setLocation(nextX, nextY);
    
        Actor counter = getOneIntersectingObject(Counter.class);
    
        // Move back immediately
        setLocation(oldX, oldY);
    
        return counter != null;
    }
    
    //check the number of selected counters
    public int getNumNearbyCounters() {
        boolean up    = getOneObjectAtOffset(0, -55, Counter.class) != null;
        boolean down  = getOneObjectAtOffset(0,  55, Counter.class) != null;
        boolean left  = getOneObjectAtOffset(-55, 0, Counter.class) != null;
        boolean right = getOneObjectAtOffset(55, 0, Counter.class) != null;
        
        int count = 0;
        if (up)    count++;
        if (down)  count++;
        if (left)  count++;
        if (right) count++;
        
        return count;
    }
    
    //if player is next to only one counter, select that counter
    //if player is next to two or more counters(e.g. in a corner),
    //select the one it faces
    public Counter getSelectedCounter() {
        int numNearbyCounters = getNumNearbyCounters();
        if (numNearbyCounters == 0) return null;
    
        MyWorld w = (MyWorld)getWorld();
        String dir = w.playerImage.getFacingDirection();
    
        // 1. If only one, return whatever is there
        if (numNearbyCounters == 1) {
            if (getOneObjectAtOffset(0, -55, Counter.class) != null) {
                return (Counter)getOneObjectAtOffset(0, -55, Counter.class);
            } else if (getOneObjectAtOffset(0, 55, Counter.class) != null) {
                return (Counter)getOneObjectAtOffset(0, 55, Counter.class);
            } else if (getOneObjectAtOffset(-55, 0, Counter.class) != null) {
                return (Counter)getOneObjectAtOffset(-55, 0, Counter.class);
            } else if (getOneObjectAtOffset(55, 0, Counter.class) != null) {
                return (Counter)getOneObjectAtOffset(55, 0, Counter.class);
            }
        }
    
        // 2. If multiple → pick the one in front
        Counter best = null;
        if (dir.equals("front") ) best = (Counter)getOneObjectAtOffset (0, 55, Counter.class);
        else if (dir.equals("back") ) best = (Counter)getOneObjectAtOffset (0, -55, Counter.class);
        else if (dir.equals("left") ) best = (Counter)getOneObjectAtOffset (-55, 0, Counter.class);
        else if (dir.equals("right") ) best = (Counter)getOneObjectAtOffset (55, 0, Counter.class);
    
        return best;
    }
}
