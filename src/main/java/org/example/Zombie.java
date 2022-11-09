package org.example;

public class Zombie {

    private int x;

    private int y;

    private final char symbol = '@';
    private Human human;

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

        if (random < 0.4) {

            if (humanX < x) {
                x--;
            } else if (humanX > x) {
                x++;
            }
        } else if ((random < 0.8)) {

            if (humanY < y) {
                y--;

            } else if (humanY > y) {
                y++;

            }
        }else {

            if (humanY < y) {
                y--;

            } else if (humanY > y) {
                y++;

            }
            if (humanX < x) {
                x--;
            } else if (humanX > x) {
                x++;
            }
        }

    }

    public char getSymbol() {
        return symbol;

    }

}
