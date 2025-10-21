import processing.core.*;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main("App");
  }

  float x = 40;
  float y = 460;
  float size = 27;
  float speed = 2;
  float xstart = 60;
  float ystart = 440;
  int deaths, prevDeaths = 0;
  int wins = 0;
  int time = 0;
  boolean left, up, down, right;
  boolean won = false;
  boolean startScreen = true;
  PImage ballImage;
  

  public void setup() {
    ballImage = loadImage("brandon.png");
  }

  public void settings() {
    size(500, 500);
  }

  public void draw() {
    background(0);
    if (startScreen) {
      fill(255);
      textSize(80);
      text("Maze Game", 70, 100);
      textSize(25);
      text("Press 1 to play easy", 90, 150);
      textSize(25);
      text("Press 2 to play hard", 90, 180);
      textSize(18);
      text("Use arrow keys to move",90, 220 );
      return; 
    }
    time += 1;
    background(0);

    fill(255, 0, 0);
    noStroke();
    rect(0, 0, 500, 40);
    rect(0, 0, 40, 500);
    rect(0, 460, 500, 40);
    rect(500 - 40, 0, 40, 500);

    rect(80, 80, 150, 500);
    rect(270, -250, 50, 500);
    rect(230, 290, 120, 500);
    rect(290, 290, 120, 500);
    rect(360, 80, 60, 500);
    fill(0, 255, 0);
    rect(420, 450, 40, 550);
    
    fill(0);
    rect(15, 400, 100, 70);

    imageMode(CENTER);
    image(ballImage, x, y, size, size);
    

    fill(255, 255, 255);
    textSize(45);
    text("wins:" + wins, 200, 400, 100);
    
    if (!won) {
      if (left == true) {
        x -= speed;
      }
      if (up == true) {
        y -= speed;
      }
      if (down == true) {
        y += speed;
      }
      if (right == true) {
        x += speed;
      }
    }
    if (won) {

      fill(255, 255, 255);
      textSize(45);
      text("You won after " + prevDeaths + " deaths!", 25, 250);

      if (time % 300 == 0) {
        won = false;

      }
    }

    int currentColor = get((int) x, (int) y);

    currentColor = get((int) x, (int) (y - size / 2));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }

    currentColor = get((int) x, (int) (y + size / 2));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }

    currentColor = get((int) (x - size / 2), (int) y);
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }

    currentColor = get((int) (x + size / 2), (int) y);
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    currentColor = get((int) x, (int) (y + size / 2));
    if (green(currentColor) == 255 && red(currentColor) == 0 && blue(currentColor) == 0) {
      wins++;
      won = true;
      x = xstart;
      y = ystart;
      prevDeaths = deaths;
      deaths = 0;
    }
  }

  // used chat gpt for only the part where when the ball touches
  // a specific color it does somthing, the green makes a text and
  // red makes ball go back to begining.

 

  public void keyPressed() {
    if (keyCode == UP) {
      up = true;
    } else if (keyCode == DOWN) {
      down = true;
    } else if (keyCode == LEFT) {
      left = true;
    } else if (keyCode == RIGHT) {
      right = true;
    }
       if (startScreen && key == '1') {
      startScreen = false;
      x = xstart;
      y = ystart;
      return;
    }

  }

  public void keyReleased() {
    if (keyCode == UP) {
      up = false;
    } else if (keyCode == DOWN) {
      down = false;
    } else if (keyCode == LEFT) {
      left = false;
    } else if (keyCode == RIGHT) {
      right = false;
    }

  }

}
