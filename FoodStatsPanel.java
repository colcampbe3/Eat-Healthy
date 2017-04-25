
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FoodStatsPanel extends JPanel {

	private Calendar day;
	private ListBox lunchbox;

	private int totalCals,  totalSodium;
	private float totalSugar, totalProtein;
	private int weekCounter = 1;

	public FoodStatsPanel(Calendar day, ListBox lunch) {
            
		this.day = day;
		this.lunchbox = lunch;

	}

	/*
	 * Method that updates info being displayed on panel
	 */
	public void update(int score) {
		for (FoodObject food : lunchbox.getFoods()) {
			
			 totalCals += food.getCalories();
			 totalSodium += food.getSodium();
			 totalSugar += food.getSugar();
			 totalProtein += food.getProtein();
		}
                DailyGoals goals = new DailyGoals(score, totalCals, totalSodium, totalSugar, totalProtein);
                totalCals = 0;
                totalSodium = 0;
                totalSugar = 0;
                totalProtein = 0;
                         
	}
}
