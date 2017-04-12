package eathealthy;

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

	private int width = 600;
	private int height = 300;
	private int scale = 1;

	private eathealthy.Calendar day;
	private ListBox lunchbox;
	private JTable table;
	private DefaultTableModel model;

	private JButton close;
	private int totalCals, totalPoints, totalSodium;
	private float totalSugar, totalProtein;
	private int weekCounter = 1;

	public FoodStatsPanel(eathealthy.Calendar day, ListBox lunch) {

		this.day = day;
		this.lunchbox = lunch;

//		font = new Font("Arial", Font.BOLD, 24 * scale);
//		// need font to be mono-spaced or else won't align correctly in textArea
//		font2 = new Font("monospaced", Font.PLAIN, 14 * scale); 

		close = new JButton("Close");

		String[] columns = { "Food", "Calories", "Sodium", "Sugar", "Protein", "Points", "Day" };
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		
		// set alignment of columns
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		
		table.getTableHeader().setBackground(new Color(59, 89, 182));
		table.getTableHeader().setForeground(Color.white);
		
		JScrollPane scrollText = new JScrollPane(table);

		setLayout(new BorderLayout());

		add(scrollText, BorderLayout.CENTER);
		add(close, BorderLayout.SOUTH);

		setSize(width * scale, height * scale);
		setPreferredSize(new Dimension(width * scale, height * scale));
		setVisible(false);

		setBorder(BorderFactory.createLineBorder(Color.black));
		// setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});
	}

	/*
	 * Method that updates info being displayed on panel
	 */
	public void update() {

		display();

		if(day.getField().getText().equals("Monday") && weekCounter > 1){
			model.addRow(new Object[] {"","","","","","",""}); // blank row
		}

		for (FoodObject food : lunchbox.getFoods()) {
			model.addRow(new Object[] { food.getName(), food.getCalories(), food.getSodium() + " mg", food.getSugar() + " g",
					food.getProtein() + " g", food.getValue(), day.getField().getText().toString() });
			 totalPoints += food.getValue();
			 totalCals += food.getCalories();
			 totalSodium += food.getSodium();
			 totalSugar += food.getSugar();
			 totalProtein += food.getProtein();
		}

		// show totals after last day each week
		if (day.getField().getText().equals("Friday")) {
			model.addRow(new Object[] {"","","","","","",""}); // blank row
			model.addRow(new Object[]{"Week " + weekCounter + " Totals: ", totalCals, totalSodium + " mg", totalSugar + " g", totalProtein + " g", totalPoints});
			
			weekCounter++;
		}
                DailyGoals goals = new DailyGoals(totalPoints, totalCals, totalSodium, totalSugar, totalProtein);

                         
	}

	public void display() {

		if (day.isFriday()) {
			setVisible(true);
		} else {
			setVisible(false);
		}
	}

	public void setScale(int num) {
		scale = num;
	}
}
