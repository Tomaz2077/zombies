package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {


    private static int[] startp() {
        TerminalSize tz;
        try {
            tz = t.getTerminalSize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int col = tz.getColumns();
        int row = tz.getRows();

        int[] pos = new int[]{Math.floorDiv(col, 2), Math.floorDiv(row, 2)};
        return pos;
    }

    private static Human human;
    private static Zombie zombie;
    private static int highscore;
    private static KeyStroke keyStroke;

    private static DefaultTerminalFactory d;
    private static Terminal t;

    public static void main(String[] args) throws IOException, InterruptedException {
        d = new DefaultTerminalFactory();
        t = d.createTerminal();
        int[] pos = startp();

        human = new Human(pos[1], pos[0]);
        zombie= new Zombie(30, 50, human);

        getHighscore();
        plotWelcomeScreen();
        plot();

        mainloop:
        while (true) {

            KeyType kt = getKeyTypeInput();

            human.move(kt);
            zombie.move();

            if (kt.equals(KeyType.Escape)) {
                break mainloop;
            }

            int winner = determineWinner();
            switch (winner) {
                case 0:
                    plot();
                    human.incrementScore();
                    break;
                case 1:
                    System.out.println("Human wins!");
                    //human wins
                    break mainloop;
                case 2:
                    break mainloop;
            }

        }
        plotEndScreen();
        t.close();
        setHighscore();
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

    public static void getHighscore() {
        File file;
        Scanner sc;

        try {
            file = new File("src/main/java/org/example/highscore.txt");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        highscore = sc.nextInt();
        System.out.println(highscore);

    }

    public static void setHighscore() {

        if (human.getScore() > highscore) {
            PrintStream p = null;
            try {
                p = new PrintStream(new File("src/main/java/org/example/highscore.txt"));
            } catch (FileNotFoundException e) {
                System.err.println("File not found!");
            }

            System.setOut(p);
            System.out.print(human.getScore());
        }


    }

    public static void plotWelcomeScreen() throws IOException, InterruptedException {
        KeyType kt;
        TerminalSize ts = t.getTerminalSize();
        t.clearScreen();
        do {
            t.setCursorPosition(0, 1);
            t.putString("Press enter to start");
            t.setCursorPosition(ts.getColumns() / 2, ts.getRows() / 2);
            t.putString("Welcome to Zombies");


            kt = getKeyTypeInput();
        } while (!kt.equals(KeyType.Enter));
        t.flush();

    }

    public static void plotEndScreen() throws IOException, InterruptedException {

        KeyType kt;
        TerminalSize ts = t.getTerminalSize();

        t.clearScreen();
        t.setCursorPosition(0, 1);


        /*if (kt.equals(KeyType.Escape)) {
            System.out.println("Game will now close! ByeBye sore loser!");
        }
        String str = "Press escape to quit game";
        System.out.println(str);*/


      /*  do {
            String str ="Press escape to quit game";
            t.setCursorPosition(1, 1);
            t.putString(str);

            kt = getKeyTypeInput();
        } while (!kt.equals(KeyType.Escape));*/
        t.setCursorPosition(0, 1);
        t.putString("Game will now close! ByeBye sore loser!");

        t.flush();

    }
}

