import processing.core.PApplet;

public class Player {
    private  float x, y; //player position
    private  float speed = 0; //jumping and falling speed
    private  boolean onGround = true; //sees if player is on ground 
    private  PApplet p; //displays on main
    
    
    Player(PApplet p,float x, float y) {
        this.p = p; //stores processing
        this.x = x; //sets x position
        this.y = y; //sets y position
    }
   public void update(){
        speed +=1; //gravity pulls player down
        y += speed; //moves player up or down
      if (y >= 350) { //checks if player hits the ground 
            y = 350; //keeps player on ground
            speed = 0; //stops vertical movemnet
            onGround = true; //allows jumping to happen again
        }
    }
    public void jump(){
           if (onGround) { //only jumps if on ground
            speed = -20; //moves player up
            onGround = false; //prevents double jumping
}}
    public void display() {
        p.fill(0,255,0); //sets players color to green
         p.rect(x, y, 40, 40); //draws the player

    }
    public float getX() {
        return x; //x position
    }

    public float getY() {
        return y; //y position
    }

}


