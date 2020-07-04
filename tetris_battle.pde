int Length = 1920;
int Width = 1080;

int turn = 0;
int keyNoA = 0;
int keyNoB = 0;

boolean playA = false;
boolean playB = false;

void setup(){
    size(Length, Width);
    frameRate(30);
}

void draw(){
    turn = 1 - turn;
    // keyNoA = 0;
    // keyNoB = 0;
    rect(0, 0, Length, Width);
    println("key is "+key);
    println("keyCode is "+keyCode);
    play();
}