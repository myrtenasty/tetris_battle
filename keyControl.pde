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

void keyReleased() {
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