package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.InputMismatchException;

public class Register extends JFrame implements ActionListener {
    Container container=getContentPane();
    JButton back = new JButton("BACK");
    JButton home = new JButton("HOME");
    JLabel useridlabel = new JLabel("PLAYER USER ID IS :");
    JButton useridbutton = new JButton("CLICK TO GET USER ID");
    JLabel namelabel = new JLabel("PLAYER NAME");
    JLabel passlabel = new JLabel("PLAYER PASSWORD");
    JLabel retypepasslabel = new JLabel("RETYPE PASSWORD");
    JTextField nametext = new JTextField();
    JPasswordField passtext = new JPasswordField();
    JPasswordField retypepasstext = new JPasswordField();
    JButton registerbutton = new JButton("REGISTER");
    JButton resetbutton = new JButton("RESET");
    JCheckBox showpasscheckbox = new JCheckBox("SHOW PASSWORD");

    long user_id;

    Register(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void setLocationAndSize(){
        back.setBounds(10,10,150,30);
        home.setBounds(200,10,150,30);
        useridlabel.setBounds(75,50,250,30);
        useridbutton.setBounds(75,90,200,30);
        namelabel.setBounds(75,140,250,30);
        nametext.setBounds(75,180,150,30);
        passlabel.setBounds(75,220,150,30);
        passtext.setBounds(75,260,150,30);
        retypepasslabel.setBounds(75,300,150,30);
        retypepasstext.setBounds(75,340,150,30);
        showpasscheckbox.setBounds(75,380,150,30);
        registerbutton.setBounds(10,420,150,30);
        resetbutton.setBounds(200,420,150,30);
    }
    public void addComponentsToContainer(){
        container.add(back);
        container.add(home);
        container.add(useridlabel);
        container.add(useridbutton);
        container.add(namelabel);
        container.add(passlabel);
        container.add(nametext);
        container.add(passtext);
        container.add(retypepasslabel);
        container.add(retypepasstext);
        container.add(registerbutton);
        container.add(resetbutton);
        container.add(showpasscheckbox);
    }
    public void addActionEvent(){
        back.addActionListener(this);
        home.addActionListener(this);
        useridbutton.addActionListener(this);
        nametext.addActionListener(this);
        passtext.addActionListener(this);
        retypepasstext.addActionListener(this);
        showpasscheckbox.addActionListener(this);
        registerbutton.addActionListener(this);
        resetbutton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            dispose();
            Play frame = new Play();
            frame.setTitle("PLAY");
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
        if(e.getSource() == useridbutton){
            user_id = System.currentTimeMillis()>>1;
            useridbutton.setText(String.valueOf(user_id));
        }
        if(e.getSource() == registerbutton){
            String userpass=passtext.getText();
            String userretypepass=retypepasstext.getText();
            String username=nametext.getText();

            if((userpass.equals(userretypepass)) && !(userpass.equals("")) && !(username.equals("")) && (user_id != 0)){
                try {
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter("F:\\NewA\\QuickMathApp\\src\\QuickMathGame\\files\\pw.txt",true));
                    out.write(user_id + ":"+ userpass+"\n");
                    out.close();

                    String filename = "F:\\NewA\\QuickMathApp\\src\\QuickMathGame\\files\\"+ user_id +".txt";
                    ObjectOutputStream data_out = new ObjectOutputStream(
                            new FileOutputStream(
                                    new File(filename)));
                    PlayerObject po = new PlayerObject(username,user_id,0,0);
                    data_out.writeObject(po);
                    data_out.close();
                }
                catch (IOException | InputMismatchException ioe){
                    JOptionPane.showMessageDialog(this,"USER CREATION FAILED","SORRY",JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("User creation failed");
                }
                JTextArea user = new JTextArea("PLEASE REMEMBER ID AND \nPASSWORD FOR FUTURE LOGIN \nUSER CREATION SUCCEEDED \nUSER ID :  " + String.valueOf(user_id));
                user.setEditable(false);
                JOptionPane.showMessageDialog(this, user,"CONGRATS",JOptionPane.WARNING_MESSAGE);
                System.out.println("User creation success" );

                dispose();
                Home frame = new Home();
                frame.setTitle("Home");
                frame.setVisible(true);
                frame.setBounds(10,10,370,600);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
            }
            else{
                if( !(userpass.equals(userretypepass)) ){
                    JOptionPane.showMessageDialog(this,"PASSWORD DO NOT MATCH","INPUT MISMATCH",JOptionPane.WARNING_MESSAGE);
                }
                if( (user_id == 0) ){
                    useridlabel.setText("PLAYER USER ID IS    * CLICK BUTTON ");
                    useridlabel.setForeground(Color.RED);
                    useridbutton.setForeground(Color.red);
                }
                if(username.equals("")){
                    namelabel.setText("PLAYER NAME   * CANT BE EMPTY");
                    namelabel.setForeground(Color.RED);
                }
                else{
                    namelabel.setText("PLAYER NAME ");
                }
                if(userpass.equals("") || userretypepass.equals("")){
                    JOptionPane.showMessageDialog(this,"PASSWORD CANT BE EMPTY","EMPTY PASSWORD",JOptionPane.WARNING_MESSAGE);
                }

            }
        }
        if(e.getSource() == showpasscheckbox){
            if(showpasscheckbox.isSelected()){
                passtext.setEchoChar((char)0);
                retypepasstext.setEchoChar((char)0);
            }else{
                passtext.setEchoChar('\u2022');
                retypepasstext.setEchoChar('\u2022');
            }
        }
        if(e.getSource() == resetbutton){
            user_id = 0;
            useridlabel.setText("PLAYER USER ID IS :");
            useridlabel.setForeground(Color.BLACK);
            useridbutton.setForeground(Color.BLACK);
            useridbutton.setText("CLICK TO GET USER ID");
            namelabel.setText("PLAYER NAME");
            namelabel.setForeground(Color.BLACK);
            passlabel.setText("PLAYER PASSWORD");
            retypepasslabel.setText("RETYPE PASSWORD");
            nametext.setText("");
            passtext.setText("");
            retypepasstext.setText("");
            showpasscheckbox.setSelected(false);
        }


    }
}
