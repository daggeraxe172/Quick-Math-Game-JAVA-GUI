package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Home extends JFrame implements ActionListener{

    Container container = getContentPane();
    JLabel home = new JLabel("HOME",JLabel.CENTER);

    JTextArea welcome = new JTextArea("    RAVI KANT 2019503549 WELCOMES \n" +
            "                      YOU TO THE GAME.\n" +
            "              TRY TO ANSWER AS MANY \n" +
            "              QUESTIONS AS POSSBLE \n" +
            "                      IN 60 SECONDS");
    JButton playbutton = new JButton("PLAY");
    JButton scorebutton = new JButton("CHECK SCORE");
    JButton exitbutton = new JButton("EXIT");


    Home(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void setLocationAndSize(){
        home.setBounds(125,20,125,30);
        home.setOpaque(true);
        home.setBackground(Color.BLACK);
        home.setForeground(Color.WHITE);
        welcome.setBounds(75,60,225,90);
        playbutton.setBounds(75,200,225,30);
        scorebutton.setBounds(75,250,225,30);
        exitbutton.setBounds(75,300,225,30);

    }
    public void addComponentsToContainer(){
        container.add(home);
        container.add(welcome);
        container.add(playbutton);
        container.add(scorebutton);
        container.add(exitbutton);
    }
    public void addActionEvent(){
        playbutton.addActionListener(this);
        scorebutton.addActionListener(this);
        exitbutton.addActionListener(this);
        welcome.setEditable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == playbutton){
            dispose();
            Play frame = new Play();
            frame.setTitle("PLAY");
            frame.setVisible(true);
            frame.setBounds(10,10,370,600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
        }
        if(e.getSource() == scorebutton){
            dispose();
            Score frame = new Score();
            frame.setTitle("SCORE");
            frame.setVisible(true);
            frame.setBounds(10,10,370,600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
        }
        if(e.getSource() == exitbutton){
            java.net.URL url = ClassLoader.getSystemResource("QuickMathGame/icons/icon1.jpg");
            ImageIcon icon1 = new ImageIcon(url);
            int option = JOptionPane.showConfirmDialog(this,"Do You Really Want To Quit","Thank you. Have A Nice Day", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon1);
            if(option == JOptionPane.YES_OPTION){
                //System.exit(1);
                dispose();
            }
            System.out.println("Thank you. Have A Nice Day");
        }
    }
}

