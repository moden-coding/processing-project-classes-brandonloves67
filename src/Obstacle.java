import processing.core.PApplet;

public class Obstacle {
    float x, y;
    float size;
    float speed;
    PApplet p;

    Obstacle(PApplet p, float x, float y, float size, float speed) {
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
        p.fill(255, 0, 0);
        p.triangle(x, y, x + size / 2, y - size, x + size, y);
    }
    public boolean isOffScreen() {
        return x + size < 0;
    }
    public boolean checkCollision(Player player){
        return player.x+40>x && player.x<x+size && player.y +40>y-size && player.y<y;
    }


   

}
