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
  boolean colorScreen = false;
  boolean secretWin=false;
  int newSpeed = 2;
  String difficulty = "";

  int r = 0;
  int g = 0;
  int b = 255;

  public void setup() {

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
      textSize(30);
      text("Press 1 to play", 90, 150);
      textSize(30);
      text("Press 2 to change color", 90, 180);
      fill(255, 0, 0);
      textSize(29);
      text("How to play:", 90, 240);
      textSize(20);
      text("Use arrows to move", 90, 260);
      textSize(20);
      text("Press 's' to speed up, press 'd' to slow down", 90, 280);

      return;
    }
    if (colorScreen) {
      fill(255);
      textSize(50);
      text("Choose Your Color", 60, 100);
      textSize(30);
      text("Press space bar for new color", 70, 150);
      textSize(20);
      text("Press 1 to start game", 80, 180);
      text("Press 3 to return home", 80, 210);
      fill(r, g, b);
      circle(250, 250, size);
      return;
    }
    if (secretWin) {
      background(255, 0, 0);

    textSize(80);
    fill(random(255), random(255), random(255));
    text("BOO!!!", 150, 200);
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

    fill(r, g, b);
    circle(x, y, size);

    fill(255, 255, 255);
    textSize(30);
    text("Press 3 to return home", 120, 450);

    fill(255, 255, 255);
    textSize(45);
    text("wins:" + wins, 200, 400);

    if (newSpeed == 1)
      difficulty = "Slow";
    if (newSpeed == 2)
      difficulty = "Medium";
    if (newSpeed == 3)
      difficulty = "Fast";
    if (newSpeed == 4)
      difficulty = "Extra Fast";

    fill(255);
    textSize(30);
    text("Speed: " + difficulty, 10, 30);

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
        // asked sam fine for some help on this part
      }
    }

    int currentColor = get((int) x, (int) y);
    // top
    currentColor = get((int) x, (int) (y - size / 2 - 1));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    // bottum
    currentColor = get((int) x, (int) (y + size / 2 + 1));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    // side
    currentColor = get((int) (x - size / 2 - 1), (int) y);
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    // side
    currentColor = get((int) (x + size / 2 + 1), (int) y);
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    // corner
    currentColor = get((int) (x - size / 2 - 1), (int) (y - size / 2 - 1));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;

    }
    // corner
    currentColor = get((int) (x + size / 2 + 1), (int) (y - size / 2 - 1));
    if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
      x = xstart;
      y = ystart;
      deaths++;}
      // corner
      currentColor = get((int) (x - size / 2 - 1), (int) (y + size / 2 + 1));
      if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
        x = xstart;
        y = ystart;
        deaths++;

      }
      // corner
      currentColor = get((int) (x + size / 2 + 1), (int) (y + size / 2 + 1));
      if (red(currentColor) == 255 && green(currentColor) == 0 && blue(currentColor) == 0) {
        x = xstart;
        y = ystart;
        deaths++;

      }
      // win
      currentColor = get((int) (x), (int) (y + size / 2 + 1));
      if (green(currentColor) == 255 && red(currentColor) == 0 && blue(currentColor) == 0) {
        wins++;
        won = true;
        x = xstart;
        y = ystart;
        prevDeaths = deaths;
        deaths = 0;
      }
      if (x > 45 && x < 55 && y > 435 && y < 445) { 
    secretWin = true;
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
      colorScreen = false;
      x = xstart;
      y = ystart;
      return;
    }

    if (colorScreen && key == ' ') {
      r = (int) random(0, 255);
      g = (int) random(0, 255);
      b = (int) random(0, 255);
      return;
    }

    if (colorScreen && key == '1') {
      colorScreen = false;
      startScreen = false;
      x = xstart;
      y = ystart;
      return;
    }

    if (key == '3') {
      startScreen = true;
      colorScreen = false;
    }

    if (startScreen && key == '2') {
      startScreen = false;
      colorScreen = true;
      return;
    }
    if (!startScreen && !colorScreen)

    {
      if (key == 's') {
        newSpeed++;
        speed = newSpeed;
        if (newSpeed > 4) {
          newSpeed = 4;
        }
      }
    }

    if (!startScreen && !colorScreen)

    {
      if (key == 'd') {
        newSpeed--;
        speed = newSpeed;
      }
      if (newSpeed < 1) {
        newSpeed = 1;
      }
    }
    if (secretWin=true && key == ' ' ) {
      x = xstart;
      y = ystart;
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
