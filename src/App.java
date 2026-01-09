import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
  Player player;
  ArrayList<Obstacle> obstacles;
  ArrayList<Coin> coins;

  int score = 0;
  boolean gameOver = false;

  float obstacleTimer = 0;
   float coinTimer = 0;

  float obstacleSpeed = 5;
  float coinSpeed = 5;
  int lastScore = 0;

  float ObstacleSpawnRate = (int)random(50, 100);
  float CoinSpawnRate = (int)random(200, 300);

  public static void main(String[] args) {
    PApplet.main("App");
  }

  public void settings() {
    size(1000, 400);
  }

  public void setup() {
    player = new Player(this, 100, 950);
    obstacles = new ArrayList<Obstacle>();
    coins = new ArrayList<Coin>();

  }

  public void draw() {
    background(30);
    fill(200);
    rect(0, 390, 1000, 10);

    if (!gameOver) {
      player.update();
      player.display();
      showObstaclesAndCoins();
      updateObstaclesAndCoins();
      collision();
      displayScore();
      speedUp();
    } else {
     endScreen();
    }
  }

  public void keyPressed() {
    if (key == ' ') {
      player.jump();
    }
    if (gameOver && key == 'a') {
      resetGame();
    }
  }

  public void showObstaclesAndCoins() {
    obstacleTimer++;
    coinTimer++;

    if (obstacleTimer >= ObstacleSpawnRate ) {
      obstacles.add(new Obstacle(this, 1000, 390, 40, obstacleSpeed));
       obstacleTimer = 0;
         ObstacleSpawnRate = (int) random(60, 120);
    }
    if (coinTimer >= CoinSpawnRate) {
      coins.add(new Coin(this, 1000, 390, 20, coinSpeed));
      coinTimer = 0;
        CoinSpawnRate = (int) random(240, 300);

    }
  }

  public void updateObstaclesAndCoins() {
    for (int i = obstacles.size() - 1; i >= 0; i--) {
      Obstacle ob = obstacles.get(i);
      ob.update();
      ob.display();
      if (ob.isOffScreen())
        obstacles.remove(i);
    }
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i);
      coin.update();
      coin.display();

    }
  }

  public void displayScore() {
    fill(255);
    textSize(24);
    text("Score: " + score, 10, 30);
  }

  public void resetGame() {
    gameOver = false;
    score = 0;
    obstacleSpeed = 5;
    coinSpeed = 5;
    lastScore = 0;
    obstacles.clear();
    coins.clear();
    player = new Player(this, 100, 950);
  }

  public void collision() {
    for (Obstacle ob : obstacles)
      if (ob.checkCollision(player)) {
        gameOver = true;
      }
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i);
      if (coin.checkCollision(player)) {
        coins.remove(i);
        score++;
      }
    }
  }
  public void endScreen(){
      fill(255, 0, 0);
      textSize(48);
      text("Game over", 50, 150);
      textSize(24);
      text("Final score: " + score, 50, 200);
      text("Press a to play again", 50, 250);

  }
  public void speedUp(){
    if (score!=lastScore && score%5==0) {
      coinSpeed+=2;
      obstacleSpeed+=2;
      lastScore=score;
    }
  }
}
