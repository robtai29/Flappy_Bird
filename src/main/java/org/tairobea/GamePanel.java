package org.tairobea;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    boolean gameOver = false;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private Image birdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;
    private Image backgroundImg;

    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;
    private Bird bird;
    int velocity = -10;
    int gravity = 1;

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    int score = 0;

    LinkedList<Pipe> pipes;

    Timer gameLoop;
    Timer placePipesTimer;

    Random random;


    GamePanel(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLUE);
        birdImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/flappybird.png"))).getImage();
        topPipeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/toppipe.png"))).getImage();
        bottomPipeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/bottompipe.png"))).getImage();
        bottomPipeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/bottompipe.png"))).getImage();
        backgroundImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/flappybirdbg.png"))).getImage();
        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImg);
        pipes = new LinkedList<>();
        random = new Random();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        drawBird(g);
        drawPipes(g);
        drawScore(g);
    }

    private void drawScore(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (!gameOver){
            g.drawString("Score: " + String.valueOf(score), 20, 20);
        }else{
            g.drawString("Gameover", 100, 20);
        }
    }

    private void drawPipes(Graphics g) {
        for (Pipe pipe: pipes){
            g.drawImage(pipe.getImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    private void move(){
        velocity += gravity;
        int birdPosY = Math.max(bird.getY() + velocity, 0);
        bird.setY(birdPosY);

        for (Pipe pipe: pipes){
            pipe.setX(pipe.getX() + pipe.getVelocity());
            if (collision(bird, pipe)){
                gameOver = true;
            }

            if (pipe.getX() + pipeWidth <= bird.getX()){
                if (!pipe.isPassed()){
                    score += 10;
                }
                pipe.setPassed(true);
            }
        }

        if (bird.getY() > boardHeight){
            gameOver = true;
        }

        pipes.removeIf(pipe -> pipe.getX() + pipeWidth <= 0);

    }

    public boolean collision(Bird a,  Pipe b){
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    private void placePipes(){
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random() * (pipeHeight /2));
        int openingSpace = boardHeight /4;
        Pipe topPipe = new Pipe(pipeX, randomPipeY, pipeWidth, pipeHeight, topPipeImg);
        pipes.add(topPipe);


        Pipe bottomPipe = new Pipe(pipeX, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImg);
        pipes.add(bottomPipe);
    }


    private void drawBird(Graphics g) {
        g.drawImage(bird.getImg(), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver){
            gameLoop.stop();
            placePipesTimer.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            velocity = -9;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
