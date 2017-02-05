package eathealthy;

public class FoodAssets {

	public static FoodManager fm = new FoodManager();
	
	public static void init(){
		fm.addFood(new FoodObject("Burrito", 200, -5));
		fm.addFood(new FoodObject("Chicken", 150, 5));
		fm.addFood(new FoodObject("Jello", 50, -2));
		fm.addFood(new FoodObject("Pork Chop", 100, 5));
		fm.addFood(new FoodObject("Doritos", 50, -2));
		fm.addFood(new FoodObject("Apple", 50, 2));
		fm.addFood(new FoodObject("Banana", 50, 3));
		fm.addFood(new FoodObject("BLT", 550, -2));
		fm.addFood(new FoodObject("Carrot", 50, -2));
		fm.addFood(new FoodObject("Grapes", 50, -2));
		fm.addFood(new FoodObject("Milk - 8oz", 50, -2));
		fm.addFood(new FoodObject("Soda Pop", 50, -2));
		fm.addFood(new FoodObject("Candy", 100, -2));
		fm.addFood(new FoodObject("Steak", 50, -2));
		fm.addFood(new FoodObject("Spaghetti", 50, -2));
		fm.addFood(new FoodObject("Yogurt", 50, -2));
		fm.addFood(new FoodObject("Broccoli", 50, -2));
		fm.addFood(new FoodObject("Pop Corn", 50, -2));
		fm.addFood(new FoodObject("Water", 0, 1));
		fm.addFood(new FoodObject("Salad", 50, 10));
		fm.addFood(new FoodObject("Energy Drink", 200, -2));
		fm.addFood(new FoodObject("Fruit Snack", 50, 1));
		fm.addFood(new FoodObject("Peanut Butter Sandwich", 50, -2));
	}
	
	public int getSize(){
		return fm.getFoods().size();
	}
}
