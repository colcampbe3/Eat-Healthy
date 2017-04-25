package eathealthy;

import javax.swing.JOptionPane;

public class HighScore{
    
    int weekPoints = 0;
    int highScore = 0;
    int totalScore = 0;
    

    
    HighScore(int totalPoints){
        this.weekPoints = totalPoints;
        
                String popupTxt = "<html><body width='220'>" + "<h1>High Score</h1>"
                    + "<p>Highest Score: " + totalPoints
                    +    "</p>";
        
                    // show how to play dialog on start
                    JOptionPane.showMessageDialog(null, popupTxt);

            }

   
        public void setWeeklyHigh(int score) {
        if (score > weekPoints) {
            weekPoints = score;
        }
    }
        
    public int getWeeklyHigh() {
        return this.weekPoints;
    }
    
    public void setTotalScore(int score) {
        this.totalScore = score;
    }
    
    public void addTotalScore(int score) { 
        this.totalScore = this.totalScore + score;
    }
    
    public int getTotalScore() {
        return this.totalScore;
    }
    
    public void setWeeklyScore(int score) { //directly change the total
        this.weekPoints = score;
    }

    public void addWeeklyScore(int score) { //increment the total by the amount specified
        this.weekPoints = this.weekPoints + score;
    }

    public int getWeeklyScore() {
        return this.weekPoints;
    }



}

