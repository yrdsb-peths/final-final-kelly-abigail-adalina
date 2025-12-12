import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    public boolean checkIfSelected = false;
    public Counter(){
    }
    
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    }
    
    public void checkIfSelected (GreenfootImage selectedImage, GreenfootImage normalImage){
        MyWorld world = (MyWorld)getWorld();
        Counter selected = world.player.getSelectedCounter();
    
        if (this == selected){
            setImage(selectedImage);
            checkIfSelected = true;
        } else {
            setImage(normalImage);
            checkIfSelected = false;
        }
    }
}
