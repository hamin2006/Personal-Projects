package flappyBird;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JFrame implements ActionListener, KeyListener, WindowListener  {
    static FlappyBird frame; //instance fields
    graphic graphics;
    Rectangle bird;
    Random rand;
    boolean end,  started, pressed, levelUp, mode = true;
    float volume = -20.0f, pauseVol = -20.0f;
    int ticks,motionBird,level,score,death, minimum_wee;
    final int width = 1920, height = 1080;
    ArrayList<Rectangle> obstacles;
    Clip clip;
    Color color = Color.RED;
    JFrame harsh;
    Timer timer;
    Clip theme;
    FloatControl gainControl;

    public void playSound(String filename, float volume) { //plays a sounds file from the various folder
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(0);

            this.gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public void playTheme(String filename, float volume) { //made only for start theme
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            theme = AudioSystem.getClip();
            theme.open(audioInputStream);
            theme.start();
            theme.loop(Clip.LOOP_CONTINUOUSLY);
            this.gainControl = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        try {
            frame = new FlappyBird();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public FlappyBird() {
        harsh = new JFrame();
        graphics = new graphic();
        timer = new Timer(0,this);
        rand = new Random();
        harsh.add(graphics);
        harsh.addKeyListener(this);
        harsh.addWindowListener(this);
        harsh.setDefaultCloseOperation(harsh.EXIT_ON_CLOSE);
        harsh.setSize(width,height);
        harsh.setResizable(false);
        harsh.setVisible(true);
        bird = new Rectangle(width/2-10,height/2-10,20,20); //setting bird dimensions
        obstacles = new ArrayList<>(); //arraylist containing obstacles
        addObstacle(true); //initializing obstacles
        addObstacle(true);
        addObstacle(true);
        addObstacle(true);
        playTheme("various/Theme.wav",volume); //super cool start theme
        timer.start();
    }
    public void createObstacle(Graphics g, Rectangle obs){ //creates obstacles using dimensions
        Color DARK_GREEN = new Color(0,150,0);
        g.setColor(DARK_GREEN);
        g.fillRect(obs.x, obs.y, obs.width,obs.height);
    }
    public void addObstacle(boolean start){ //adds obstacles to the plane
        int space = 400;
        int width = 100;
        int height = 50 + rand.nextInt(600);//to limit at the top of the screen
        if (start) {
            obstacles.add(new Rectangle(this.width + width + obstacles.size() * 300, this.height - height - 150, width, height)); //if start of game adding new rectangles to obstacles arraylist
            obstacles.add(new Rectangle(this.width + width + (obstacles.size() - 1) * 300, 0, width, this.height - height - space));
        }else{
            obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x +600, this.height - height - 150, width, height));
            obstacles.add(new Rectangle(obstacles.get(obstacles.size()-1).x, 0, width, this.height - height - space));
        }
    }
    public void repaint(Graphics g) { //main graphical display
        if (g instanceof Graphics2D){ //turning on anti aliasing in-order to output a smoother text
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        g.setColor(Color.CYAN); //background
        g.fillRect(0,0,width,height);

        g.setColor(Color.GREEN); //grassground
        g.fillRect(0,height-150,width,150);

        g.setColor(color); //bird
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle obstacle : obstacles){ //enhanced for loop outputting obstacles put in arrayList
            createObstacle(g,obstacle);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri",1,100));
        if (end){
            g.drawString("GAME OVER", this.width/2 -250, this.height/2 - 100);
            g.drawString("Score: "+score, this.width/2 - 150 , this.height/2+100);

        }
        if (!started && !end){
            if (!pressed) {
                g.drawString("PRESS SPACE TO START", this.width / 2 - 500, this.height / 2 - 100);
            }else if (pressed){
                g.drawString("PRESS SPACE TO UNPAUSE", this.width / 2 - 500, this.height / 2 - 100);
            }
        }
        if (!end && started){
            g.drawString(String.valueOf(score), this.width/2 -25, 100);
            if (mode) {
                g.drawString("Level: " + level, this.width / 2 + 500, 100);
            }
        }
        if (!end){
            g.setFont(new Font("Calibri",1,50));
            g.drawString("Press P to Pause", this.width / 2 - 175, height - 100);
        }else if (end){
            g.setFont(new Font("Calibri",1,50));
            g.drawString("Press R to Restart ", this.width / 2 - 175, height - 100);
        }

    }

    public void actionPerformed(ActionEvent e) {
        ticks++;
        if (score == 0) {
            level = 1;
        }
        if (mode) {
            minimum_wee = 5 * level;
        }else{
            minimum_wee = 5 ; //velocity
        }
        if (started) {
            for (int i = 0; i < obstacles.size(); i++) { //obstacle motion
                Rectangle obstacle = obstacles.get(i);
                obstacle.x -= minimum_wee;
            }
            if (ticks % 2 == 0 && motionBird < 15) { //bird motion
                motionBird ++;
            }
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obstacle = obstacles.get(i);
                if (obstacle.x + obstacle.width < 0) {
                    obstacles.remove(obstacle);
                    if (obstacle.y == 0) {
                        addObstacle(false);
                    }
                }
            }
            bird.y += motionBird;

            for (Rectangle obstacle : obstacles) { //collision detection
                if (obstacle.y == 0 && bird.x + bird.width / 2 > obstacle.x + obstacle.width / 2 - minimum_wee && bird.x + bird.width / 2 < obstacle.x + obstacle.width / 2 + minimum_wee && !end) {
                    score++;
                    if (score % 15 == 0 && mode){
                        playSound("various/levelUp.wav",volume);
                        this.level++;
                        levelUp = true;
                    }else{
                        levelUp = false;
                    }
                }
                if (obstacle.intersects(bird)) {
                    end = true;
                    if (bird.x <= obstacle.x) {
                        bird.x = obstacle.x - bird.width;
                    } else {
                        if (obstacle.y != 0) {
                            bird.y = obstacle.y - bird.height;
                        } else if (bird.y < obstacle.height) {
                            bird.y = obstacle.height;
                        }
                    }
                }
            }
            if (bird.y + motionBird>=this.height-150){ //kills bird if he hits the ground
                bird.y = height-150-bird.height;
                end = true;
            }
            if (bird.y <0){ //keeps bird in bounds ends game if bird goes over window
                bird.y = 0;
                end = true;
            }

            if (end){ //made in order to counteract timer
                started = false;
                if (end) {
                    death ++;
                    started = true;
                }
            }
            if (death==1) { //at death play these two sounds
                playSound("various/sfx_hit.wav", volume);
                if (bird.y<this.height-150-bird.height) { //if above ground
                    playSound("various/sfx_die.wav", volume);

                }
            }
        }
        graphics.repaint();
    }
    public void jump(){ //jump function ran when the space bar is pressed jumping the bird
        playSound("various/flap.wav",volume);
        if (end){
            restart();
        }
        if (!started){
            started = true;
        }else if (!end){
            if (motionBird>0){
                motionBird = 0;
            }
            motionBird -= 10;
        }
    }
    public void restart(){ //restart function resetting the whole game
        death = 0;
        bird = new Rectangle(width/2-10,height/2-10,20,20);
        obstacles.clear();
        addObstacle(true);
        addObstacle(true);
        addObstacle(true);
        addObstacle(true);
        score = 0;
        motionBird = 0;
        if (!theme.isActive() && !end) {
            playTheme("various/Theme.wav", volume);
        }else{

        }
        end = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { //all interactive key bindings
        if (e.getKeyCode() == KeyEvent.VK_SPACE){ //space
            pressed = false;

            if (clip!=null && !levelUp) {
                clip.stop();
            }
            if (theme != null){
                theme.stop();
            }
            if (end){
                jump();
            }else {
                jump();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P){ //P
            if (clip !=null) {
                clip.stop();
            }
            if (theme != null){
                theme.stop();
            }
            if (!end){
                started = false;
                pressed = true;
                playSound("various/pause.wav", pauseVol);
                pauseMenu one = new pauseMenu(frame);
                one.setVisible(true);
                one.setResizable(false);
                one.addWindowListener(this);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R){ //R
            end = false;
            started = false;
            restart();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ //Esc
            System.out.println("worjk");
            harsh.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void windowOpened(WindowEvent e) { //if pause window is opened then the game will pause
        end = false;
        started = false;
    }
    @Override
    public void windowClosing(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
