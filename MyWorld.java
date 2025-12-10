import greenfoot.*;

public class MyWorld extends World {
    int unitWidth = 80;
    
    public PlayerController player = new PlayerController();
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        addObject(player,836,494);
        
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Counter counter = new Counter();
        addObject(counter,0,0);
        Counter counter2 = new Counter();
        addObject(counter2,668,462);
    }
    
    public void getFood() {
        Food food = new Food();
        addObject(food, player.getX(), player.getY());
    }
}
