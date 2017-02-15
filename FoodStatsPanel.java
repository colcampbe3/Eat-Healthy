package eathealthy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FoodStatsPanel extends JPanel {

	private int width = 500;
	private int height = 200;
	private int scale = 1;

	private Timer day;
	private ListBox lunchbox;
	private JTextArea textArea;
//	private JLabel name;
//	private JLabel calories;
//	private JLabel points;
	private JLabel header;
	private Font font;
	private Font font2;

	private String format = "  %-24s %6d %12d %12s";
	private int totalCals;
	private int totalPoints;

	public FoodStatsPanel(Timer day, ListBox lunch) {

		this.day = day;
		this.lunchbox = lunch;

		font = new Font("Arial", Font.BOLD, 24 * scale);
		font2 = new Font("monospaced", Font.PLAIN, 14 * scale); // need font that is mono-spaced in JTextArea or else won't align correctly

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(font2);
		
		JScrollPane scrollText = new JScrollPane(textArea);

//		name = new JLabel("Name");
//		calories = new JLabel("Calories");
//		points = new JLabel("Points");
		String heading = String.format("  %-15s %9s %10s %10s", "Name", "Calories", "Points", "Day");
		header = new JLabel(heading);

		// set fonts
//		name.setFont(font);
//		calories.setFont(font);
//		points.setFont(font);
		header.setFont(font);
		
		// set colors
		setBackground(Color.black);
//		name.setForeground(Color.green);
		header.setForeground(Color.white);
		textArea.setBackground(Color.white);
		textArea.setForeground(Color.black);

//		setLayout(null);
		setLayout(new BorderLayout());
		
		add(header, BorderLayout.NORTH);
		add(scrollText, BorderLayout.CENTER);
//		add(textArea);
		
//		Insets insets = this.getInsets();
//		header.setBounds(insets.left + 20, insets.top + 5, header.getPreferredSize().width, header.getPreferredSize().height);
//		textArea.setBounds(insets.left, insets.top + 40, this.width, 400);
		
//		textArea.setPreferredSize(new Dimension(this.width, 400));
//		scrollText.setBounds(insets.left, insets.top + 40, this.width, 180);

		setSize(width * scale, height * scale);
		setPreferredSize(new Dimension(width * scale, height * scale));
		setVisible(true);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		// setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	/*
	 * Method that updates info being displayed on panel
	 */
	public void update() {

		String tempFoodString = new String();
		
//		display();
		
		// clears panel and resets values at starting of the week
		if (day.getField().getText().equals("Monday")) {
			// clear text area
			textArea.setText("");
			totalPoints = 0;
			totalCals = 0;
		}
		
		for (FoodObject food : lunchbox.getFoods()) {
			tempFoodString = String.format(format, food.getName(), food.getCalories(), food.getValue(), day.getField().getText().toString());
			totalPoints += food.getValue();
			totalCals += food.getCalories();
			textArea.append(tempFoodString + "\n");
		}
		
		// show totals after last day each week
		if (day.getField().getText().equals("Friday")) {
			textArea.append(String.format("\n  %-24s %6d\n", "Total Calories: ", totalCals));
			textArea.append(String.format("  %-24s %6d\n", "Total Points: ", totalPoints));
		}
		
//		ArrayList<FoodObject> food = lunchbox.getFoods();
//		for (int i = 0; i < food.size(); i++) {
//			String tempFoodString = new String(
//					String.format("%-24s %8s %12s\n", food.get(i).getName(), food.get(i).getCalories(), food.get(i).getValue()));
//
//			textArea.append(tempFoodString);
//		}
	}
	
	public void display() {

		if (day.isFriday()) {
			setVisible(true);
		} else {
			setVisible(false);
		}
	}
	
	public void setScale(int num){
		scale = num;
	}
}
