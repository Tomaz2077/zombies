package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class HighScore {
    private int highScore;
    private boolean newHighScore=false;

    public HighScore(){
        this.highScore=loadHighScore();
    }
    public int loadHighScore(){

        File file;
        Scanner sc;

        try {
            file = new File("src/main/java/org/example/highscore.txt");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int loadedHighScore=0;
        try {
            loadedHighScore= sc.nextInt();
        }catch (Exception ignored){

        }


        sc.close();
        return loadedHighScore;


    }
    public void save(){

        PrintStream p = null;
        try {
            p = new PrintStream(new File("src/main/java/org/example/highscore.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

        System.setOut(p);
        System.out.print(highScore);
    }

    public void setHighScore(Human human){
        if(human.getScore()>highScore){
            highScore=human.getScore();
            newHighScore=true;
        }
    }
    public boolean getNewHighScore(){
        return newHighScore;
    }
    public int getHighScore(){
        return highScore;
    }
}
