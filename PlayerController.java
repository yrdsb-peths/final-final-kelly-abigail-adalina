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
     * "a" to get or place down holdable objects / add food to pot or plate
     * "w" to chop
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
        
        if ("a".equals(Greenfoot.getKey())) {
            holdOrPlaceDownHoldableObject();
            checkIfAddFoodToPot();
            checkIfServeFoodToPlate();
        }
        
        MyWorld w = (MyWorld) getWorld();
        if (Greenfoot.isKeyDown("w")) {
            if (choppingConditionSatisfied()){
                w.playerImage.evokeChoppingAnimation();
                Food cuttingFood = (Food)getSelectedCounter().getObjectOnTop();
                cuttingFood.increaseCurrentCuttingTime();
                if (cuttingFood.hasFinishedChopping()) updateFoodToChoppedVersion();
            }
        }
    }
    
    /**
     * serves food to plate if possible
     */
    private void checkIfServeFoodToPlate() {
        //do nothing is player is not holding anything
        if (!isHoldingObject || holdingObject == null) return;
    
        // Only serve if player is holding an empty plate
        if (!(holdingObject instanceof Plate)) return;
        Plate holdingPlate = (Plate) holdingObject;
        if(! holdingPlate.isEmpty()) return;
        
        //do nothing is no counter nearby
        Counter selectedCounter = getSelectedCounter();
        if (selectedCounter == null) return;
    
        //food can only be placed inside pot
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        if (!(objectOnCounter instanceof Pot)) return;
        //food can only be served when food in pot is done cooking
        Pot selectedPot = (Pot) objectOnCounter;
        if (! selectedPot.hasFinishedCooking()) return;
        
        if(selectedPot.getType().equals("onion")) {
            holdingPlate.setType ("onion");
            holdingPlate.setImageToOnionSoupPlate(); 
        } else if(selectedPot.getType().equals("tomato")) {
            holdingPlate.setType ("tomato");
            holdingPlate.setImageToTomatoSoupPlate(); 
        } else if(selectedPot.getType().equals("mushroom")) {
            holdingPlate.setType ("mushroom");
            holdingPlate.setImageToMushroomSoupPlate(); 
        }
        
        selectedPot.setToEmptyPotStatus();
        holdingPlate.setIsEmpty(false);
    }
    
    /**
     * returns if player will collide with a counter object
     */ 
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
    
    /**
     * return the number of nearby counter objects
     */
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
    
    /**
     * return one selected counter object:
     * if player is next to only one counter, select that counter
     * if player is next to two or more counters(e.g. in a corner),select the one it faces
     */
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
        return getCounterInFront(dir);
    }
    
    /**
     * return the counter object in front of player
     */
    public Counter getCounterInFront(String dir) {
        if (dir.equals("front") ) return (Counter)getOneObjectAtOffset (0, 55, Counter.class);
        else if (dir.equals("back") ) return (Counter)getOneObjectAtOffset (0, -55, Counter.class);
        else if (dir.equals("left") ) return (Counter)getOneObjectAtOffset (-55, 0, Counter.class);
        else if (dir.equals("right") ) return (Counter)getOneObjectAtOffset (55, 0, Counter.class);
        
        return null;
    }
    
    /**
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
    
    /**
     * add food to pot if all conditions satisfied
     */
    private void checkIfAddFoodToPot() {
        //do nothing is player is not holding anything
        if (!isHoldingObject || holdingObject == null) return;
    
        // Only food can go into pots
        if (!(holdingObject instanceof Food)) return;
        
        // only chopped food can go into pots
        Food holdingFood = (Food) holdingObject;
        if(! holdingFood.hasBeenChopped()) return;
        
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
    
    /**
     * remove player holdingObject from world and set player status to not holding objects
     */
    private void removeHoldingObject() {
        MyWorld w = (MyWorld) getWorld();
        w.removeObject(holdingObject);
        holdingObject = null;
        isHoldingObject = false;
    }
    
    /**
     * change pot status to having one food being added
     */
    private void changeToOneFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        
        selectedPot.setRequiredCookingTime (500);
        selectedPot.cookingStatusBar.setMaxVal(500);
        
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
    
    /**
     * change pot status to having two foods being added
     */
    private void changeToTwoFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        Food holdingFood = (Food) holdingObject;
        
        if ( !holdingFood.getType().equals(selectedPot.getType()) ) return;
        
        selectedPot.setRequiredCookingTime (800);
        selectedPot.cookingStatusBar.setMaxVal(800);
        
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
    
    /**
     * change pot status to having three foods being added
     */
    private void changeToThreeFoodSoup() {
        Counter selectedCounter = getSelectedCounter();
        HoldableObject objectOnCounter = selectedCounter.getObjectOnTop();
        Pot selectedPot = (Pot) objectOnCounter;
        Food holdingFood = (Food) holdingObject;
        
        if ( !holdingFood.getType().equals(selectedPot.getType()) ) return;
        
        selectedPot.setRequiredCookingTime (1100);
        selectedPot.cookingStatusBar.setMaxVal(1100);
        
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
    
    /**
     * returns true if player can chop
     */
    public boolean choppingConditionSatisfied() {
        MyWorld w = (MyWorld)getWorld();
        String dir = w.playerImage.getFacingDirection();
        Counter counterInFront = getCounterInFront(dir);
        
        if ( !(counterInFront instanceof CuttingCounter))return false;
        
        if (isHoldingObject) return false;
        
        if ( !(counterInFront.getObjectOnTop() instanceof Food)) return false;
        
        Food foodOnTop = (Food) counterInFront.getObjectOnTop();
        if (foodOnTop.hasBeenChopped()) return false;
        
        return true;
    }
    
    private void updateFoodToChoppedVersion() {
        MyWorld w = (MyWorld)getWorld();
        String dir = w.playerImage.getFacingDirection();
        Counter counterInFront = getCounterInFront(dir);
        Food foodOnTop = (Food) counterInFront.getObjectOnTop();
        
        foodOnTop.setHasBeenChopped(true);
        
        if (foodOnTop.getType().equals("mushroom")) {
            Mushroom mushroom = (Mushroom) foodOnTop;
            mushroom.setImage(mushroom.choppedMushroom);
        } else if (foodOnTop.getType().equals("onion")) {
            Onion onion = (Onion) foodOnTop;
            onion.setImage(onion.choppedOnion);
        } else if (foodOnTop.getType().equals("tomato")) {
            Tomato tomato = (Tomato) foodOnTop;
            tomato.setImage(tomato.choppedTomato);
        }
    }
    
}
