import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
  Player player;  //the player character
  ArrayList<Obstacle> obstacles; //list of obstacles on screen
  ArrayList<Coin> coins; //list of coins on screen

  int score = 0; //current score
  int highscore = 0; //highest score saved

  boolean gameOver = false; //checks if game is over

  float obstacleTimer = 0; //timer for spawning obstacles
  float coinTimer = 0;  //timer for spawning coins

  int obstacleSpeed = 10; //speed of obstacles
  int coinSpeed = 10; //speed of coins
  int lastScore = 0; //score at the last speed up

  float ObstacleSpawnRate; //how often the obstacles appear 
  float CoinSpawnRate; //how often the coins appear 

  boolean gameStarted=false; //shows its on start screen

 float obstacleSize;  //random obstacle size

   PImage image;  //chatgpt. //background photo
  float imageX = 0; //x position of background

  public static void main(String[] args) {
    PApplet.main("App");
  }

  public void settings() {
    size(1000, 400); //window size
  }

  public void setup() {
     image = loadImage("image.jpeg");  //chatgpt
    player = new Player(this, 100, 950); //creates the player
    obstacles = new ArrayList<Obstacle>(); //creates obstacle list
    coins = new ArrayList<Coin>(); //creates coin list
    readHighscore(); //saves highscore

  }

  public void draw() {
    if (!gameOver) { //only if game is happening
    imageX -= obstacleSpeed * 0.3; //chatgpt //moves background left
    if (imageX <= -width) {
      imageX = 0;//chatgpt //resets background
    }}
    image(image, imageX, 0, width, height);    //chatgpt //draws background
    image(image, imageX + width, 0, width, height); //chatgpt //second image for looping

    fill(0,0, 0); 
    rect(0, 390, 1000, 10);  //draws the ground

    if (!gameStarted) {
      startScreen(); //if game hasnt started display start screen
      return; //so game doesnt run
    }
    if (!gameOver) { //if game is still going 
      player.update(); //update players movement
      player.display(); //draw player
      showObstaclesAndCoins(); //shows obstacles and coins
      updateObstaclesAndCoins(); //moves them
      collision(); //checks for collisions
      displayScore(); //shows score on screen
      speedUp(); //increases speed overtime
    } else {
      endScreen(); //when games over, shows screen
    }
  }

  public void keyPressed() {
    if (key == ' ') {
      player.jump();  //player jumps when space bar is hit
    }
    if (gameOver && key == 'a') {
      resetGame();  //when games over, restarts game when a is hit
    }
    if (!gameStarted && key == ' ' ) {
      gameStarted=true; //when on start screen and space pressed game starts
      return;
    }
  }

  public void showObstaclesAndCoins() {
    obstacleTimer++; //increases obstacle timer
    coinTimer++; //increases coin timer
    if (obstacleTimer >= ObstacleSpawnRate) { 
     int obstacleSize = (int)random(60,100); //random obstacle size
      obstacles.add(new Obstacle(this, 1000, 390, obstacleSize, obstacleSpeed)); //adds obstacles
      obstacleTimer = 0; //resets obstacle timer
      if (score>=10) {
        ObstacleSpawnRate=(int)random(45,60); //faster spawing when score is more than ten
      }else{
      ObstacleSpawnRate = (int) random(60, 120);} //normal spawning
    }
   
    if (coinTimer >= CoinSpawnRate) {
      Coin newCoin = (new Coin(this, 1000, 390, 20, coinSpeed)); //makes coins
     if (!coinOverlapObstacles(newCoin)) { //adds coin if not overlapping obstacles
     coins.add(newCoin); 
    }
      coinTimer = 0;} //resets coin timer
      CoinSpawnRate = (int) random(60, 120); //random coin spawn rate 
  }

  public void updateObstaclesAndCoins() {
    for (int i = obstacles.size() - 1; i >= 0; i--) {
      Obstacle ob = obstacles.get(i); //gets each obstacle
      ob.Speed(obstacleSpeed); //sets the obstacles speed
      ob.update(); //updates/move obstacle
      ob.display(); //shows obstacle
      if (ob.isOffScreen())  
        obstacles.remove(i); //removes if off screen
    }
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i); //gets coin
      coin.Speed(coinSpeed); //sets coin speed
      coin.update(); //update/move coin
      coin.display(); //show coin
      if (coin.isOffScreen())
        coins.remove(i); //removes if off screen
    }
  }

  public void displayScore() {
    fill(255);
    textSize(24);
    text("Score: " + score, 10, 30); //shows score
    text("High score: " + highscore, 390, 30); //shows highscore
    if (score==10 || score == 11) {
      textSize(50);
      text("MORE OBSTATACLES", 200,100); //message
    }
  }

  public void resetGame() {
    gameOver = false; //resets the game
    score = 0; //resets score
    obstacleSpeed = 10; //resets obstacle speed 
    coinSpeed = 10; //resets coin speed 
    lastScore = 0; //resets last score for the speed increase
    obstacles.clear(); //clears obstacles
    coins.clear(); //clears coins
    player = new Player(this, 100, 950); //makes new player
  }

  public void collision() {
    for (Obstacle ob : obstacles)
      if (ob.checkCollision(player)) {
        gameOver = true;  //ends game if player hits obstacle
      }
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i);
      if (coin.checkCollision(player)) {
        coins.remove(i); //removes coin if player hits it
        score++; // score increases
      }
      if (score > highscore) {
        highscore = score; //updates highscore
        saveHighscore(); //saves highscore
      }
    }
  }
  public boolean coinOverlapObstacles(Coin coin){
    for (Obstacle ob:obstacles) {
      if (coin.overlapObstacle(ob)) {
        return true; //if coin is overlapping obstacles
      }
    }
    return false; //no overlap
  }

  public void endScreen() {
    fill(255, 0, 0);
    textSize(48);
    text("Game over", 50, 150); //game over text
    textSize(24);
    text("Final score: " + score, 50, 200); //shows final score
    text("High score: " + highscore, 50, 230); //shows highscore
    text("Press 'a' to play again", 50, 260); // press a to restart

  }
  public void startScreen(){ 
     fill(255); //white
    textSize(60); //text size
    text("SPACE to Jump", 300, 200); //directions
    textSize(30); //text size
     text("Press SPACE to start", 300, 250); //directions
  }

  public void speedUp() {
    if (score != lastScore && score % 5 == 0) {
      coinSpeed += 2; //increases coin speed
      obstacleSpeed += 2; //increases obstacle speed
      lastScore = score; //updates last score so it only speeds up once
    }
  }

  public void saveHighscore() {
    try (PrintWriter writer = new PrintWriter("highscore.txt")) {
      writer.println(highscore); // Writes the integer to the file
      writer.close(); // Closes the writer and saves the file

    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
      e.printStackTrace();
    }
  }

  public void readHighscore() {
    try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {
      // we read the file until all lines have been read
      while (scanner.hasNextLine()) {
        // we read one line
        String row = scanner.nextLine();
        // we print the line that we read
        highscore = Integer.valueOf(row);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

}
