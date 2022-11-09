package org.example;

import com.googlecode.lanterna.TextColor;

public class Zombie {

    private int x;
    private TextColor textColor = TextColor.ANSI.DEFAULT;

    private int y;

    private long color;

    private Human human;
    private final String symbol ="\uD83D\uDC80";


    public Zombie(int x, int y, Human human) {

        this.x = x;
        this.y = y;
        this.human = human;


    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        int humanX = human.getX();
        int humanY = human.getY();
        double random = Math.random();
        double randomBadMoves = 0.1; //prosenter 10%
        if (random > randomBadMoves) {
            mover(humanX, humanY, 1);
        } else {
            mover(humanX, humanY, -1);
        }

    }

    private void mover(int humanX, int humanY, int k) {

        double random = Math.random();
        if (random < 0.4) {

            if (humanX < x) {
                x = x - k;
            } else if (humanX > x) {
                x = x + k;
            }
        } else if ((random < 0.8)) {

            if (humanY < y) {
                y = y - k;

            } else if (humanY > y) {
                y = y + k;

            }
        } else {

            if (humanY < y) {
                y = y - k;

            } else if (humanY > y) {
                y = y + k;

            }
            if (humanX < x) {
                x = x - k;
            } else if (humanX > x) {
                x = x + k;
            }
        }
    }

    public String getSymbol() {
        return symbol;

    }

    public TextColor getTextColor() {
        return textColor;
    }

}
