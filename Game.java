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
      
    }
  
}
