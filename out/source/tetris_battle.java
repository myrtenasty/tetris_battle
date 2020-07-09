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
int globalFreshRate = 15;

boolean playA = false;
boolean playB = false;

boolean spinA = false;
boolean spinB = false;
boolean spinableA = true;
boolean spinableB = true;

blockClass tetrisBlock[] = new blockClass[3];
pressClass control[] = new pressClass[2]; // control[0] for playerA, control[1] for player B

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
    for(int j = 0; j <2; j++){
        control[j] = new pressClass();
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

    for(int c = 0; c <1; c++){
        control[c].timerCount();
        tetrisBlock[c].freshRate = control[c].drop(tetrisBlock[c].freshRate);
        println("block y vel is "+tetrisBlock[c].yvel);
        tetrisBlock[c].xvel = control[c].move(tetrisBlock[c].xvel);
        println("block x vel is "+tetrisBlock[c].xvel);
        if(tetrisBlock[c].ypos >= Width-blockSize*2){
            tetrisBlock[c].xvel = 0;
            tetrisBlock[c].yvel = 0;
            spinableA = false;
            spinableB = false;
        }
    }
    println("block A is under control: "+control[0].on);

    tetrisBlock[0].draw();
    if(control[0].up && spinableA){
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
    int freshRate;


    blockClass(int x, int y){
        xpos = x;
        ypos = y;
        xvel = 0;
        yvel = 1;
        // shape = int(random(7));
        shape = 4;
        // type = int(random(2));
        type = 1;
        dir = PApplet.parseInt(random(4));
        freshRate = globalFreshRate;
    }

    public void draw(){
        if(frameCount%4 == 3){
            xpos = xpos+xvel*blockSize;
        }
        if(frameCount%freshRate == freshRate-1){
            ypos = ypos+yvel*blockSize;
        }
        drawBlock();
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
                    // fill(0);
                    // rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    // fill(255);
                    // rect(xpos-(1-i/4)*blockSize, ypos-(2-i%4)*blockSize, blockSize, blockSize);
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
                    rect(xpos-(i/4)*blockSize, ypos+(1-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 0 && block[i] == 1){
                    fill(255);
                    rect(xpos-(i/4)*blockSize, ypos+(1-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 0){
                    fill(255);
                    rect(xpos-(i/4)*blockSize, ypos+(1-i%4)*blockSize, blockSize, blockSize);
                }
                else if(type == 1 && block[i] == 1){
                    fill(0);
                    rect(xpos-(i/4)*blockSize, ypos+(1-i%4)*blockSize, blockSize, blockSize);
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
        control[0].upPressed();
        break;
        case 'a':
        keyNoA = 2;
        control[0].leftPressed();
        break;
        case 's':
        keyNoA = 3;
        control[0].downPressed();
        break;
        case 'd':
        keyNoA = 4;
        control[0].rightPressed();
        break;
        case CODED:
        switch(keyCode){
            case UP:
            keyNoB = 1;
            spinB = true;
            control[1].upPressed();
            break;
            case LEFT:
            keyNoB = 2;
            control[1].leftPressed();
            break;
            case DOWN:
            keyNoB = 3;
            control[1].downPressed();
            break;
            case RIGHT:
            keyNoB = 4;
            control[1].rightPressed();
            break;
        }
        break;
    }
}

public void keyReleased() {
    if(key == 'a'||key == 's'||key == 'd'||key == 'w'){
        playA = false;
        control[0].release();
    }
    else if(keyCode == 37||keyCode == 38||keyCode == 39||keyCode == 40){
        playB = false;
        control[1].release();
    }

    if(key == 'w'){
        spinableA = true;
    }
    if(keyCode == 38){
        spinableB = true;
    }
}

class pressClass{
    boolean on;
    boolean up;
    boolean down;
    boolean right;
    boolean left;
    int timer;

    pressClass(){
        on = false;
        up = false;
        down = false;
        right = false;
        left = false;
        timer = 0;
    }

    public void timerCount(){
        if(on){
            timer++;
        }
    }

    public void upPressed(){
        on = true;
        up = true;
        down = false;
        right = false;
        left = false;
    }

    public void downPressed(){
        on = true;
        up = false;
        down = true;
        right = false;
        left = false;
    }

    public void rightPressed(){
        on = true;
        up = false;
        down = false;
        right = true;
        left = false;
    }

    public void leftPressed(){
        on = true;
        up = false;
        down = false;
        right = false;
        left = true;
    }

    public void release(){
        on = false;
        up = false;
        down = false;
        right = false;
        left = false;
        timer = 0;
    }

    public int drop(int rate){
        if(!on){
            rate = 15;
        }
        else if(timer >= 10 && down){
            rate = max(2, rate-1);
        }
        return rate;
    }

    public int move(int xvel){
        if(!on){
            xvel = 0;
            println("xvel is "+xvel);
        }
        else if(left){
            xvel = -1;
            println("xvel is "+xvel);
        }
        else if(right){
            xvel = 1;
            println("xvel is "+xvel);
        }
        return xvel;

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
