package org.example;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;

public class Human {
    private TextColor textColor = TextColor.ANSI.YELLOW;
    private int x;
    private int y;

    private int maxY;
    private int maxX;

    private int score;
    private final String symbol = "\uD83E\uDD37\u200D\u200D";

    public Human(int x, int y,int maxX,int maxY) {
        this.x = x;
        this.y = y;
        this.score = 0;
        this.maxX=maxX;
        this.maxY=maxY;
    }

        public void move (KeyType keyType){

            if (keyType.equals(KeyType.ArrowUp)) {
                y--;
            }
            if (keyType.equals(KeyType.ArrowDown)) {
                y++;
            }
            if (keyType.equals(KeyType.ArrowLeft)) {
                x--;
            }
            if (keyType.equals(KeyType.ArrowRight)) {
                x++;
            }
            if (y>maxY){
                y=0;
            } else if (y<0) {
                y=maxY;
            } else if (x>maxX) {
                x=1;
            }else if (x<0){
                x=maxX;
            }

            System.out.printf("x: %d y: %d mx: %d my: %d \n",x,y,maxX,maxY);


        }

        public int getX () {
            return x;
        }

        public int getY () {
            return y;
        }

        public String getSymbol () {
            return symbol;
        }

        public int getScore () {
            return score;
        }

        public void incrementScore () {
            score++;
        }

        public TextColor getTextColor () {
            return textColor;
        }

}
