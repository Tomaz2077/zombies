package org.example;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory d = new DefaultTerminalFactory();
        Terminal terminal = d.createTerminal();

        Controller controller=new Controller(terminal);
        Drawer drawer=new Drawer(terminal);
        HighScore score=new HighScore();

        int start_y=Math.floorDiv(drawer.getCol(),2);
        int start_x=Math.floorDiv(drawer.getRow(),2);

        Human human = new Human(start_x, start_y);
        Zombie zombie= new Zombie(15, 15, human);


        Platform.startup(() -> {
            Media media = new Media(Main.class.getResource("/ZombieNights.mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }); //adds music
        drawer.plotWelcomeScreen();
        drawer.plotGameLoop(score,human,zombie);

        //starts our main game loop, stops only when break'ed
        mainloop:
        while (true) {

            KeyType kt = controller.getKeyTypeInput();

            human.move(kt);
            zombie.move();

            if (kt.equals(KeyType.Escape)) {
                break mainloop;
            }

            int winner = determineWinner(human,zombie);
            switch (winner) {
                case 0:
                    drawer.plotGameLoop(score,human,zombie);
                    human.incrementScore();
                    break;
                case 1:
                    System.out.println("Human wins!");
                    //human never wins
                    break mainloop;
                case 2:
                    score.setHighScore(human);
                    score.save();
                    drawer.plotEndScreen(human);
                    break mainloop;
            }

        }
        Platform.exit();
        terminal.close();


    }

    public static int determineWinner(Human human,Zombie zombie) {
        if (zombie.getX() == human.getX() && zombie.getY() == human.getY()) {
            return 2;
        } else return 0;
    }


}

