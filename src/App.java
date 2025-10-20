import processing.core.*;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main("App");
  }

  float x = 60;
  float y = 440;
  float size = 40;
  float speed = 10;
  float xstart = 60;
  float ystart= 440;
  int deaths = 0;
  int wins = 0;


  public void setup() {
  }

  public void settings() {
    size(500, 500);
  }

  public void draw(){
      
      background(0);
       fill(0,0,255);
       circle(x, y, size);
       
       fill(255,0,0);
       
       rect(0, 0, 500, 40);       
       rect(0, 0, 40, 500);        
       rect(0, 460, 500, 40);
       rect(500 - 40, 0, 40, 500);
      
       rect(80, 80, 150, 500);
       rect(270, -250, 50, 500);
       rect(230, 290, 120, 500);
       rect(290,290,120,500);
       rect(360,80, 60, 500);
       fill(0,255,0);
      rect(420, 450, 40, 500);
      fill(255,255,255);
      textSize(45);
      text("wins:" + wins,200,35,100);

   
    int currentColor = get((int)x, (int)y);

  currentColor=get((int)x, (int)(y + size/2));
  if (green(currentColor) == 255 && red(currentColor) == 0 && blue(currentColor) == 0) {
       fill(255,255,255);
        textSize(45);
        textAlign(CENTER, CENTER);
        text("You won after " + deaths + " deaths!", 250,250);
        delay(1000);
         x = xstart;
         y = ystart;
        wins++;
    
        
    }

currentColor = get((int)x, (int)(y - size/2));
if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
    x = xstart;
    y = ystart;
    deaths++;
    

}

currentColor = get((int)x, (int)(y + size/2));
if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
    x = xstart;
    y = ystart;
    deaths++;
  
}

currentColor = get((int)(x - size/2), (int)y);
if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
    x = xstart;
    y = ystart;
    deaths++;
    
}

currentColor = get((int)(x + size/2), (int)y);
if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
    x = xstart;
    y = ystart;
    deaths++;
  

    }} 
    // used chat gpt for only the part where when the ball touches 
    //a specific color it does somthing, the green makes a text and 
    //red makes ball go back to begining, but did the rest myself

  public void keyPressed() {
    if (keyCode == UP) {
      y -= speed;
    } else if (keyCode == DOWN) {
      y += speed;
    } else if (keyCode == LEFT) {
      x -= speed;
    } else if (keyCode == RIGHT) {
      x += speed;
    }
  }
}