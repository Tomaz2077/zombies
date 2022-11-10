package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Drawer {

    private Terminal t;
    private TerminalSize terminalSize;
    private int col;
    private int row;

    private Controller controller;
    private KeyType keyType;


    public Drawer(Terminal t) throws IOException {
        this.t = t;
        this.terminalSize = this.t.getTerminalSize();
        this.col = terminalSize.getColumns();
        this.row = terminalSize.getRows();
        this.controller = new Controller(this.t);


    }


    public void plotWelcomeScreen() throws IOException, InterruptedException {
        t.clearScreen();
        t.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
        do {
            t.setCursorPosition(x, y);
            t.putString("Press enter to start");
            t.setCursorPosition(col / 2 -10, row / 2);
            t.putString("Welcome to Zombies");
            t.flush();

            keyType = controller.getKeyTypeInput();
        } while (!keyType.equals(KeyType.Enter));


    }

    public void plotEndScreen(Human human) throws IOException, InterruptedException {

        t.clearScreen();
        t.setForegroundColor(TextColor.ANSI.CYAN);
        do {

            File testcase = new File(System.getProperty("user.dir")+
                    "\\src\\main\\resources\\GameOver.txt");
            Scanner in = new Scanner(testcase);

            int row = 1;
            while (in.hasNextLine()) {
                t.setCursorPosition(20, row++);
                t.putString(in.nextLine());
                t.flush();
            }
            row=row+2;
            t.setCursorPosition(col / 2 - 10, row++);
            t.putString("Your score: " + human.getScore());
            t.setCursorPosition(col / 2 - 10, row / 2 - 1);
            t.putString("Press escape to quit game: ");
            t.flush();

            keyType = controller.getKeyTypeInput();
        } while (!keyType.equals(KeyType.Escape));
    }


    public void plotHuman(Human human) throws IOException {

        t.setCursorPosition(human.getY(), human.getX());
        t.setForegroundColor(human.getTextColor());
        t.putString(human.getSymbol());
    }

    public void plotZombie(Zombie... zombies) throws IOException {

        for (Zombie zombie : zombies) {
            t.setCursorPosition(zombie.getY(), zombie.getX());
            t.setForegroundColor(zombie.getTextColor());
            t.putString(zombie.getSymbol());
        }

    }

    public void plotHighScore(Human human, HighScore score) throws IOException {

        t.setCursorPosition(0, 0);
        t.setForegroundColor(TextColor.ANSI.GREEN);
        t.putString(human.getScore() > score.getHighScore() ? "Highscore: " + human.getScore() : "Score: " + human.getScore());
    }

    public void flush() throws IOException {
        t.flush();
    }

    public void clearScreen() throws IOException {
        t.clearScreen();
    }

    public void plotGameLoop(HighScore highScore, Human human, Zombie... zombie) throws IOException {
        clearScreen();
        plotHuman(human);
        plotZombie(zombie);
        plotHighScore(human, highScore);
        flush();

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


}
