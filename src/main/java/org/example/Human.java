package org.example;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;

public class Human {
    private TextColor textColor=TextColor.ANSI.YELLOW;
    private int x;
    private int y;
    private int score;
    private final String symbol = "\uD83E\uDD37\u200D\u200D";

    public Human(int x, int y){
        this.x=x;
        this.y=y;


    }

    public void move(KeyType keyType) {
//        if(keyType==null){
//            return;
//        }

        if (keyType.equals(KeyType.ArrowUp)) {
            x--;
        }
        if (keyType.equals(KeyType.ArrowDown)) {
            x++;
        }
        if (keyType.equals(KeyType.ArrowLeft)) {
            y--;
        }
        if (keyType.equals(KeyType.ArrowRight)) {
            y++;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;

    }

    public TextColor getTextColor(){
        return textColor;
    }
}
