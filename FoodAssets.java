
public class FoodAssets {

    public static FoodManager fm = new FoodManager();

    public static void init(){

        // FoodObject(name, calories, sodium, sugar, protein, points)
        // https://www.nutritionvalue.org
        fm.addFood(new FoodObject("Chicken Sandwich", 249, 753, 3.6f, 16, -5, 5, 5));
	fm.addFood(new FoodObject("BLT", 244, 548, 5.5f, 15, -5, 5, 5));
	fm.addFood(new FoodObject("Grapes", 69, 2 ,15f, 0.7f, -5, 5, 5));
	fm.addFood(new FoodObject("Bean Burrito", 209, 563, 1.7f, 7.4f, -5, 5, 5));
	fm.addFood(new FoodObject("Gelatin", 62, 75, 13, 1.2f, -5, 5, 5));		
        fm.addFood(new FoodObject("Apple", 116, 2, 23, 0.6f, -5, 5, 5));
	fm.addFood(new FoodObject("Pork Chop", 287, 89, 0, 40f, -5, 5, 5));
	fm.addFood(new FoodObject("Soda Pop", 140, 49, 35, 0, -5, 5, 5));
	fm.addFood(new FoodObject("Carrots", 35, 78, 5, 0.6f, -5, 5, 5));
	fm.addFood(new FoodObject("Banana", 121, 1, 17, 1.5f, -5, 5, 5));
	fm.addFood(new FoodObject("Doritos", 498, 708, 0, 7.8f, -5, 5, 5));
	fm.addFood(new FoodObject("Fruit Yogurt", 106, 37, 12, 7.3f, -5, 5, 5));
	fm.addFood(new FoodObject("Plain Popcorn", 110, 2, 0, 3.7f, -5, 5, 5));
        fm.addFood(new FoodObject("Watermelon Slice", 86, 3, 18, 2.7f,-5, 5, 5));
	fm.addFood(new FoodObject("8oz Milk", 122, 100, 12, 8.1f, -5, 5, 5));
        fm.addFood(new FoodObject("Peanut Butter Crackers", 192, 329, 3, 4.2f, -5, 5, 5));
        fm.addFood(new FoodObject("Fruit Snacks", 155, 10, 30, 0, -5, 5, 5));
	fm.addFood(new FoodObject("Meatball Sub", 458, 913, 10, 20, -5, 5, 5));
        fm.addFood(new FoodObject("Tuna Sandwich", 524, 780, 5, 29, -5, 5, 5));
	fm.addFood(new FoodObject("Water Bottle", 0, 7, 0, 0, -5, 5, 5));
        fm.addFood(new FoodObject("Energy Drink", 111, 101, 26, 1.6f, -5, 5, 5));
        fm.addFood(new FoodObject("Peanut Butter Candies", 229, 89, 25, 5.7f, -5, 5, 5));
        fm.addFood(new FoodObject("Broccoli", 31, 30, 2, 2.6f, -5, 5, 5));
  	fm.addFood(new FoodObject("Chicken Noodle Soup", 60, 831, 0, 2.9f, -5, 5, 5));
        fm.addFood(new FoodObject("Orange Juice", 122, 5, 21, 1.7f, -5, 5, 5));
        fm.addFood(new FoodObject("Orange", 69, 1, 12, 1.3f, -5, 5, 5));
        fm.addFood(new FoodObject("Fish Filet Sandwich", 374, 582, 5, 15.0f, -5, 5, 5));
        fm.addFood(new FoodObject("Mac and Cheese", 285, 714, 3, 13.0f, -5, 5, 5));
	fm.addFood(new FoodObject("Strawberries", 46, 1, 7, 1f, 0, 0, 0));
        fm.addFood(new FoodObject("Pizza Rolls", 262, 479, 3, 7.0f, 0, 0, 0));
        fm.addFood(new FoodObject("Pepperoni Pizza Slice", 348, 876, 4.9f, 15.0f, 0, 0, 0));
        fm.addFood(new FoodObject("Spinach", 23, 70, 0.4f, 3f, 0, 0, 0));
        fm.addFood(new FoodObject("Lemon-Lime Soda", 232, 85, 61, 0, 0, 0, 0));
        fm.addFood(new FoodObject("Pudding Cup", 153, 164, 19, 2.3f, 0, 0, 0));
        fm.addFood(new FoodObject("Cereal Bar", 365, 354, 29, 4.2f, 0, 0, 0));
        fm.addFood(new FoodObject("Pear", 116, 0, 19, 1.4f, 0, 0, 0));
        fm.addFood(new FoodObject("Bologna Sandwich", 307, 922, 3.4f, 13f, 0, 0, 0));
        fm.addFood(new FoodObject("Ham and Cheese Sandwich", 308, 925, 3.5f, 21, 0, 0, 0));
        fm.addFood(new FoodObject("Potato Chips", 157, 52, 0, 1.9f, 0, 0, 0));
        fm.addFood(new FoodObject("French Fries", 491, 607, 0.5f, 5.9f, 0, 0, 0));
	fm.addFood(new FoodObject("Cottage Cheese", 206, 764, 5.6f, 23, 0, 0, 0));
	fm.addFood(new FoodObject("Green Tea", 2, 2, 0f, 0.5f, 0, 0, 0));
        fm.addFood(new FoodObject("Lemonade", 110, 14, 28.0f, 0, 1, 0, 0));
	fm.addFood(new FoodObject("Rib Eye Steak", 447, 167, 0 , 53, 0, 0,0));
        fm.addFood(new FoodObject("Caviar", 264, 1500, 0, 25, 0, 0, 0));
        fm.addFood(new FoodObject("Baked Potato", 246, 138, 2.2f, 8.6f, 0, 0, 0));
        fm.addFood(new FoodObject("Baked Beans", 121, 475, 0, 6.4f, 0, 0, 0));
	fm.addFood(new FoodObject("Chocolate-Chip Cookies", 147, 158, 9.9f, 1.7f, 0, 0, 0));
        fm.addFood(new FoodObject("Pistachios", 162, 2, 2.2f, 6, 0, 0, 0));

    }

    public static int getSize(){
        return fm.getFoods().size();
    }
}