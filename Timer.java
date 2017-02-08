
public class Timer {
    
    private JTextField jtf;
    
    public Timer(){
        jtf = new JTextField("Monday");
        jtf.setEditable(false);
        this.set();
    }
    
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
    
    public void set(){
        jtf.setBounds(10, 10, 90, 30);
    }
    
    public JTextField getField(){
        return jtf;
    }
    
    public boolean isFriday(){
        return jtf.getText().equals("Friday");
    }
}
