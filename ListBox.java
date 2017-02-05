package eathealthy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListBox extends JPanel {

	public final int WIDTH = 250;
	public final int HEIGHT = 300;
	
	private JList<FoodObject> itemList;
	private DefaultListModel<FoodObject> model;
	private JScrollPane scrollPane;
	
	//constructor
	public ListBox() {

		model = new DefaultListModel<FoodObject>();
		itemList = new JList<FoodObject>(model);
		scrollPane = new JScrollPane(itemList);
		
		itemList.setFont(new Font("Arial",Font.PLAIN,20));
//		itemList.setBackground(Color.black);
//		itemList.setForeground(Color.white);
		
		// Centers text in list
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)itemList.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  
		
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setSize(new Dimension(WIDTH, HEIGHT));
//		System.out.println("This panel is " + this.getWidth() + " x " + this.getHeight());
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void addItem(FoodObject food){
		model.addElement(food);
	}
	
	public FoodObject getSelectedItem(){
		if(!(itemList.isSelectionEmpty())){
			return model.getElementAt(itemList.getSelectedIndex());
		}
		return null;
	}
	
	public void removeSelectedItem(){
		if(!(itemList.isSelectionEmpty())){
			model.removeElementAt(itemList.getSelectedIndex());
		}
	}
	
	public void clearList(){
		model.clear();
	}
	
	/*
	 * Method that fills list with random food items
	 */
	public void fillRandom(){
		Random r = new Random();
		int randNum;
		ArrayList<FoodObject> tempFoodList = new ArrayList<FoodObject>();
		ArrayList<FoodObject> tempFoodList2 = new ArrayList<FoodObject>();
		
		// get a copy of all foods from food assets class
		for (int i = 0; i < FoodAssets.fm.getFoods().size(); i++) {
			tempFoodList.add(FoodAssets.fm.getFoods().get(i));
		}
		
		// randomly take 30 items
		for (int i = 0; i < 12; i++) {
			randNum = r.nextInt(tempFoodList.size());
//			System.out.println(randNum);
			tempFoodList2.add(tempFoodList.get(randNum));
			tempFoodList.remove(randNum);
			
		}
		
		// clear list of current items
		if(!model.isEmpty())
			model.clear();
		
		// populate list with random food items
		for(FoodObject food : tempFoodList2){
			model.addElement(food);
		}
	}
	
	// GETTERS SETTERS
	
		public JList<FoodObject> getItemList() {
			return itemList;
		}

		public DefaultListModel<FoodObject> getModel() {
			return model;
		}

		/*
		 * Method returns array of foods from list
		 */
		public ArrayList<FoodObject> getFoods(){
			
			ArrayList<FoodObject> tempFoods = new ArrayList<FoodObject>();
			
			for (int i = 0; i < model.size(); i++) {
				tempFoods.add(model.get(i));
			}
			
			return tempFoods;
		}

}
