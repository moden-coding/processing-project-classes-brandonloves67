import processing.core.PApplet;

public class Obstacle {
    private float x, y; //obstacle position
     private float size; //obstacle size
     private float speed; //obstalce speed
     private PApplet p; //displays on main

    Obstacle(PApplet p, float x, float y, int size, int speed) {
        this.p = p; //stores processing
        this.x = x; //sets startng x 
        this.y = y; //sets starting y
        this.size = size; //sets obstacle size
        this.speed = speed; //sets obstacle speed
    
    }

    public void update() {
        x -= speed; //moves obstacle left
    }
  
    public void display() {
        p.fill(255, 0, 0);  //red obstacle
        p.triangle(x, y, x + size / 2, y - size, x + size, y); //position, each point on triangle
    }
     public boolean isOffScreen() {
        return x + size < 0; // checks if obstacle is off screen
    }
    public boolean checkCollision(Player player){
       float pX = player.getX(); //player x
       float pY = player.getY(); //player y
       float pSize = 40; //size of player
       float obLeft = x; //left side of obstacle
       float obRight = x + size; //right side of obstacle
       float obTop = y - size; //top of obstacle

    return pX + pSize > obLeft && pX < obRight && pY + pSize > obTop; //checks is player hits the obstacle
    }
     public void Speed(float s){
        speed = s; //updates obstacle speed
    }
    public float getX() {
        return x; //x position
    }

    public float getY() {
        return y; //y position
    }
    public float getSize(){
        return size; //size
    }


   

}
