package QuickMathGame;

import java.io.Serializable;

public class PlayerObject implements Serializable{
    long user_id ;
    String name;
    int played;
    int highest_score;

    PlayerObject(String name, long user_id, int played, int highest_score){
        this.name = name;
        this.user_id = user_id;
        this.played = played;
        this.highest_score = highest_score;
    }
    String getuser_id(){
        return Long.toString(user_id);
    }
    String getName(){
        return name.toUpperCase();
    }
    String getplayed(){
        return Integer.toString(played);
    }
    String gethighest_score(){
        return Integer.toString(highest_score);
    }

}

