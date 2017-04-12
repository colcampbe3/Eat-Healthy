

import java.awt.Font;

import javax.swing.JLabel;

public class Calendar {

    private JLabel dayLabel;

    public Calendar(){
        dayLabel = new JLabel("Monday");
        dayLabel.setFont(new Font("arial", Font.BOLD, 16));
//        dayLabel.setBorder(new EmptyBorder(5,5,5,5));
//        dayLabel.setBackground(Color.white);
        this.set();
    }
    //constructor, sets timer to monday, calls a function to set the size and locaton

    public void change(){
        if(dayLabel.getText().equals("Monday")){
//            jtf = new JTextField("Tuesday");
            dayLabel.setText("Tuesday");
        }
        else if(dayLabel.getText().equals("Tuesday")){
//            jtf = new JTextField("Wednesday");
            dayLabel.setText("Wednesday");
        }
        else if(dayLabel.getText().equals("Wednesday")){
//            jtf = new JTextField("Thursday");
            dayLabel.setText("Thursday");
        }
        else if(dayLabel.getText().equals("Thursday")){
//            jtf = new JTextField("Friday");
            dayLabel.setText("Friday");
        }
        else if(dayLabel.getText().equals("Friday")){
//            jtf = new JTextField("Monday");
            dayLabel.setText("Monday");
        }
//        jtf.setEditable(false);
//        this.set();
    }
    //increments timer forward by one day and friday back to monday

    public void set(){
        dayLabel.setBounds(10, 10, 90, 30);
    }
    //sets location and size of timer

    public JLabel getField(){
        return dayLabel;
    }
    //method to get underlying text field, so it can be added to frame

    public String getDay(){
        return dayLabel.getText();
    }

    public void setDay(String day){
        dayLabel.setText(day);
    }

    public boolean isFriday(){
        return dayLabel.getText().equals("Friday");
    }
    //checks if it is friday, this is used to reset the fridge each week
}