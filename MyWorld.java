import greenfoot.*;

public class MyWorld extends World {
    public int unitWidth = 60;
    int counterOffset = unitWidth/2;
    private GreenfootImage background = new GreenfootImage("images/background.PNG");
    
    public PlayerController player = new PlayerController();
    public PlayerImage playerImage = new PlayerImage();
    
    private int[] slots = {100, 300, 500};
    private int orderTimer = 0;
    private int orderDelay = 180;
    private int lifeSpan = 360;
    
    private Order[] activeOrders = new Order[3];
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        
        background.scale (1000, 600);
        setBackground (background);
        
        setPaintOrder(HoldableObject.class, PlayerImage.class, PlayerController.class, Counter.class);
        
        //set up counter positions and player
        prepare();

        addObject(player,836,494);
        addObject (playerImage, 0, 0);
        
        CuttingCounter cuttingCounter = new CuttingCounter();
        addObject(cuttingCounter, 410, 330);
        
        spawnNextOrder();
    }
    
    public void act()
    {
        orderTimer++;
        
        if (orderTimer >= orderDelay)
        {
            spawnNextOrder();
            orderTimer = 0;
        }
        
        for (int i = 0; i < activeOrders.length; i++)
        {
            if (activeOrders[i] != null)
            {
                activeOrders[i].incrementTimer();
                if (activeOrders[i].getTimer() >= lifeSpan)
                {
                    removeObject(activeOrders[i]);
                    activeOrders[i] = null;
                }
            }
        }
    }
    
    private void spawnNextOrder()
    {
                
        for(int i = 0; i < activeOrders.length; i++)
        {
            if (activeOrders[i] == null)
            {
                Order order = randomOrder();
                addObject(order, slots[i], 30);
                activeOrders[i] = order;
                break;
            }
        }
    }
    
    public Order randomOrder()
    {
        int r = Greenfoot.getRandomNumber(3);
        if (r == 0) 
        {
            return new MushroomSoupOrder();
        } else if (r == 1)
        {
            return new OnionSoupOrder();
        } else
        {
            return new TomatoSoupOrder();
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //first row of counters
        NormalCounter[] counter1 = new NormalCounter[3];
        for (int i=0; i<3; i++) {
            counter1[i] = new NormalCounter();
            addObject(counter1[i],20+(i+1)*unitWidth + counterOffset,2*unitWidth+counterOffset);
        }
        
        FoodCounter counter2 = new FoodCounter("onion");
        addObject(counter2,20+4*unitWidth + counterOffset,2*unitWidth+counterOffset);
        
        FoodCounter counter3 = new FoodCounter ("tomato");
        addObject(counter3,20+5*unitWidth + counterOffset,2*unitWidth+counterOffset);
        
        FoodCounter counter4 = new FoodCounter ("mushroom");
        addObject(counter4,20+6*unitWidth + counterOffset,2*unitWidth+counterOffset);
        
        NormalCounter[] counter5 = new NormalCounter[2];
        for (int i=0; i<2; i++) {
            counter5[i] = new NormalCounter();
            addObject(counter5[i],20+(7+i)*unitWidth + counterOffset,2*unitWidth+counterOffset);
        }
        
        StoveCounter counter6 = new StoveCounter();
        addObject(counter6,20+9*unitWidth + counterOffset,2*unitWidth+counterOffset);
        
        StoveCounter counter7 = new StoveCounter();
        addObject(counter7,20+10*unitWidth + counterOffset,2*unitWidth+counterOffset);
        
        NormalCounter[] counter8 = new NormalCounter[3];
        for (int i=0; i<3; i++) {
            counter8[i] = new NormalCounter();
            addObject(counter8[i],20+(11+i)*unitWidth + counterOffset,2*unitWidth+counterOffset);
        }
        
        // left column of counters
        NormalCounter[] counter9 = new NormalCounter[3];
        for (int i=0; i<3; i++) {
            counter9[i] = new NormalCounter();
            addObject(counter9[i],20+unitWidth+counterOffset,(i+3) * unitWidth + counterOffset);
        }
        
        // second row of counters
        NormalCounter[] counter10 = new NormalCounter[9];
        for (int i=0; i<4; i++) {
            counter10[i] = new NormalCounter();
            addObject(counter10[i],20+(i+2)*unitWidth + counterOffset, 5*unitWidth + counterOffset);
        }
    }
    
    public Onion generateOnion() {
        Onion onion = new Onion();
        addObject(onion, player.getX(), player.getY());
        onion.setIsBeingHeld (true);
        return onion;
    }
    public Mushroom generateMushroom() {
        Mushroom mushroom = new Mushroom();
        addObject(mushroom, player.getX(), player.getY());
        mushroom.setIsBeingHeld (true);
        return mushroom;
    }
    public Tomato generateTomato() {
        Tomato tomato = new Tomato();
        addObject(tomato, player.getX(), player.getY());
        tomato.setIsBeingHeld (true);
        return tomato;
    }
}
