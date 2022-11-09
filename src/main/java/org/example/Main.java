package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;


public class Main {

    private static Human human = new Human(10, 10);
    private static Zombie zombie = new Zombie(30, 50, human);
    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory d = new DefaultTerminalFactory();
        Terminal t = d.createTerminal();

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
                    System.out.println("Zombie wins!");
                    //zombie wins
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

    public static int determineWinner() {
        if (zombie.getX() == human.getX() && zombie.getY() == human.getY()) {
            return 2;
        } else return 0;
        //there is no way for human to win at this point
    }
}

