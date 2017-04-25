
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rick on 3/8/2017.
 * This is an object to store the information linked to the user profile with commands to set and retrieve the
 * information stored within.
 */
public class User implements Serializable {
    private String name;
    private int weeklyHighScore;
    private int dailyHighScore;
    private float totalScore;
    private double weight;
    private int age;
    private boolean sex; //false: female, true: male
    private int weeklyScore; //The current weekly score, can be stored here or by the game itself.
    private int unlock; //Commented out until implemented.
    private String day;
    private ArrayList<FoodObject> fridge;
    private ArrayList<FoodObject> lunchBox;


    public User(String name) {
        this.name = name;
        this.weeklyHighScore = 0;
        this.dailyHighScore = 0;
        this.totalScore = 0;
        this.unlock = 40;
    }

    public User(String name, double weight, int age, boolean sex) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.unlock = 40;
        this.weeklyHighScore = 0;
        this.dailyHighScore = 0;
        this.totalScore = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setWeeklyHigh(int score) {
        if (score > weeklyHighScore) {
            weeklyHighScore = score;
        }
    }

    public int getWeeklyHigh() {
        return this.weeklyHighScore;
    }

    public void setDailyHigh(int score) {
        if (score > dailyHighScore) {
            dailyHighScore = score;
        }
    }

    public void storeFridge(ArrayList<FoodObject> newFridge) {
        this.fridge = newFridge;
    }

    public void storeLunchBox(ArrayList<FoodObject> newLunchBox) {
        this.lunchBox = newLunchBox;
    }

    public ArrayList<FoodObject> getFridge() {
        return this.fridge;
    }

    public ArrayList<FoodObject> getLunchBox() {
        return this.lunchBox;
    }

    public String getDay() {
        return this.day;
    }
    public void setDay(String newDay) {
        this.day = newDay;
    }

    public int getDailyHigh() {
        return this.dailyHighScore;
    }

    public void setTotalScore(float score) { //directly change the total
        this.totalScore = score;
    }

    public void addTotalScore(int score) { //increment the total by the amount specified
        this.totalScore = this.totalScore + score;
    }

    public float getTotalScore() {
        return this.totalScore;
    }

    public void setWeight(double newWeight) {
        this.weight = newWeight;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public int getAge() {
        return this.age;
    }

    public void setSex(boolean newSex) {
        this.sex = newSex;
    }

    public boolean getSex() {
        return this.sex;
    }

    public void setWeeklyScore(int score) { //directly change the total
        this.weeklyScore = score;
    }

    public void addWeeklyScore(int score) { //increment the total by the amount specified
        this.weeklyScore = this.weeklyScore + score;
    }

    public int getWeeklyScore() {
        return this.weeklyScore;
    }


    public void setUnlock(int unlock) {
        this.unlock = unlock;
    }

    public int getUnlock() {
        
        if(weeklyHighScore > 1950){
            unlock = 51;
        }else if(weeklyHighScore > 1930){
            unlock = 50;
        }else if(weeklyHighScore > 1900){
            unlock = 49;
        }else if(weeklyHighScore > 1850){
            unlock = 48;
        }else if(weeklyHighScore > 1800){
            unlock = 47;
        }else if(weeklyHighScore > 1700){
            unlock = 46;
        }else if(weeklyHighScore > 1600){
            unlock = 45;
        }else if(weeklyHighScore > 1500){
            unlock = 44;
        }else if(weeklyHighScore > 1250){
            unlock = 43;
        }else if(weeklyHighScore > 1000){
            unlock = 42;
        }else if(weeklyHighScore > 500){
            unlock = 41;
        }else unlock = 40;
        return this.unlock;
    }

}