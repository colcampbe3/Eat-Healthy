package eathealthy;

import javax.swing.JOptionPane;

public class DailyGoals {
    int points, cals, sodium;
    float sugar, protein;
    
    
    

    
    DailyGoals(int totalPoints, int totalCals, int totalSodium, float totalSugar, float totalProtein){
        this.points = totalPoints;
        this.cals = totalCals;
        this.sodium = totalSodium;
        this.sugar = totalSugar;
        this.protein = totalProtein;
    
        String popupTxt = "<html><body width='220'>" + "<h1>Daily Stats</h1>"
                    + "<p>Calories: " + cals 
                    + "<p>Points: " + points + "</p>"
                    + "<p>Sodium: " + sodium + "</p>"
                    + "<p>Sugar: " + sugar  + "</p>"
                    + "<p>Protein: " + protein
                    +    "</p>";
        
                    // show how to play dialog on start
                    JOptionPane.showMessageDialog(null, popupTxt);
        
    }}
