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
int blockSize = 20;

boolean playA = false;
boolean playB = false;

boolean spinA = false;
boolean spinB = false;
boolean spinableA = true;
boolean spinableB = true;

blockClass tetrisBlock[] = new blockClass[3];

int I[] = {1, 1, 1, 1, 0, 0, 0, 0}; // I block
int J[] = {0, 0, 1, 0, 1, 1, 1, 0}; // J block
int L[] = {1, 1, 1, 0, 0, 0, 1, 0}; // L block
int O[] = {0, 1, 1, 0, 0, 1, 1, 0}; // O block
int S[] = {1, 1, 0, 0, 0, 1, 1, 0}; // S block
int T[] = {0, 1, 0, 0, 1, 1, 1, 0}; // T block
int Z[] = {0, 1, 1, 0, 1, 1, 0, 0}; // Z block

public void setup(){
    size(Length, Width);
    frameRate(30);
    for(int i = 0; i < 3; i++){
    int x = 1000;
    int y = 500;
    tetrisBlock[i] = new blockClass(x, y);
}
}

public void draw(){
    turn = 1 - turn;
    // keyNoA = 0;
    // keyNoB = 0;
    fill(255);
    rect(0, 0, Length, Width);
    // println("key is "+key);
    // println("keyCode is "+keyCode);
    // play();
    tetrisBlock[0].drawBlock();
    if((spinA || spinB) && (spinableA||spinableB)){
        tetrisBlock[0].dir = (tetrisBlock[0].dir+1)%4;
        spinableA = false;
        spinableB = false;
        spinA = false;
        spinB = false;
    }
}
class blockClass
{
    int xpos;
    int ypos;
    int xvel;
    int yvel;
    int shape; // define the tetris bolock shape
    int type; // define block type, 0 for moving white, 1 for moving black, 2 for static white, 3 for static black
    int dir; // define the direction of block


    blockClass(int x, int y){
        xpos = x;
        ypos = y;
        xvel = 0;
        yvel = 1;
        shape = PApplet.parseInt(random(7));
        type = PApplet.parseInt(random(2));
        dir = PApplet.parseInt(random(4));
    }

    public void draw(){
        xpos = xpos+xvel;
        ypos = ypos+yvel;
    }

    public void drawBlock(){
        int block[] = new int[8];
        switch(shape){
            case 0:
            for(int j = 0; j < 8; j++){block[j] = I[j];} // I block
            break;
            case 1:
            for(int j = 0; j < 8; j++){block[j] = J[j];} // J block
            break;
            case 2:
            for(int j = 0; j < 8; j++){block[j] = L[j];} // L block
            break;
            case 3:
            for(int j = 0; j < 8; j++){block[j] = O[j];} // O block
            break;
            case 4:
            for(int j = 0; j < 8; j++){block[j] = S[j];} // S block
            break;
            case 5:
            for(int j = 0; j < 8; j++){block[j] = T[j];} // T block
            break;
            case 6:
            for(int j = 0; j < 8; j++){block[j] = Z[j];} // Z block
            break;
        }

        switch(dir){
            case 0:
            for(int i = 0; i < 8; i++){
                noStroke();
                if(type == 0 && block[i] == 0){
                    fill(0);
                    rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    fill(255);
                    rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 1){
                    fill(0);
                    rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
            }
            break;
            case 1:
            for(int i = 0; i < 8; i++){
                noStroke();
                if(type == 0 && block[i] == 0){
                    fill(0);
                    rect(xpos+(1-i%4)*blockSize, ypos-(1-i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos+(1-i%4)*blockSize, ypos-(1-i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    fill(255);
                    rect(xpos+(1-i%4)*blockSize, ypos-(1-i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 1){
                    fill(0);
                    rect(xpos+(1-i%4)*blockSize, ypos-(1-i/4)*blockSize, blockSize, blockSize);
                }
            }
            break;
            case 2:
            for(int i = 0; i < 8; i++){
                noStroke();
                if(type == 0 && block[i] == 0){
                    fill(0);
                    rect(xpos-(i/4)*blockSize, ypos+(i%4-1)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos-(i/4)*blockSize, ypos+(i%4-1)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    fill(255);
                    rect(xpos-(i/4)*blockSize, ypos+(i%4-1)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 1){
                    fill(0);
                    rect(xpos-(i/4)*blockSize, ypos+(i%4-1)*blockSize, blockSize, blockSize);
                }
            }
            break;
            case 3:
            for(int i = 0; i < 8; i++){
                noStroke();
                if(type == 0 && block[i] == 0){
                    fill(0);
                    rect(xpos+(i%4-2)*blockSize, ypos-(i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos+(i%4-2)*blockSize, ypos-(i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    fill(255);
                    rect(xpos+(i%4-2)*blockSize, ypos-(i/4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 1){
                    fill(0);
                    rect(xpos+(i%4-2)*blockSize, ypos-(i/4)*blockSize, blockSize, blockSize);
                }
            }
            break;
        }
    }

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
        spinA = true;
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
            spinB = true;
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

    if(key == 'w'){
        spinableA = true;
    }
    if(keyCode == 38){
        spinableB = true;
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
