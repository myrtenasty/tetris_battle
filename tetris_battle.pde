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

void setup(){
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

void draw(){
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