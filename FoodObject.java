import java.io.Serializable;

public class FoodObject implements Serializable {

    private String name;
    private double calories;
    private double vitaminC;
    private double vitaminK;
    private double protein;
    private double sodium;
    private double sugar;
    private int value;

    public FoodObject(String name, double cal, double vitC, double vitK, double pro, double sod, double sug, int val){
        this.name = name;
        this.calories = cal;
        this.vitaminC = vitC;
        this.vitaminK = vitK;
        this.protein = pro;
        this.sodium = sod;
        this.sugar = sug;
        this.value = val;
    }

    @Override
    public String toString(){
        return name;
    }


    // GETTERS SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getVitaminC() {return vitaminC;}

    public void setVitaminC(double vitaminC) { this.vitaminC = vitaminC; }

    public double getVitaminK() {return vitaminK;}

    public void setVitaminK(double vitaminK) { this.vitaminK = vitaminK; }

    public double getProtein() {return protein;}

    public void setProtein(double protein) { this.protein = protein; }

    public double getSodium() {return sodium;}

    public void setSodium(double sodium) { this.sodium = sodium; }

    public double getSugar() {return sugar;}

    public void setSugar(double sugar) { this.sugar = sugar; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
