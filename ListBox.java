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
import javax.swing.border.EmptyBorder;

public class ListBox extends JPanel {

	public static final int DEFAULT_WIDTH = 250;
	public static final int DEFAULT_HEIGHT = 300;

	private JList<FoodObject> itemList;
	private DefaultListModel<FoodObject> model;
	private JScrollPane scrollPane;
	private JLabel heading;
	private int x, y, width, height, maxCapacity = 200;

	// default constructor
	public ListBox(){
		model = new DefaultListModel<FoodObject>();
		itemList = new JList<FoodObject>(model);
		scrollPane = new JScrollPane(itemList);
		
		createList();
	}
	
	// constructor
	public ListBox(int x, int y, int width, int height, String text) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		model = new DefaultListModel<FoodObject>();
		itemList = new JList<FoodObject>(model);
		scrollPane = new JScrollPane(itemList);
		heading = new JLabel(text);
		
		createList();
	}
	
	/*
	 * Configures and sets up all components in the list
	 */
	public void createList(){
		itemList.setFont(new Font("Arial", Font.PLAIN, 20));
		itemList.setDragEnabled(true);

		// Centers text in list
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) itemList.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setSize(new Dimension(width, height));
		// itemList.setSelectionMode();
		// System.out.println("This panel is " + this.getWidth() + " x " +
		// this.getHeight());
		add(scrollPane, BorderLayout.CENTER);
		
		if(heading != null){
			heading.setHorizontalAlignment(JLabel.CENTER);
			heading.setFont(new Font("Arial", Font.BOLD, 26));
//			heading.setBackground(Color.black);
			heading.setForeground(Color.white);
			heading.setBorder(new EmptyBorder(5, 5, 5, 5)); // puts padding around header
			add(heading, BorderLayout.NORTH);
		}
		
		if(width == 0 && height == 0){
			width = DEFAULT_WIDTH;
			height = DEFAULT_HEIGHT;
		}
		
		setBounds(x, y, width, height);
	}

	public void addItem(FoodObject food) {
		model.addElement(food);
	}

	public FoodObject getSelectedItem() {
		if (!(itemList.isSelectionEmpty())) {
			return model.getElementAt(itemList.getSelectedIndex());
		}
		return null;
	}

	public void removeSelectedItem() {
		if (!(itemList.isSelectionEmpty())) {
			model.removeElementAt(itemList.getSelectedIndex());
		}
	}

	public void clearList() {
		model.clear();
	}

	public int getTotalCal() {

		int totalCal = 0;

		for (int i = 0; i < model.size(); i++) {
			totalCal += model.getElementAt(i).getCalories();
		}

		return totalCal;
	}

	public int getTotalPoints() {

		int totalPoints = 0;

		for (int i = 0; i < model.size(); i++) {
			totalPoints += model.getElementAt(i).getValue();
		}

		return totalPoints;
	}

	/*
	 * Method that fills list with random food items
	 */
	public void fillRandom() {
		Random r = new Random();
		int randNum;
		ArrayList<FoodObject> tempFoodList = new ArrayList<FoodObject>();
		ArrayList<FoodObject> tempFoodList2 = new ArrayList<FoodObject>();

		// get a copy of all foods from food assets class
		for (int i = 0; i < FoodAssets.fm.getFoods().size(); i++) {
			tempFoodList.add(FoodAssets.fm.getFoods().get(i));
		}

		// randomly take items
		for (int i = 0; i < 20; i++) {
			randNum = r.nextInt(tempFoodList.size());
			// System.out.println(randNum);
			tempFoodList2.add(tempFoodList.get(randNum));
			tempFoodList.remove(randNum);

		}

		// clear list of current items
		if (!model.isEmpty())
			model.clear();

		// populate list with random food items
		for (FoodObject food : tempFoodList2) {
			model.addElement(food);
		}
	}

	// GETTERS SETTERS

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	public void setMaxCapacity(int capacity){
		this.maxCapacity = capacity;
	}
	
	public void setTitleColor(Color textColor, Color background){
		setBackground(background);
		heading.setForeground(textColor);
	}
	
	public int listSize(){
		return model.getSize();
	}
	
	public JList<FoodObject> getItemList() {
		return itemList;
	}

	public DefaultListModel<FoodObject> getModel() {
		return model;
	}

	/*
	 * Method returns array of foods from list
	 */
	public ArrayList<FoodObject> getFoods() {

		ArrayList<FoodObject> tempFoods = new ArrayList<FoodObject>();

		for (int i = 0; i < model.size(); i++) {
			tempFoods.add(model.get(i));
		}

		return tempFoods;
	}

}
