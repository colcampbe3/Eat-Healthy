package eathealthy;

public class Timer {
    
    private JTextField jtf;
    
    public Timer(){
        jtf = new JTextField("Monday");
        jtf.setEditable(false);
        this.set();
    }
    //constructor, sets timer to monday, calls a function to set the size and locaton
    
    public void change(){
        if(jtf.getText().equals("Monday")){
            jtf = new JTextField("Tuesday");
        }
        else if(jtf.getText().equals("Tuesday")){
            jtf = new JTextField("Wednesday");
        }
        else if(jtf.getText().equals("Wednesday")){
            jtf = new JTextField("Thursday");
        }
        else if(jtf.getText().equals("Thursday")){
            jtf = new JTextField("Friday");
        }
        else if(jtf.getText().equals("Friday")){
            jtf = new JTextField("Monday");
        }
        jtf.setEditable(false);
        this.set();
    }
    //increments timer forward by one day and friday back to monday
    
    public void set(){
        jtf.setBounds(10, 10, 90, 30);
    }
    //sets location and size of timer
    
    public JTextField getField(){
        return jtf;
    }
    //method to get underlying text field, so it can be added to frame
    
    public boolean isFriday(){
        return jtf.getText().equals("Friday");
    }
    //checks if it is friday, this is used to reset the fridge each week
}
