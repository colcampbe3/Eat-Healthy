package Game;
import java.util.ArrayList;

ListBox fridge;
ListBox lunchBox;

public class Game() {
    ListBox fridge;
    ListBox lunchBox;
    
    public game(ListBox fridgeRef,ListBox lunchBoxRef) {
        fridge = fridgeRef;
        lunchBox = lunchBoxRef;
    }
    public void add() {
        if (lunchBox.getItemList().getSelectedIndex() != -1 && lunchBox.getModel().size() > 0) {
					fridge.addItem(lunchBox.getSelectedItem());
					lunchBox.removeSelectedItem();
        }
    }
    public void remove() {
         if (lunchBox.getItemList().getSelectedIndex() != -1 && lunchBox.getModel().size() > 0) {
					fridge.addItem(lunchBox.getSelectedItem());
					lunchBox.removeSelectedItem();
         }
    }
    public void pack() {
	 ArrayList<FoodObject> stuff = lunchBox.getFoods();
	 System.out.printf("\n%-24s %8s %12s\n", "Name", "Calories", "Points");
	 System.out.println("-------------------------------------------------");
	 for (int i = 0; i < stuff.size(); i++) {
	 	System.out.printf("%-24s %8s %12s\n", stuff.get(i).getName(), stuff.get(i).getCalories(),
		stuff.get(i).getValue());
	 }
      
    }
  
}
