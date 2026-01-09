import processing.core.PApplet;

public class Player {
    float x, y;
    float speed = 0;
    boolean onGround = true;
    PApplet p; 
    
    
    Player(PApplet p,float x, float y) {
        this.p = p;
        this.x = x;
        this.y = y;
    }
   public void update(){
        speed +=1;
        y += speed;
      if (y >= 350) {
            y = 350;
            speed = 0;
            onGround = true;
        }
    }
    public void jump(){
           if (onGround) {
            speed = -20;
            onGround = false;
}}
  public void display() {
        p.fill(0,255,0);
         p.rect(x, y, 40, 40);

    }

}


