package QuickMathGame;

import javax.swing.*;

public class Main {
    public static void main(String[] arg){
        Home frame = new Home();
        frame.setTitle("HOME");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
    }
}
