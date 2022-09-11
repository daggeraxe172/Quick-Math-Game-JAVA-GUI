package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play extends JFrame implements ActionListener{
    Container container = getContentPane();
    JButton back = new JButton("BACK");
    JButton home = new JButton("HOME");
    JLabel registerlabel = new JLabel("FOR FIRST TIME PLAYER");
    JButton registerbutton = new JButton("REGISTER");
    JLabel loginlabel = new JLabel("FOR REGULAR PLAYER");
    JButton loginbutton = new JButton("LOGIN");
    Play(){
        setLayoutManager();
        setLocationSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void setLocationSize(){
        back.setBounds(10,10,150,30);
        home.setBounds(200,10,150,30);
        registerlabel.setBounds(100,150,200,30);
        registerbutton.setBounds(75,200,200,30);
        loginlabel.setBounds(100,300,200,30);
        loginbutton.setBounds(75,350,200,30);
    }
    public void addComponentsToContainer() {
        container.add(loginlabel);
        container.add(loginbutton);
        container.add(registerlabel);
        container.add(registerbutton);
        container.add(back);
        container.add(home);
    }
    public void addActionEvent(){
        loginbutton.addActionListener(this);
        registerbutton.addActionListener(this);
        back.addActionListener(this);
        home.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            dispose();
            Home frame = new Home();
            frame.setTitle("HOME");
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
        if(e.getSource() == registerbutton){
            dispose();
            Register frame1 = new Register();
            frame1.setTitle("REGISTER");
            frame1.setVisible(true);
            frame1.setBounds(10,10,370,600);
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setResizable(false);
        }
        if(e.getSource() == loginbutton){
            dispose();
            Login frame1 = new Login();
            frame1.setTitle("LOGIN");
            frame1.setVisible(true);
            frame1.setBounds(10,10,370,600);
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setResizable(false);
        }
    }
}
