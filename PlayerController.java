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
    boolean isHoldingObject = false;
    private HoldableObject holdingObject = null;
    
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
        
        if ("a".equals(Greenfoot.getKey())){
            holdOrPlaceDownHoldableObject();
            checkIfAddFoodToPot();
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
    
    /*
     * if the player is holding an object, place it down
     * if the player is not holding an object, take or generate one
     */
    public void holdOrPlaceDownHoldableObject() {
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
    
    private void checkIfAddFoodToPot() {
        //do nothing is player is not holding anything
        if (!isHoldingObject || holdingObject == null) return;
    
        // Only food can go into pots
        if (!(holdingObject instanceof Food)) {
            return;
        }
        
        //do nothing is no counter nearby
        Counter selectedCounter = getSelectedCounter();
        if (selectedCounter == null) return;
    
        //food can only be placed inside pot
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        if (!(objectOnCounter instanceof Pot)) return;
    
        Pot selectedPot = (Pot) objectOnCounter;
        
        if (selectedPot.getNumFoodInside() == 0) {
            changeToOneFoodSoup();
            
        } else if (selectedPot.getNumFoodInside() == 1) {
            changeToTwoFoodSoup();
            
        } else if (selectedPot.getNumFoodInside() == 2) {
            changeToThreeFoodSoup();
            
        }

    }
    
    private void removeHoldingObject() {
        MyWorld w = (MyWorld) getWorld();
        w.removeObject(holdingObject);
        holdingObject = null;
        isHoldingObject = false;
    }
    
    private void changeToOneFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        
        if (holdingObject instanceof Mushroom) {
            selectedPot.setType ("mushroom");
            selectedPot.setImage (selectedPot.mushroomSoup[0]);
            selectedPot.setNumFoodInside(1);
            
        } else if (holdingObject instanceof Onion) {
            selectedPot.setType ("onion");
            selectedPot.setImage (selectedPot.onionSoup[0]);
            selectedPot.setNumFoodInside(1);
            
        } else if (holdingObject instanceof Tomato) {
            selectedPot.setType ("tomato");
            selectedPot.setImage (selectedPot.tomatoSoup[0]);
            selectedPot.setNumFoodInside(1);
            
        }
        
        removeHoldingObject();
    }
    
    private void changeToTwoFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        Food holdingFood = (Food) holdingObject;
        
        if ( !holdingFood.getType().equals(selectedPot.getType()) ) return;
        
        if (holdingObject instanceof Mushroom) {
            selectedPot.setImage (selectedPot.mushroomSoup[1]);
            selectedPot.setNumFoodInside(2);
            
        } else if (holdingObject instanceof Onion) {
            selectedPot.setImage (selectedPot.onionSoup[1]);
            selectedPot.setNumFoodInside(2);
            
        } else if (holdingObject instanceof Tomato) {
            selectedPot.setImage (selectedPot.tomatoSoup[1]);
            selectedPot.setNumFoodInside(2);
            
        }
        
        removeHoldingObject();
    }
    
    private void changeToThreeFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        Food holdingFood = (Food) holdingObject;
        
        if ( !holdingFood.getType().equals(selectedPot.getType()) ) return;
        
        if (holdingObject instanceof Mushroom) {
            selectedPot.setImage (selectedPot.mushroomSoup[2]);
            selectedPot.setNumFoodInside(3);
            
        } else if (holdingObject instanceof Onion) {
            selectedPot.setImage (selectedPot.onionSoup[2]);
            selectedPot.setNumFoodInside(3);
            
        } else if (holdingObject instanceof Tomato) {
            selectedPot.setImage (selectedPot.tomatoSoup[2]);
            selectedPot.setNumFoodInside(3);
            
        }
        
        removeHoldingObject();
    }
    
    public void setHoldingObject (HoldableObject object) {
        holdingObject = object;
    }
    
}
