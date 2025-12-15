import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeliveryCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeliveryCounter extends Counter
{
    private GreenfootImage deliveryCounter = new GreenfootImage ("images/deliveryCounter.PNG");
    private GreenfootImage selectedDeliveryCounter = new GreenfootImage ("images/selectedDeliveryCounter.PNG");
  
    int width = 120;
    public DeliveryCounter() {
        deliveryCounter.scale (width, width);
        selectedDeliveryCounter.scale (width, width);
    }
    
    public void act()
    {
        // Add your action code here.
        checkIfSelected(selectedDeliveryCounter, deliveryCounter);
    }
}
