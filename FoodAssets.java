package eathealthy;

public class FoodAssets {

    public static FoodManager fm = new FoodManager();

    public static void init(){

        // FoodObject(name, calories, sodium, sugar, protein, points)
        // https://www.nutritionvalue.org
        fm.addFood(new FoodObject("CF Chicken Sandwich", 249, 753, 3.6f, 16, -5, 5,5));
        fm.addFood(new FoodObject("McDonald's BLT", 244, 548, 5.5f, 15, 5,5,5));
        fm.addFood(new FoodObject("Grapes - 100g", 69, 2 ,15f, 0.7f, 5,5,5));
        fm.addFood(new FoodObject("TB Bean Burrito", 209, 563, 1.7f, 7.4f, 5,5,5));
        fm.addFood(new FoodObject("Egg", 382, 1280, 5.4f, 81, 5,5,5));

        fm.addFood(new FoodObject("Burrito", 200, 50, 50, 50, -5,5,5));
        fm.addFood(new FoodObject("Jello", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Pork Chop", 100, 50, 50, 50, 5,5,5));
        fm.addFood(new FoodObject("Doritos", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Apple", 50, 50, 50, 50, 2,5,5));
        fm.addFood(new FoodObject("Banana", 50, 50, 50, 50, 3,5,5));
        fm.addFood(new FoodObject("BLT", 550, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Carrot", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Grapes", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Milk - 8oz", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Soda Pop", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Candy", 100, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Steak", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Spaghetti", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Yogurt", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Broccoli", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Pop Corn", 50, 50, 50, 50, -2,5,5));
        fm.addFood(new FoodObject("Water", 0, 50, 50, 50, 1,5,5));
        fm.addFood(new FoodObject("Salad", 50, 50, 50, 50, 10,5,5));
        fm.addFood(new FoodObject("Energy Drink", 200, 50, 50, 50,-2,5,5));
        fm.addFood(new FoodObject("Fruit Snack", 50, 50, 50, 50, 1,5,5));
        fm.addFood(new FoodObject("Peanut Butter Sandwich", 50, 50, 50, 50, -2,5,5));
    }

    public int getSize(){
        return fm.getFoods().size();
    }
}

