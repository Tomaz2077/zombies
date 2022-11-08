package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;


public class Main {
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
            if (kt.equals(KeyType.ArrowUp)) {
                x--;
            }
            if (kt.equals(KeyType.ArrowDown)) {
                x++;
            }
            if (kt.equals(KeyType.ArrowLeft)) {
                y--;
            }
            if (kt.equals(KeyType.ArrowRight)) {
                y++;
            }
            if (kt.equals(KeyType.Escape)) {
                break main;
            }

            t.clearScreen();
            t.setCursorPosition(y, x);
            t.putCharacter(player);
            t.flush();

        }
        t.close();
    }
}

