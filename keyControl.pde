void keyPressed(){

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

void keyReleased() {
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

    void timerCount(){
        if(on){
            timer++;
        }
    }

    void upPressed(){
        on = true;
        up = true;
        down = false;
        right = false;
        left = false;
    }

    void downPressed(){
        on = true;
        up = false;
        down = true;
        right = false;
        left = false;
    }

    void rightPressed(){
        on = true;
        up = false;
        down = false;
        right = true;
        left = false;
    }

    void leftPressed(){
        on = true;
        up = false;
        down = false;
        right = false;
        left = true;
    }

    void release(){
        on = false;
        up = false;
        down = false;
        right = false;
        left = false;
        timer = 0;
    }

    int drop(int rate){
        if(!on){
            rate = 15;
        }
        else if(timer >= 10 && down){
            rate = max(2, rate-1);
        }
        return rate;
    }

    int move(int xvel){
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