package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;


public class Main {

    private static Human human = new Human(10, 10);
    private static Zombie zombie = new Zombie(30, 50, human);
    public static void main(String[] args) throws IOException, InterruptedException {
        d = new DefaultTerminalFactory();
        t = d.createTerminal();
        int[] pos = startp();

        human = new Human(pos[1], pos[0]);
        zombie= new Zombie(30, 50, human);

        getHighscore();
        plotWelcomeScreen();
        plot();

        main:
        while (true) {
            KeyStroke keyStroke = null;
            int x = 10;
            int y = 10;
            final char player = 'X';

            do {
                Thread.sleep(5);
                keyStroke = t.pollInput();
            } while (keyStroke == null);


            KeyType kt = keyStroke.getKeyType();

            human.move(kt);
            zombie.move();

            if (kt.equals(KeyType.Escape)) {
                break main;
            }

            int winner = determineWinner();
            switch (winner) {
                case 0:
                    break;

                case 1:
                    System.out.println("Human wins!");
                    //human wins
                    break main;
                case 2:
                    plotWelcomeScreen();
                    break main;
            }

            t.clearScreen();

            t.setCursorPosition(human.getY(), human.getX());
            t.putCharacter(human.getSymbol());
            t.setCursorPosition(zombie.getY(), zombie.getX());
            t.putCharacter(zombie.getSymbol());

            t.flush();

        }
        t.close();
    }

    public static KeyType getKeyTypeInput() throws InterruptedException, IOException {
        keyStroke = null;
        do {
            Thread.sleep(5);
            keyStroke = t.pollInput();
        } while (keyStroke == null);
        return keyStroke.getKeyType();
    }

    public static void plot() throws IOException {
        t.clearScreen();

        t.setCursorPosition(human.getY(), human.getX());
        t.setForegroundColor(human.getTextColor());
        t.putString(human.getSymbol());

        t.setCursorPosition(zombie.getY(), zombie.getX());
        t.setForegroundColor(zombie.getTextColor());
        t.putString(zombie.getSymbol());

        t.setCursorPosition(0, 0);
        t.setForegroundColor(TextColor.ANSI.GREEN);
        t.putString(human.getScore() > highscore ? "Highscore: " + human.getScore() : "Score: " + human.getScore());

        t.flush();

    }

    public static int determineWinner() {
        if (zombie.getX() == human.getX() && zombie.getY() == human.getY()) {
            System.out.println("donzo");
            return 2;
        } else return 0;
        //there is no way for human to win at this point
    }
}

