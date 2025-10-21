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
  int deaths = 0;
  int wins = 0;
  int time = 0;
  boolean left, up, down, right;

  public void setup() {
  }

  public void settings() {
    size(500, 500);
  }

  public void draw() {

    background(0);
    fill(0, 0, 255);
    circle(x, y, size);

    fill(255, 0, 0);

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
    rect(420, 450, 40, 500);
    fill(255, 255, 255);
    textSize(45);
    text("wins:" + wins, 200, 450, 100);

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

    int currentColor = get((int) x, (int) y);

    currentColor = get((int) x, (int) (y + size / 2));
    if (green(currentColor) == 255 && red(currentColor) == 0 && blue(currentColor) == 0 && time == 0) {
      time = millis();
      wins++;

    }
    if (time > 0) {
      fill(255, 255, 255);
      textSize(45);
      text("You won after " + deaths + " deaths!", 50, 250);
    }
    if (millis()-time > 1000) {
      x = xstart;
      y = ystart;
      time=0;
    }

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
  }
  // used chat gpt for only the part where when the ball touches
  // a specific color it does somthing, the green makes a text and
  // red makes ball go back to begining, but did the rest myself


  

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
