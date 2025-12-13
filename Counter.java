import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    private HoldableObject objectOnTop = null;
    public boolean isSelected = false;
    public Counter(){
    }
    
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
    
    //a super class for its subclasses
    //when is being selected change image to the first parameter
    public void checkIfSelected (GreenfootImage selectedImage, GreenfootImage normalImage){
        MyWorld world = (MyWorld)getWorld();
        Counter selected = world.player.getSelectedCounter();
    
        if (this == selected){
            setImage(selectedImage);
            isSelected = true;
        } else {
            setImage(normalImage);
            isSelected = false;
        }
    }
    
    public HoldableObject getIntersectingHoldableObject() {
        return (HoldableObject)getOneIntersectingObject (HoldableObject.class);
    }
    
    public void setObjectOnTop(HoldableObject object) {
        objectOnTop = object;
    }
    
    public HoldableObject getObjectOnTop() {
        return objectOnTop;
    }
}
