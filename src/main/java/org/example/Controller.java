package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Controller {

    private KeyType keyTypeInput;
    private KeyStroke keyStroke;
    private Terminal t;

    public Controller(Terminal t){
        this.t=t;

    }


    public  KeyType getKeyTypeInput() throws InterruptedException, IOException {

        do {
            Thread.sleep(5);
            keyStroke = t.pollInput();
        } while (keyStroke == null);
        return keyStroke.getKeyType();
    }
}
