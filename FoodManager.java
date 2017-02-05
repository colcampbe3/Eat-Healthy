package eathealthy;

import java.util.ArrayList;

public class FoodManager {

	private ArrayList<FoodObject> foods;
	
	public FoodManager(){
		foods = new ArrayList<FoodObject>();
	}
	
	public void addFood(FoodObject food){
		foods.add(food);
	}
	
	public void removeFood(String food){
		foods.remove(food);
	}
	
	// GETTERS SETTERS
	public ArrayList<FoodObject> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<FoodObject> foods) {
		this.foods = foods;
	}
}
