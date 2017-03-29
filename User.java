package eathealthy;

import java.io.Serializable;

/**
 * Created by Rick on 3/8/2017.
 * This is an object to store the information linked to the user profile with commands to set and retrieve the
 * information stored within.
 */
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7165837951626619805L;
	private String name;
    private int weeklyHighScore;
    private int dailyHighScore;
    private int totalScore;
    private double weight;
    private int age;
    private boolean sex; //false: female, true: male
    private int weeklyScore; //The current weekly score, can be stored here or by the game itself.
    //private boolean unlock[]; //Commented out until implemented.

    public User(String name) {
        this.name = name;
        this.weeklyHighScore = 0;
        this.dailyHighScore = 0;
        this.totalScore = 0;
    }
    
    public User(String name, int weight, int age, boolean sex) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
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

    public int getDailyHigh() {
        return this.dailyHighScore;
    }

    public void setTotalScore(int score) { //directly change the total
        this.totalScore = score;
    }

    public void addTotalScore(int score) { //increment the total by the amount specified
        this.totalScore = this.totalScore + score;
    }

    public int getTotalScore() {
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

    /*

    public void setUnlock(int index,boolean status) {
        this.unlock[index] = status;
    }

    public boolean getUnlock(int index) {
        return this.unlock[index];
    }

    */
}
