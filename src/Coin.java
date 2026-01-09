import processing.core.PApplet;
public class Coin {
    float x, y;
    float size;
    float speed;
    PApplet p;
    boolean collected = false;
     Coin(PApplet p, float x, float y, float size, float speed) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }
     public void update() {
        x -= speed;
    }
      public void display() {
        if (!collected) {
            p.fill(255, 215, 0); 
            p.ellipse(x, y - size / 2, size, size);
        }
    }
    public boolean isOffScreen() {
        return x + size < 0;
    }
    public boolean checkCollision(Player player){
          return !collected && player.x + 40 > x && player.x < x + size &&  player.y + 40 > y - size && player.y < y;
    }



}
