void play(){
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