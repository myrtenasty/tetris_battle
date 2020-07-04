import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class tetris_battle extends PApplet {

int Length = 1920;
int Width = 1080;

int turn = 0;
int keyNoA = 0;
int keyNoB = 0;

boolean playA = false;
boolean playB = false;

public void setup(){
    size(Length, Width);
    frameRate(30);
}

public void draw(){
    turn = 1 - turn;
    // keyNoA = 0;
    // keyNoB = 0;
    rect(0, 0, Length, Width);
    println("key is "+key);
    println("keyCode is "+keyCode);
    play();
}
public void keyPressed(){

    if(key == 'a'||key == 's'||key == 'd'||key == 'w'){
        playA = true;
    }
    else if(keyCode == 37||keyCode == 38||keyCode == 39||keyCode == 40){
        playB = true;
    }

    switch(key){
        case 'w':
        keyNoA = 1;
        break;
        case 'a':
        keyNoA = 2;
        break;
        case 's':
        keyNoA = 3;
        break;
        case 'd':
        keyNoA = 4;
        break;
        case CODED:
        switch(keyCode){
            case UP:
            keyNoB = 1;
            break;
            case LEFT:
            keyNoB = 2;
            break;
            case DOWN:
            keyNoB = 3;
            break;
            case RIGHT:
            keyNoB = 4;
            break;
        }
        break;
    }
}

public void keyReleased() {
    if(key == 'a'||key == 's'||key == 'd'||key == 'w'){
        playA = false;
    }
    else if(keyCode == 37||keyCode == 38||keyCode == 39||keyCode == 40){
        playB = false;
    }
}
public void mousePressed(){
    fill(255, 0, 0);
    rect(50, 50, 300, 300);
    fill(255);
}
public void play(){
    if(playA){
        switch(keyNoA){
            case 0:
            break;
            case 1:
            fill(0, 255, 0);
            rect(500, 500, 500, 500);
            break;
            case 2:
            fill(0, 0, 255);
            rect(500, 500, 500, 500);
            break;
            case 3:
            fill(255, 0, 0);
            rect(500, 500, 500, 500);
            break;
            case 4:
            fill(255, 255, 0);
            rect(500, 500, 500, 500);
            break;
        }
    }

    if(playB){
        switch(keyNoB){
            case 0:
            break;
            case 1:
            fill(0, 255, 0);
            rect(1000, 500, 500, 500);
            break;
            case 2:
            fill(0, 0, 255);
            rect(1000, 500, 500, 500);
            break;
            case 3:
            fill(255, 0, 0);
            rect(1000, 500, 500, 500);
            break;
            case 4:
            fill(255, 255, 0);
            rect(1000, 500, 500, 500);
            break;
        }
    } 

    fill(255);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "tetris_battle" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
