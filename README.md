# Set up Greenfoot for VSCode

[Mac]
1. Open `Finder` and navigate to the Applications folder.
2. Right-click on the Greenfoot app and select `Show Package Contents`.
3. In the opened folder, go to `Contents -> Resources -> Java` and find the `greenfoot.jar` file.
4. Copy this `greenfoot.jar` to the `/lib` Folder in this project.

[Windows]
1. Open `File Explorer` and navigate to `C:\Program Files\Greenfoot` and locate the `greenfoot.jar` file.
2. Copy this `greenfoot.jar` to the `\lib` Folder in this project.

<br>
<br>

# project.greenfoot
Copy the `project.greenfoot.md` file and rename it to `project.greenfoot`

# Learn Markdown Basics
[Markdown basics](https://www.markdownguide.org/getting-started/)


# Work update 

**Kelly**
- [ ] Order generate system
  * generagte random soup order with count-down timer bar 

**Abigail**
- [ ] stove system

**Adelina**
- [ ] Asset/background image
  - [ ] Player sprites
      - [ ] animation (cutting)
  - [x] Normal and selected counters (delivery/food/stove/cutting) 
  - [x] Food (mushroom/onion/tomato)
  - [x] Pot (empty/filling/full) 
  - [ ] Plate (empty/
  - [x] Soup order
  - [x] GameWorld background
  - [ ] WelcomeWorld background
  - [ ] Instruction page
  - [ ] Story page
  - [ ] Level page
- [x] Player control
  * player movement using arrow keys
  * player movement is within the PlayerController class, which is an invisible square of width 60
      * it is responsible for checking collisions
  * playerImage is an image following the movement of PlayerController, does not check collisions, allowing the player's head to overlap the counters (appear to be closer)
- [x] Select counter system
  * counters next to the player will "light up"
  * if player is in a corner, the one it is facing will "light up"
- [x] take / generate / put down objects
  * when user presses "a"
      * if player is not next to any counter (no selected counters), nothing will happen
      * if player is holding an object next to a counter with nothing on top, object set location to the position of the counter
      * if player is holding an object next to a counter already with an object on top, player continues to hold the object
      * if player is holding nothing next to a food counter, generate new food according to food counter type

