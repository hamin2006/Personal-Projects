package flappyBird;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class pauseMenu extends JFrame implements KeyListener{
    JPanel panel1 = new JPanel(); //panels
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    FlappyBird h; //local object
    boolean on = true, on2 = true;
    public pauseMenu(FlappyBird h){ //object from main game which can edit the aspects inside the main game //constructor
        this.h = h;
        addKeyListener(this); //setting up window
        setFocusable(true);
        setTitle("Paused"); //frame setup
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel1.setLayout(null);
        panel1.setBackground(Color.CYAN);
        setContentPane(panel1); //inital contentPane

        //panel 1
        JLabel paused = new JLabel("You Are Currently Paused");
        paused.setFont(new Font("Calibri",1,100));
        paused.setBounds(1920/2 - 500, 200, 1500, 100);

        JLabel gameMode = new JLabel();
        if (h.mode){
            gameMode.setText("Game Mode: Progressive");
        }else{
            gameMode.setText("Game Mode: Classic");
        }
        gameMode.setBounds(820, 1080/2+400,300,75);
        gameMode.setFont(new Font("Calibri",1,25));

        Button instructions = new Button("INSTRUCTIONS");
        Button play = new Button("PLAY");
        instructions.setBounds(500, 1080/2-75,200,75);

        play.setBounds(1920-700, 1080/2-75,200,75);
        panel1.add(play);
        Button Progress = new Button("PROGRESSIVE MODE");
        Button classic = new Button("CLASSIC MODE");

        classic.setForeground(Color.RED);
        Progress.setForeground(Color.BLUE);
        classic.setBounds(700, 100,200,75);
        Progress.setBounds(1000, 100,200,75);

        Button restart = new Button("RESTART");
        restart.setBounds(860, 1080/2+100,200,75);

        Button custom = new Button("CUSTOMIZE");
        custom.setBounds(860, 1080/2-200,200,75);

        CheckboxGroup G = new CheckboxGroup();
        Checkbox ON = new Checkbox("On", G, on);
        Checkbox OFF = new Checkbox("Off", G, !on);
        JLabel music = new JLabel("Pause Menu Music:");
        music.setBounds(1650, 50, 200,10);
        ON.setBounds(1775, 75, 200,20);
        OFF.setBounds(1775, 25, 200,20);
        ON.setFont(new Font("Calibri",1,25));
        OFF.setFont(new Font("Calibri",1,25));
        panel1.add(ON);
        panel1.add(OFF);
        panel1.add(music);
        panel1.add(paused);
        panel1.add(gameMode);
        panel1.add(classic);
        panel1.add(Progress);
        panel1.add(instructions);
        panel1.add(restart);
        panel1.add(custom);
        ON.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (!h.clip.isActive()) {
                    h.clip.start();
                }
                on = true;
            }
        });
        OFF.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (on) {
                    if (h.clip.isActive()) {
                        h.clip.stop();
                    }
                    on = false;
                }
            }
        });
        CheckboxGroup G2 = new CheckboxGroup();
        if (h.volume == -80.0f){
            on2 = false;
        }else if (h.volume == -20.0f){
            on2 = true;
        }
        Checkbox ON2 = new Checkbox("On", G2, on2);
        Checkbox OFF2 = new Checkbox("Off", G2, !on2);
        JLabel music2 = new JLabel("Game Music/SFX:");
        music2.setBounds(50, 50, 200,10);
        ON2.setBounds(200, 75, 200,20);
        OFF2.setBounds(200, 25, 200,20);
        ON2.setFont(new Font("Calibri",1,25));
        OFF2.setFont(new Font("Calibri",1,25));
        panel1.add(ON2);
        panel1.add(OFF2);
        panel1.add(music2);
        ON2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                h.volume = -20.0f;
                on2 = true;
            }
        });
        OFF2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                h.volume = -80.0f;
                on2=false;
            }
        });

        //panel 2
        panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel2.setLayout(null);
        panel2.setBackground(Color.CYAN);
        JLabel intro = new JLabel("Instructions");
        intro.setFont(new Font("Calibri",1,100));
        intro.setBounds(1920/2-260, 60, 500, 110 );
        panel2.add(intro);
        Button back = new Button("Back");
        back.setBounds(1920/2 - 100, 950,200,75);
        panel2.add(back);
        JTextArea instr = new JTextArea();
        instr.setBounds(1920/2-370, 130, 950, 820);
        instr.setBackground(Color.CYAN);
        instr.setFont(new Font("Calibri",1,40));
        instr.setText("Controls: \n        " +
                "Press space to make the bird flap \n        " +
                "Press P to enter the pause menu\n        " +
                "Press R to Restart the game\n        " +
                "Press Esc to Close\n" +
                "GamePlay: \n        " +
                "Initially, the speed will be slow to allow \n        " +
                "you to become familiar with the game. \n        " +
                "As you progress through the game, levels \n        " +
                "will increase every 15 pillars and with \n        " +
                "each increase the bird will travel faster, \n        " +
                "challenging your reaction time.\n        " +
                "And of course don't touch the pillars\n        " +
                "or try to go over them\n" +
                "Restart: \n        " +
                "Once dead press space to restart \n");
        instr.append("                   GOOD LUCK AND SET A HIGH SCORE");
        instr.setEditable(false);
        panel2.add(instr);

        //panel 3
        panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel3.setLayout(null);
        panel3.setBackground(Color.CYAN);
        Button back2 = new Button("Back");
        back2.setBounds(1920/2 - 100, 950,200,75);
        panel2.add(back2);
        panel3.add(back2);
        JLabel customize = new JLabel("Customize");
        customize.setFont(new Font("Calibri",1,100));
        customize.setBounds(1920/2-200, 60, 500, 110 );
        panel3.add(customize);
        JLabel change = new JLabel("Choose a Bird Colour");
        change.setFont(new Font("Calibri",1,30));
        change.setBounds(1920/2-140, 200, 500, 110 );
        panel3.add(change);
        JLabel color = new JLabel();
        String bird = null;
        if (h.color == Color.BLUE){
            bird = "Blue";
        }else if (h.color == Color.yellow){
            bird = "Yellow";
        }else if (h.color == Color.ORANGE){
            bird = "Orange";
        }else if (h.color == Color.RED){
            bird = "Red";
        }else if (h.color == Color.BLACK){
            bird = "Black";
        }else if (h.color == Color.MAGENTA){
            bird = "Pink";
        }
        color.setText("Bird Colour: " + bird);
        color.setBounds(820, 1080/2+250,300,75);
        color.setFont(new Font("Calibri",1,25));
        panel3.add(color);
        Button blue = new Button("BLUE");
        Button yellow = new Button("YELLOW");
        Button orange = new Button("ORANGE");
        Button red = new Button("RED");
        Button black = new Button("BLACK");
        Button pink = new Button("PINK");
        int mid = 100;
        blue.setBounds(mid, 1080/2-150,200,75);
        yellow.setBounds(mid+300, 1080/2-150,200,75);
        orange.setBounds(mid+600, 1080/2-150,200,75);
        red.setBounds(mid+900, 1080/2-150,200,75);
        black.setBounds(mid+1200, 1080/2-150,200,75);
        pink.setBounds(mid+1500, 1080/2-150,200,75);
        panel3.add(blue);
        panel3.add(yellow);
        panel3.add(orange);
        panel3.add(red);
        panel3.add(black);
        panel3.add(pink);


        //buttons
        play.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                returnBack();
            }
        });
        classic.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                h.mode = false;
                gameMode.setText("Game Mode: Classic");
            }
        });
        Progress.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                h.mode = true;
                gameMode.setText("Game Mode: Progressive");
            }
        });
        instructions.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel1.setVisible(false);
                panel2.setVisible(true);
                setContentPane(panel2);
            }
        });
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel2.setVisible(false);
                panel1.setVisible(true);
                setContentPane(panel1);
            }
        });
        back2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel3.setVisible(false);
                panel1.setVisible(true);
                setContentPane(panel1);
            }
        });
        custom.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel1.setVisible(false);
                panel3.setVisible(true);
                setContentPane(panel3);

            }
        });
        restart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                h.pressed = false;
                h.restart();
                h.clip.stop();
                returnBack();
            }
        });
        blue.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Blue");
                h.color = Color.BLUE;
            }
        });
        yellow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Yellow");
                h.color = Color.YELLOW;
            }
        });
        orange.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Orange");
                Color orangeActual = new Color(255, 137, 0); //awt orange looked more yellow
                h.color = orangeActual;
            }
        });
        red.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Red");
                h.color = Color.RED;
            }
        });
        black.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Black");
                h.color = Color.BLACK;
            }
        });
        pink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                color.setText("Bird Colour: Pink");
                h.color = Color.MAGENTA;
            }
        });
    }
    public void returnBack(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            h.harsh.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            System.out.println("ended");
        }
    }
}
