
   
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EatHealthy {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = WIDTH / 12 * 9 + 20; // aspect ratio 12:9

    private JPanel guiPanel, gamePanel;
    private Handler handler;
    private Menu menu;

    private JButton addButton, removeButton, packLunchButton, randomButton, testSave, testReturn;
    private ListBox fridge, lunchBox;
    private Calendar day;
    private Window window;
    private JLabel cal, pts, cap, counter, goal, scoreBoard, highScore;
    private User profile;
//    private boolean newGame = false;

    private int calGoal = 1200;

    public static void main(String[] args) {

        // Sets theme
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // initializes a profile to avoid a null reference
        User profile = new User("", 0, 0, false);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EatHealthy().start(profile);
            }
        });

    }

    /*
     * Method that starts the game by initializing frame and all components
     */
    public void start(User newProfile) { //Receives User profile from UserInfo.java -R

        // creates all food objects
        this.profile = newProfile;
        handler = new Handler(this);

        FoodAssets.init();

        // Creates window to hold all components
        window = new Window(WIDTH, HEIGHT, "Eat Healthy");
        fridge = new ListBox(WIDTH - ListBox.DEFAULT_WIDTH - 10, 50, ListBox.DEFAULT_WIDTH, ListBox.DEFAULT_HEIGHT,
                "FRIDGE");
        lunchBox = new ListBox(10, 50, ListBox.DEFAULT_WIDTH, 190, "LUNCH BOX");
        lunchBox.setMaxCapacity(5);

        day = new Calendar();
        
        //points panel initialization
        JPanel z = new JPanel();
        z.setBackground(Color.ORANGE);
        scoreBoard = new JLabel("SCORE: " + profile.getWeeklyScore());
        highScore = new JLabel("HIGHSCORE: " + profile.getWeeklyHigh());
        z.setLayout(new BoxLayout(z, BoxLayout.Y_AXIS));
        z.setBorder(new EmptyBorder(4, 4, 4, 4));
        z.add(scoreBoard);
        z.add(highScore);
        z.setBounds(300, 5, 380, 60);
        // creates calorie counter
        JPanel p = new JPanel();
        counter = new JLabel("<HTML><U>Nutrition</U><HTML>", SwingConstants.CENTER);
        goal = new JLabel("Calorie Goal: " + calGoal);
        cal = new JLabel("Calories: " + lunchBox.getTotalCal());
        pts = new JLabel("Points: " + lunchBox.getTotalPoints((double)profile.getWeight(),profile.getAge(), profile.getSex())); //Passes values to be used in calculation -R
        cap = new JLabel("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());                           //This happens any time points are calculated.
        cap.setBackground(Color.GREEN);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(4, 4, 4, 4));
        p.add(counter);
        p.add(goal);
        p.add(cal);
        p.add(pts);
        p.add(cap);
        
        guiPanel = new JPanel(handler.getLayout());
        menu = new Menu(WIDTH, HEIGHT, handler);

        gamePanel = new JPanel();

        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setLayout(null);
        gamePanel.setOpaque(false); // sets background invisible

        // attach components to panel
        gamePanel.add(p);
        gamePanel.add(day.getField());
        gamePanel.add(fridge);
        gamePanel.add(lunchBox);
        //points
        gamePanel.add(z);

        createButtons();

        guiPanel.add(menu, State.MENU.toString());
        guiPanel.add(gamePanel, State.GAME.toString());
        guiPanel.setOpaque(false);

        window.attach(guiPanel);
        handler.changeGameState(State.MENU); // sets menu as starting game state

        // sets size & positioning for GUI component
        p.setBounds(removeButton.getX() - 10, removeButton.getY() + removeButton.getHeight() + 4, 140, 90);

        // must be called last or components won't be displayed
        window.getFrame().setVisible(true);
    }

    public void displayGameHelp(){
        String popupTxt = "<html><body width='220'>" + "<h1>Eat Healthy</h1>"
                + "<p>Welcome " + profile.getName() + " to Eat Healthy. A game that simulates packing a lunch.</p><br>"
                + "<p><u>How To Play:</u> &nbsp&nbsp Pick foods from the list on the right to pack into the lunch box."
                + " Try picking an assortment of healthy foods to score big.</p>";

        // show how to play dialog on start
        JOptionPane.showMessageDialog(null, popupTxt);
    }

    public void createNewGame(User user){
        setProfile(user);
        fridge.fillRandom();
        lunchBox.clearList();
        setDay("Monday");
        updateCalorieCounter(); // refreshes text info on calorie counter
        handler.changeGameState(State.GAME);
    }

    public int getCalGoal(){
        if(profile.getSex()){
            return ((100 * profile.getAge() + 900) / 3);
        } else {
            return ((100 * profile.getAge() + 700) / 3);
        }
    }

    public void createButtons() {

        int btnWidth = 120;
        int btnHeight = 40;
        Color btnColor = new Color(59, 89, 182);

        // initializes buttons
        addButton = new JButton("Add Food");
        removeButton = new JButton("Remove Food");
        packLunchButton = new JButton("Pack Lunch");
        randomButton = new JButton("Fill Random");
        testSave = new JButton("Test Save");
        testReturn = new JButton("Main Menu");

        testSave.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                profile.storeFridge(fridge.getFoods());
                profile.storeLunchBox(lunchBox.getFoods());
                profile.setDay(day.getDay());
                try {
                	String path = "./saves/";
                	File directory = new File(path);
            		// create directory if it doesn't exist
            		if(!directory.exists()){
            			directory.mkdir();
            			System.out.println("making directory " + directory.toString());
            		}
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + profile.getName() + ".dat"));
                    out.writeObject(profile);
                    out.close();
                }
                catch(Exception e) {
                    System.out.println("The File Could Not Be Saved.");
                    e.printStackTrace();
                }
            }

        });

        testReturn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                handler.changeGameState(State.MENU);
            }

        });

        // adds function to the add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fridge.getItemList().getSelectedIndex() != -1 && fridge.listSize() > 0
                        && lunchBox.listSize() < lunchBox.getMaxCapacity()) {
                    lunchBox.addItem(fridge.getSelectedItem());
                    fridge.removeSelectedItem();

                    updateCalorieCounter();
                }
            }
        });

        // adds function to the remove button
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lunchBox.getItemList().getSelectedIndex() != -1 && lunchBox.getModel().size() > 0) {
                    fridge.addItem(lunchBox.getSelectedItem());
                    lunchBox.removeSelectedItem();

                    updateCalorieCounter();
                }
            }
        });

        // adds function to the pack lunch button
        packLunchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profile.setWeeklyScore(profile.getWeeklyScore() 
                        + lunchBox.getTotalPoints((double)profile.getWeight(), 
                                profile.getAge(), profile.getSex()));
                lunchBox.clearList();
                scoreBoard.setText("SCORE: " + profile.getWeeklyScore());
                if (day.isFriday()) {
                    fridge.fillRandom();
                    profile.setWeeklyHigh(profile.getWeeklyScore());
                    profile.setWeeklyScore(0);
                    highScore.setText("HIGHSCORE: " + profile.getWeeklyHigh());
                    scoreBoard.setText("SCORE: " + profile.getWeeklyScore());
                }
                day.change();

                updateCalorieCounter();
            }
        });

        // adds function to the fill random button
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fridge.fillRandom();
                lunchBox.clearList();
            }
        });

        // sets color, size & positioning for buttons
        packLunchButton.setBounds(lunchBox.getX() + lunchBox.getWidth() / 2 - btnWidth / 2, 610, btnWidth, btnHeight);
        packLunchButton.setBackground(btnColor);
        packLunchButton.setForeground(Color.WHITE);
        addButton.setBounds(fridge.getX() + ListBox.DEFAULT_WIDTH / 2 - btnWidth / 2,
                fridge.getY() + fridge.getHeight(), btnWidth, btnHeight);
        addButton.setBackground(btnColor);
        addButton.setForeground(Color.WHITE);
        removeButton.setBounds(lunchBox.getX() + ListBox.DEFAULT_WIDTH / 2 - btnWidth / 2,
                lunchBox.getY() + lunchBox.getHeight(), btnWidth, btnHeight);
        removeButton.setBackground(btnColor);
        removeButton.setForeground(Color.WHITE);

        testSave.setBounds(addButton.getX(), addButton.getY() + 100, btnWidth, btnHeight);
        testReturn.setBounds(testSave.getX(), testSave.getY() + 50, btnWidth, btnHeight);

        gamePanel.add(addButton);
        gamePanel.add(removeButton);
        gamePanel.add(packLunchButton);
        gamePanel.add(testSave);
        gamePanel.add(testReturn);
    }

    public void updateCalorieCounter(){
        counter.setText("<HTML><U>"+ profile.getName() +"'s Nutrition</U><HTML>");
        goal.setText("Calorie Goal: " + getCalGoal());
        cal.setText("Calories: " + lunchBox.getTotalCal());
        pts.setText("Points: " + lunchBox.getTotalPoints(profile.getWeight(),profile.getAge(), profile.getSex()));
        
        // highlights food label if capacity reached
        if (lunchBox.listSize() == lunchBox.getMaxCapacity()) {
            cap.setOpaque(true);
        } else {
            cap.setOpaque(false);
        }
        cap.setText("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());
    }

    // GET & SET METHODS

    public User getUser(){
        return profile;
    }

    public void setProfile(User user){
        profile = user;
    }

    public ListBox getFridge(){
        return fridge;
    }

    public void setFridgeItems(ArrayList<FoodObject> foods){
        fridge.setFoods(foods);
    }

    public ListBox getLunchBox(){
        return lunchBox;
    }

    public void setLunchItems(ArrayList<FoodObject> foods){
        lunchBox.setFoods(foods);
    }

    public void setDay(String day){
        this.day.setDay(day);
    }

    public JPanel getGUIPanel(){
        return guiPanel;
    }
}


