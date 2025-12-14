import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class stoveCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoveCounter extends Counter
{
    private GreenfootImage stoveCounter = new GreenfootImage ("images/stoveCounter.PNG");
    private GreenfootImage selectedStoveCounter = new GreenfootImage ("images/selectedStoveCounter.PNG");
    private GreenfootImage emptyPot = new GreenfootImage("images/emptyPot.PNG");
    private GreenfootImage[] tomatoSoup = new GreenfootImage[3];
    private GreenfootImage[] mushroomSoup = new GreenfootImage[3];
    private GreenfootImage[] onionSoup = new GreenfootImage[3];
    private boolean hasFoodOnTop = false;
    private String soupType = ""; //tomato, mushroom, onion
    private int ingredientCount = -1;
    Pot pot;
    int offset = 15;
    int width = 60;
    
    public StoveCounter() {
        stoveCounter.scale(width, width);
        selectedStoveCounter.scale(width, width);
        emptyPot.scale(width, width);
        setImage (stoveCounter);
        for (int i=0; i<3; i++) {
            tomatoSoup[i] = new GreenfootImage ("images/tomatoSoup/tomatoSoup" + i + ".PNG");
        }
        for (int i=0; i<3; i++) {
            mushroomSoup[i] = new GreenfootImage ("images/mushroomSoup/mushroomSoup" + i + ".PNG");
        }
        for (int i=0; i<3; i++) {
            onionSoup[i] = new GreenfootImage ("images/onionSoup/onionSoup" + i + ".PNG");
        }
    }
    /**
     * Act - do whatever the stoveCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkIfSelected(selectedStoveCounter, stoveCounter);
        
        Actor top = this.getObjectOnTop();
        
            
        if(!hasFoodOnTop && top != null)
        {
            HoldableObject food = (HoldableObject) top;
            
            if(!food.getIsBeingHeld() && !hasFoodOnTop){
            addFood(food);
            hasFoodOnTop = true;
            }
        }
        
        if(top == null)
        {
            hasFoodOnTop = false;
        }
    }
    protected void addedToWorld(World w)
    {
        pot = new Pot();
        pot.setImage(emptyPot);

        // add pot at the stove's position
        w.addObject(pot, getX(), getY()-offset);
    }
    private void addFood(HoldableObject food)
    {
        getWorld().removeObject(food);
        //Decide soup type of first ingerident
        if(soupType.equals(""))
        {
            if(food instanceof Tomato){
                soupType = "tomato";
            } else if(food instanceof Mushroom){
                soupType = "mushroom";
            } else if(food instanceof Onion){
                soupType = "onion";
            }else{
                return;
            }
        }
        //Prevents mixing of ingreident
        if(!isCorrectIngredient(food)){
            return;
        }
        
        //Increase count
        if(ingredientCount < 2){
            ingredientCount ++;
        }
        
        changeImageOfPot();
    }
    private boolean isCorrectIngredient(Actor top){
        if(soupType.equals("tomato") && top instanceof Tomato){
            return true;
        } else if (soupType.equals("mushroom") && top instanceof Mushroom){
            return true;
        } else if(soupType.equals("onion") && top instanceof Onion){
            return true;
        }else{
            return false;
        }
    }
   private void changeImageOfPot(){
        if(soupType.equals("tomato"))
        {
            pot.setImage(tomatoSoup[ingredientCount]);
        }else if(soupType.equals("onion")){
            pot.setImage(onionSoup[ingredientCount]);
        }else if(soupType.equals("mushroom")){
            pot.setImage(mushroomSoup[ingredientCount]);
        }else{
            return;
        }
    }
}
