import processing.core.PApplet;
public class Coin {
    private  float x, y; //position of coin
    private  float size; //size of coin
     private float speed; //speed the coin moves
     private PApplet p; //displays to the main 
    private  boolean collected = false; //tracks if coin is collected

     Coin(PApplet p, float x, float y, int size, int speed) {
        this.p = p;  //stores processing
        this.x = x; //sets x position
        this.y = y; //sets y position 
        this.size = size; //coin size
        this.speed = speed; //sets coin speed
    }
     public void update() {
        x -= speed; //moves coin left
    }
      public void display() {
        if (!collected) { //only make if not collected
            p.fill(255, 215, 0); //color to yellow
            p.ellipse(x, y - size / 2, size, size); //draws coin
        }
    }
    public boolean isOffScreen() {
        return x + size < 0; //checks if coin is off screen
    }
    public boolean checkCollision(Player player){
          return !collected && player.getX() + 40 > x && player.getX() < x + size &&  player.getY() + 40 > y - size && player.getY() < y;
    } //checks if player is touching coin
      public void Speed(float s){
        speed = s; //updates coin speed
    }
  public boolean overlapObstacle(Obstacle ob){
       float oX = ob.getX(); //obstacle x position
       float oY = ob.getY(); //obstacle y position
       float oSize = ob.getSize(); //obstacles size
       float coinLeft = x; //left side of coin
       float coinRight = x + size; //right side of coi
       float coinTop = y - size; //top of coin
       float coinBottom = y; //bottom of coin

    return oX + oSize > coinLeft && oX < coinRight && oY + oSize > coinTop &&  coinBottom > oY - oSize;
   //checks if coin overlaps obstacles
    }
  }



