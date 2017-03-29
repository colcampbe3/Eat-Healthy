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

@SuppressWarnings("serial")
public class ListBox extends JPanel {
	
	public enum Nutrient {
		PROTEIN,
		SUGAR,
		VITAK,
		VITAC,
		SODIUM
	};

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
		itemList.setFont(new Font("Arial", Font.PLAIN, 16));
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
	
	public double getTotal(Nutrient nutrient){
		
		double total = 0;
		
		for (int i = 0; i < model.size(); i++) {
			if(nutrient == Nutrient.PROTEIN)
				total += model.getElementAt(i).getProtein();
			else if(nutrient == Nutrient.SODIUM)
				total += model.getElementAt(i).getSodium();
			else if(nutrient == Nutrient.SUGAR)
				total += model.getElementAt(i).getSugar();
			else if(nutrient == Nutrient.VITAC)
				total += model.getElementAt(i).getVitaminC();
			else if(nutrient == Nutrient.VITAK)
				total += model.getElementAt(i).getVitaminK();
			else
				total += 0; // don't add anything
		}

		return total;
	}
	
	public int getTotalPoints(double weight, int age, boolean sex) { //Passes values for use in calculation -R

        /* This method of point calculation gives flat amounts of points for meeting certain benchmarks
         This means there is a maxnumber of points that can be earned in an single day and by extension a single week.
         This means it's possible to reach these values and "win" the game. It can be changed to an linear model if desired.
         -R
          */




        int totalPoints = 0;
        int totalCal = 0;
        int totalViC = 0;
        int totalViK = 0;
        int totalPro = 0;
        int totalSod = 0;
        int totalSug = 0;
        for (int i = 0; i < model.size(); i++) {
            totalCal += model.getElementAt(i).getCalories();
            totalViC += model.getElementAt(i).getVitaminC();
            totalViK += model.getElementAt(i).getCalories();
            totalPro += model.getElementAt(i).getProtein();
            totalSod += model.getElementAt(i).getSodium();
            totalSug += model.getElementAt(i).getSugar();
        }
        /*These values are WIP feel free to change them for balancing reasons.
        CALORIES

                    ((100*age) + 900) / 3
                        ^ This is the daily recommended calories for a male divided by three.
                            NOTE: THIS IS ONLY FOR AGES 9 - 12

        If total Calories is within 100 points of the recommended value you get 100 points
        Otherwise if it's within 200 points you get 50. -R      */
        if (sex) {
            if ( Math.abs(totalCal - (((100*age) + 900)/3)) <= 100) {
                totalPoints += 100;
            }
            else if( Math.abs(totalCal - (((100*age) + 900)/3)) <= 200) {
                totalPoints += 50;
            }
        }
        else {
            if ( Math.abs(totalCal - (((100*age) + 700)/3)) <= 100) {
                totalPoints += 100;
            }
            else if( Math.abs(totalCal - (((100*age) + 700)/3)) <= 200) {
                totalPoints += 50;
            }
        }
        //SODIUM - 1500mg Daily
        //If total Sodium is within 100mgs of recommended value divided by three you get 100 points
        //Otherwise if it's within 200mgs you get 50 points. -R
        if ( Math.abs(totalSod - 500) <= 100) {
            totalPoints += 100;
        }
        else if ( Math.abs(totalSod - 500) <= 200) {
            totalPoints += 50;
        }
        //SUGAR - 30g Daily
        //If total Sugar is within 3gs of recommended value divided by three you get 100 points
        //Otherwise if it's within 5gs you get 50 points. -R
        if ( Math.abs(totalSug - 10) <= 3) {
            totalPoints += 100;
        }
        else if ( Math.abs(totalSug - 10) <= 5) {
            totalPoints += 50;
        }
        /* Protein

            (weight * .45g) / 3 <-- This is the daily recommended amount of protien divided by three.
                NOTE: THIS IS ONLY FOR AGES 9 - 12.

            Full points is within 80% of daily value
            Half points is within 65% of daily value
            -R
         */
        if ( Math.abs((totalPro - ((weight *.45)/3))) <= (weight * .36)/3) {
            totalPoints += 100;
        }
        else if ( (totalPro - ((weight *.45)/3)) <= (weight * .29)/3) {
            totalPoints += 50;
        }
        //Vitamin C - 45mg Daily
        //If total Vitamin C is above 100% of recommended value divided by three you get 100 points
        //Otherwise if it's above 90% you get 50 points. -R
        if ( totalViC/15 >= 1) {
            totalPoints += 100;
        }
        else if ( totalViC >= .9) {
            totalPoints += 50;
        }
        /*Vitamin K - 0.006mg Daily 0r 60mcg (MicroGrams)
            If total Vitam C is above 100% of recommended value divided by three you get 100 points
            Otherwise if it's above 90% you get 50 points.

            Vitamin K is found in green vegetables I included it to as a way to check if the lunch included vegetables,
             if this turns ot to be to difficult to track it can be removed.
             -R
        */
        if ( totalViK/0.002 >= 1) {
            totalPoints += 100;
        }
        else if ( totalViK/0.0018 >= .9) {
            totalPoints += 50;
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
	
	public void setFoods(ArrayList<FoodObject> foods){
		clearList();
		
		for(FoodObject food : foods){
			addItem(food);
		}
	}

}
