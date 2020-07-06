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

void setup(){
    size(Length, Width);
    frameRate(30);
    for(int i = 0; i < 3; i++){
    int x = 1000;
    int y = 500;
    tetrisBlock[i] = new blockClass(x, y);
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
    tetrisBlock[0].drawBlock();
    if((spinA || spinB) && (spinableA||spinableB)){
        tetrisBlock[0].dir = (tetrisBlock[0].dir+1)%4;
        spinableA = false;
        spinableB = false;
        spinA = false;
        spinB = false;
    }
}