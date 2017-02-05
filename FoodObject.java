package eathealthy;

public class FoodObject {

	private String name;
	private int calories;
	private int value;
	
	public FoodObject(String name, int cal, int val){
		this.name = name;
		this.calories = cal;
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
}
