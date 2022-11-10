package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory d = new DefaultTerminalFactory();
        Terminal terminal = d.createTerminal();
        terminal.setCursorVisible(false);


        Controller controller=new Controller(terminal);
        Drawer drawer=new Drawer(terminal);
        HighScore score=new HighScore();
        int col= drawer.getCol();  //x rettning --
        int row = drawer.getRow(); //y rettning |

        int start_x=Math.floorDiv(col,2);
        int start_y=Math.floorDiv(row,2);

        Human human = new Human(start_x, start_y,col,row);
        Zombie zombie= new Zombie(15, 15, human);
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(zombie);


        Platform.startup(() -> {
            Media media = new Media(Main.class.getResource("/ZombieNights.mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }); //adds music
        drawer.plotWelcomeScreen();
        drawer.plotGameLoop(score,human,zombies);

        //starts our main game loop, stops only when break'ed
        mainloop:
        while (true) {

            KeyType kt = controller.getKeyTypeInput();

            human.move(kt);
            for(Zombie zom : zombies){
                zom.move();
            }

            if (kt.equals(KeyType.Escape)) {
                break;
            }

            int winner = determineWinner(human,zombies);
            switch (winner) {
                case 0:
                    drawer.plotGameLoop(score,human,zombies);
                    if ((human.getScore()%40==0) && (human.getScore()>1)){
                        int c= (int) (Math.random()*drawer.getCol());
                        int r= (int) (Math.random()*drawer.getRow());

                        zombies.add(new Zombie(r,c,human));
                    }
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

    public static int determineWinner(Human human,List<Zombie> zombies) {
        for(Zombie zombie :zombies){
            if (zombie.getX() == human.getX() && zombie.getY() == human.getY()) {
                return 2;
            }
        }
        return 0;
    }


}

