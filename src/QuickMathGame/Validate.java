package QuickMathGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Validate {

    Validate(){
    }

    int DecideValid(long user_id, String pass) {
        //validate from pw.txt
        //0 valid user
        //1 username incorrect or password incorrect
        //2 invalid user

        try {
            BufferedReader pw = new BufferedReader(
                    new FileReader("F:\\NewA\\QuickMathApp\\src\\QuickMathGame\\files\\pw.txt"));

            String valid_id = user_id+ "";
            String valid_id_pw = user_id + ":" + pass;
            String currentLine = pw.readLine();

            while (currentLine != null){
                currentLine = currentLine.trim();
                if (valid_id_pw.equals(currentLine)) {
                    System.out.println("Successful!");
                    return 0;

                } else if (valid_id.equals((currentLine.split(":")[0]))) {
                    System.out.println("Again");
                    return 1;
                }
                currentLine = pw.readLine();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println();
        }
        return 2;
    }
}
