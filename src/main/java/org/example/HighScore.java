package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class HighScore {
    private int highScore;
    private boolean newHighScore=false;
    String path;

    public HighScore(){
        this.path=System.getProperty("user.dir")+"\\"+"src\\main\\resources\\highscore.txt";
        this.highScore=loadHighScore();

    }
    public int loadHighScore(){

        File file;
        Scanner sc;

        try {
            file = new File(path);
            sc = new Scanner(file);
        } catch (Exception e) {
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
            File file = new File(path);

            p = new PrintStream(file);
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
