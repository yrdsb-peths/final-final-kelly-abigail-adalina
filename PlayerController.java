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
    private static final GreenfootImage CONTROLLER = new GreenfootImage ("images/emptyController.PNG");
    private static final int WIDTH = 60;
    //variables
    
    boolean isHoldingObject = false;
    private HoldableObject holdingObject = null;
    
    public PlayerController () {
        CONTROLLER.scale(WIDTH,WIDTH);
        setImage (CONTROLLER);
    }
    public void act()
    {
        controlPlayer();
    }

    /**
     * use arrows to move the player
     * "a" to get or place down holdable objects
     */
    private void controlPlayer() {
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
        
        if ("a".equals(Greenfoot.getKey())) {
            holdOrPlaceDownHoldableObject();
        }
        
        if (Greenfoot.isKeyDown("w")) chop();
    }
    
    public void chop() {
        
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
    private int getNumNearbyCounters() {
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
    
    /*
     * if the player is holding an object, place it down
     * if the player is not holding an object, take or generate one
     */
    private void holdOrPlaceDownHoldableObject() {
        Counter selectedCounter = getSelectedCounter();
        
        //if there is no nearby Counter, do nothing
        if (selectedCounter != null) {
            HoldableObject selectedObject = selectedCounter.getObjectOnTop();
            if (isHoldingObject) {
                
                /*
                 * place down object if:
                 * 1. player is holding an object
                 * 2. there is a selected Counter
                 * 3. there is no object on top of the selected Counter
                 */
                if (selectedObject == null){
                    holdingObject.setLocation (selectedCounter.getX(), selectedCounter.getY());
                    selectedCounter.setObjectOnTop (holdingObject);
                    holdingObject.setIsBeingHeld(false);
                    holdingObject = null;
                    isHoldingObject = false;
                }
                
            } else {
                
                /*
                 * take object up if
                 * 1. player is not holding an object
                 * 2. there is an object on the nearby Counter
                 */
                if (selectedObject != null){
                    
                    selectedObject.setIsBeingHeld(true);
                    holdingObject = selectedObject;
                    isHoldingObject = true;
                    selectedCounter.setObjectOnTop(null);
                    
                    /*
                     * if no object on the nearby Counter, check if it is a food counter
                     * if it is, generate a new food according to counter type
                     */
                } else if (selectedCounter instanceof FoodCounter) {
                    MyWorld w = (MyWorld) getWorld();
                    FoodCounter selectedFoodCounter = (FoodCounter)selectedCounter;
                    
                    if (selectedFoodCounter.getType().equals("onion") ) {
                        holdingObject = w.generateOnion();
                    } else if (selectedFoodCounter.getType().equals("tomato") ) {
                        holdingObject = w.generateTomato();
                    } else if (selectedFoodCounter.getType().equals("mushroom") ) {
                        holdingObject = w.generateMushroom();
                    }
                    isHoldingObject = true;
                    selectedCounter.setObjectOnTop(null);
                }
            }
        }
    }
    
    public void setHoldingObject (HoldableObject object) {
        holdingObject = object;
    }
    
}
