package QuickMathGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Random;


public class Game extends JFrame implements ActionListener{
    Container container = getContentPane();

    JLabel questionlabel = new JLabel("QUESTION : ");
    JTextField questiontext = new JTextField();
    JLabel answerlabel = new JLabel("ANSWER : ");
    JTextField answertext = new JTextField();
    JLabel presentscorelabel = new JLabel("PRESENT SCORE : ");
    JTextField presentscoretext = new JTextField();

    int result = 0;
    int score = -1;
    long userid;

    Timer gametimer;
    int start = 20; //GAME TIMER in seconds

    Game(long user_id){
        userid = user_id;

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        result = Calculate();
        Score();
        setTimer();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void setLocationAndSize() {
        questionlabel.setBounds(100, 100, 150, 30);
        questiontext.setBounds(100, 140, 150, 30);
        answerlabel.setBounds(100, 200, 150, 30);
        answertext.setBounds(100, 240, 150, 30);
        presentscorelabel.setBounds(100, 290, 150, 30);
        presentscoretext.setBounds(100, 330, 150, 30);
    }
    public void addComponentsToContainer() {
        container.add(questionlabel);
        container.add(questiontext);
        container.add(answerlabel);
        container.add( answertext);
        container.add(presentscorelabel);
        container.add( presentscoretext);
    }
    public void addActionEvent() {
        questiontext.setEditable(false);
        presentscoretext.setEditable(false);
        answertext.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent evt) {
                super.windowOpened(evt);
                answertext.requestFocus();
            }
        });
    }
    public void setTimer(){
        gametimer = new Timer(1010,this);
        gametimer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e1) {
        start -= 1;
        if(start >= 0){
            try{
                String s = e1.getActionCommand();
                if(result == Integer.parseInt(s)){
                    Score();
                }
                result = Calculate();
                answertext.setText(null);
            }
            catch(Exception e3){
                System.out.println("");
            }
        }else{
            gametimer.stop();
            java.net.URL url2 = ClassLoader.getSystemResource("QuickMathGame/icons/icon2.jpg");
            ImageIcon icon2 = new ImageIcon(url2);
            JOptionPane.showMessageDialog(this,"TIME IS UP. YOUR SCORE IS : " + score ,"SCORE",JOptionPane.PLAIN_MESSAGE,icon2);

            modify(userid,score);

            java.net.URL url3 = ClassLoader.getSystemResource("QuickMathGame/icons/icon2.jpg");
            ImageIcon icon3 = new ImageIcon(url3);
            int option = JOptionPane.showConfirmDialog(this,"DO YOU  WANT TO PLAY AGAIN ?","PLAY AGAIN",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,icon3);

            if(option == JOptionPane.YES_OPTION){
                dispose();
                Game frame = new Game(userid);
                frame.setTitle("USER ID : "+userid);
                frame.setVisible(true);
                frame.setBounds(10,10,370,600);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
            }
            else{
                dispose();
                Home frame = new Home();
                frame.setTitle("HOME");
                frame.setVisible(true);
                frame.setBounds(10,10,370,600);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
            }
        }
    }

    int Calculate(){
        int num1 = new Random().nextInt(11); //1 to 10
        int num2 = new Random().nextInt(11) + 1; //0 to 10

        String operator = "+-/*%";
        int random_operator = new Random().nextInt(5);

        questiontext.setText("(" + num1 + ") " + operator.charAt(random_operator) + " (" + num2 + ")");

        return switch (operator.charAt(random_operator)) {
            case ('+') -> num1 + num2;
            case ('-') -> num1 - num2;
            case ('*') -> num1 * num2;
            case ('/') -> num1 / num2;
            case ('%') -> num1 % num2;
            default -> 0;
        };
    }
    void Score(){
        score += 1;
        presentscoretext.setText(" " +score + " ");
    }
    void modify(long user_id,int score) {
        //save to file named id.txt where id is user id
        try {
            String filename = "F:\\NewA\\QuickMathApp\\src\\QuickMathGame\\files\\"+ user_id +".txt";

            ObjectInputStream data_in = new ObjectInputStream(
                    new FileInputStream(
                            new File(filename)));
            PlayerObject po1 = (PlayerObject) data_in.readObject();
            data_in.close();

            po1.played += 1;
            if(po1.highest_score < score) {
                po1.highest_score = score ;
            }

            ObjectOutputStream data_out = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(filename)));
            data_out.writeObject(po1);

            data_out.close();

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,"SCORE DID NOT SAVE. TRYING AGAIN...","SAVE",JOptionPane.INFORMATION_MESSAGE);
            modify(user_id,score);
        }
    }
}
