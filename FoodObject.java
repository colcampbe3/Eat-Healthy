package eathealthy;

public class FoodObject {

	private String name;
	private int calories;
	private int value;
	private int sodium;
	private float sugar;
	private float protein;
	
	public FoodObject(String name, int cal,  int sodium, float sugar, float protein, int val){
		this.name = name;
		this.calories = cal;
		this.sodium = sodium;
		this.sugar = sugar;
		this.protein = protein;
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

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public float getSugar() {
		return sugar;
	}

	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}
}
