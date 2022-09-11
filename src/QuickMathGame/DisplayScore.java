package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DisplayScore extends JFrame implements ActionListener{
    Container container =getContentPane();
    JButton back = new JButton("BACK");
    JButton home = new JButton("HOME");
    JLabel useridlabel = new JLabel("PLAYER ID : ");
    JLabel namelabel = new JLabel("PLAYER NAME : ");
    JLabel playedlabel = new JLabel("GAMES PLAYED : ");
    JLabel highestscorelabel = new JLabel("HIGHEST SCORE : ");
    JTextField useridtext = new JTextField("");
    JTextField nametext= new JTextField("");
    JTextField playedtext = new JTextField("");
    JTextField highestscoretext= new JTextField("");
    DisplayScore(long userid){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        displayScore(userid);
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void setLocationAndSize() {
        back.setBounds(10,10,150,30);
        home.setBounds(200,10,150,30);
        useridlabel.setBounds(50, 150, 100, 30);
        useridtext.setBounds(150, 150, 100, 30);
        namelabel.setBounds(50, 200, 100, 30);
        nametext.setBounds(150, 200, 100, 30);
        playedlabel.setBounds(50, 300, 100, 30);
        playedtext.setBounds(150, 300, 100, 30);
        highestscorelabel.setBounds(50, 350, 100, 30);
        highestscoretext.setBounds(150, 350, 100, 30);
    }
    public void addComponentsToContainer(){
        container.add(back);
        container.add(home);
        container.add(useridlabel);
        container.add(namelabel);
        container.add(playedlabel);
        container.add(highestscorelabel);
        container.add(useridtext);
        container.add(nametext);
        container.add(playedtext);
        container.add(highestscoretext);
    }
    public void addActionEvent(){
        back.addActionListener(this);
        home.addActionListener(this);

        useridtext.setEnabled(false);
        nametext.setEditable(false);
        playedtext.setEditable(false);
        highestscoretext.setEditable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == back){
            dispose();
            Score frame = new Score();
            frame.setTitle("SCORE");
            frame.setVisible(true);
            frame.setBounds(10,10,370,600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
        }
        if(e.getSource() == home){
            dispose();
            Home frame = new Home();
            frame.setTitle("HOME");
            frame.setVisible(true);
            frame.setBounds(10,10,370,600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
        }
    }

    void displayScore(long user_id){
        try{
            String filename = "F:\\NewA\\QuickMathApp\\src\\QuickMathGame\\files\\"+ user_id +".txt";
            ObjectInputStream data_in = new ObjectInputStream(
                    new FileInputStream(
                            new File(filename)));
            PlayerObject po1 = (PlayerObject) data_in.readObject();

            useridtext.setText(po1.getuser_id());
            nametext.setText(po1.getName());
            playedtext.setText(po1.getplayed());
            highestscoretext.setText(po1.gethighest_score());

            data_in.close();
        }
        catch(IOException | ClassNotFoundException e){
            e.getStackTrace();
            JOptionPane.showMessageDialog(this,"CANNOT DISPLAY DUE TO EXCEPTIONS","SORRY",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Sorry cant display");
        }
    }
}
