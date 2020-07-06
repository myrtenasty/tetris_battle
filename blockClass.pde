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
        shape = int(random(7));
        type = int(random(2));
        dir = int(random(4));
    }

    void draw(){
        xpos = xpos+xvel;
        ypos = ypos+yvel;
    }

    void drawBlock(){
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

