package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener{
    Container container=getContentPane();
    JButton back = new JButton("BACK");
    JButton home = new JButton("HOME");
    JLabel useridlabel = new JLabel("TYPE USER ID :");
    JTextField useridtext= new JTextField();
    JLabel passlabel = new JLabel("TYPE PASSWORD : ");
    JPasswordField passtext = new JPasswordField();
    JButton scorebutton = new JButton("DISPLAY");
    JButton resetbutton = new JButton("RESET");
    JCheckBox showpasscheckbox = new JCheckBox("SHOW PASSWORD");
    Score(){
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
        useridlabel.setBounds(75,150,250,30);
        useridtext.setBounds(75,190,150,30);
        passlabel.setBounds(75,230,250,30);
        passtext.setBounds(75,270,150,30);
        showpasscheckbox.setBounds(75,310,150,30);
        scorebutton.setBounds(10,420,150,30);
        resetbutton.setBounds(200,420,150,30);
    }
    public void addComponentsToContainer(){
        container.add(back);
        container.add(home);
        container.add(useridlabel);
        container.add(useridtext);
        container.add(passtext);
        container.add(passlabel);
        container.add(scorebutton);
        container.add(resetbutton);
        container.add(showpasscheckbox);
    }
    public void addActionEvent(){
        back.addActionListener(this);
        home.addActionListener(this);
        useridtext.addActionListener(this);
        passtext.addActionListener(this);
        showpasscheckbox.addActionListener(this);
        scorebutton.addActionListener(this);
        resetbutton.addActionListener(this);
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
        if(e.getSource() == scorebutton){
            String userpass=passtext.getText();

            if(useridtext.getText().equals("")){
                JOptionPane.showMessageDialog(this,"USER ID CAN'T BE EMPTY","WRONG INFO",JOptionPane.WARNING_MESSAGE);
            }
            if(userpass.equals("")){
                JOptionPane.showMessageDialog(this,"USER PASSWORD CAN'T BE EMPTY","WRONG INFO",JOptionPane.WARNING_MESSAGE);
            }

            if( !(useridtext.getText().equals("")) && !(userpass.equals("")) ) {
                long userid;
                try{
                    userid = Long.parseLong(useridtext.getText());

                    int valid_user = new Validate().DecideValid(userid,userpass);

                    if(valid_user==0){
                        dispose();
                        DisplayScore frame = new DisplayScore(userid);
                        frame.setTitle("USER ID : "+userid);
                        frame.setVisible(true);
                        frame.setBounds(10,10,370,600);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setResizable(false);
                    }
                    else if (valid_user == 1)
                    {
                        JOptionPane.showMessageDialog(this,"PASSWORD INCORRECT","WRONG INFO",JOptionPane.INFORMATION_MESSAGE);
                        resetbutton.doClick(500);
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"USER ID AND PASSWORD INCORRECT","WRONG INFO",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        Home frame = new Home();
                        frame.setTitle("HOME");
                        frame.setVisible(true);
                        frame.setBounds(10,10,370,600);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setResizable(false);
                    }
                }catch(Exception ioe){
                    JOptionPane.showMessageDialog(this,"USER ID IS NUMERIC","WRONG INFO",JOptionPane.WARNING_MESSAGE);
                    resetbutton.doClick(500);
                }
            }
        }
        if(e.getSource() == showpasscheckbox){
            if(showpasscheckbox.isSelected()){
                passtext.setEchoChar((char)0);
            }else{
                passtext.setEchoChar('\u2022');
            }
        }
        if(e.getSource() == resetbutton){
            useridlabel.setText("TYPE USER ID :");
            useridtext.setText("");
            passlabel.setText("TYPE PASSWORD : ");
            passtext.setText("");
            showpasscheckbox.setSelected(false);
        }

    }
}
