package eathealthy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EatHealthy {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = WIDTH / 12 * 9 + 20; // aspect ratio 12:9
															// format
	private String path = "res/background/fridge_closed.jpg";
	private File f = new File(path);

	private JButton addButton, removeButton, packLunchButton, randomButton;
	private ListBox fridge, lunchBox;
	private FoodStatsPanel statsPanel;
	private Timer day;
	private Window window;
	private JLabel cal, pts, cap;
	
	private int calGoal = 1200;

	public static void main(String[] args) {

                UserInfo user = new UserInfo();
                user.setVisible(true);

	}

	/*
	 * Method that starts the game by initializing frame and all components
	 */
	public void start() {
                
		// creates all food objects
		FoodAssets.init();

		// Creates window to hold all components
		window = new Window(WIDTH, HEIGHT, "Eat Healthy");
		fridge = new ListBox(WIDTH - ListBox.DEFAULT_WIDTH - 10, 50, ListBox.DEFAULT_WIDTH, ListBox.DEFAULT_HEIGHT,
				"FRIDGE");
		fridge.fillRandom();
		lunchBox = new ListBox(10, 50, ListBox.DEFAULT_WIDTH, 190, "LUNCH BOX");
		lunchBox.setMaxCapacity(5);

		day = new Timer();

		statsPanel = new FoodStatsPanel(day, lunchBox);
		
		// creates calorie counter
		JPanel p = new JPanel();
		JLabel counter = new JLabel("<HTML><U>Nutrition</U><HTML>", SwingConstants.CENTER);
		JLabel goal = new JLabel("Calorie Goal: " + calGoal);
		cal = new JLabel("Calories: " + lunchBox.getTotalCal());
		pts = new JLabel("Points: " + lunchBox.getTotalPoints());
		cap = new JLabel("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());
		cap.setBackground(Color.GREEN);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(new EmptyBorder(4, 4, 4, 4));
		p.add(counter);
		p.add(goal);
		p.add(cal);
		p.add(pts);
		p.add(cap);

		// attach GUI components to window
		window.getPanel().add(p);
		window.getPanel().add(day.getField());
		window.getPanel().add(statsPanel);
		window.getPanel().add(fridge);
		window.getPanel().add(lunchBox);
		createButtons();

		loadBackgroundImage();

		// sets size & positioning for GUI components
		statsPanel.setBounds(WIDTH / 2 - (statsPanel.getWidth() / 2), HEIGHT / 2 - statsPanel.getHeight() / 2, statsPanel.getWidth(),
				statsPanel.getHeight());
		p.setBounds(removeButton.getX(), removeButton.getY() + removeButton.getHeight() + 10, 120, 90);

		// must be called last or components won't be displayed
		window.getFrame().setVisible(true);

		String popupTxt = "<html><body width='220'>" + "<h1>Eat Healthy</h1>"
				+ "<p>Welcome to Eat Healthy. A game that simulates packing a lunch.</p><br>"
				+ "<p><u>How To Play:</u> &nbsp&nbsp Pick foods from the list on the right to pack into the lunch box."
				+ " Try picking an assortment of healthy foods to score big.</p>";

		// show how to play dialog on start
		JOptionPane.showMessageDialog(null, popupTxt);
	}

	public void createButtons() {

		int btnWidth = 120;
		int btnHeight = 40;
		Color btnColor = new Color(59, 89, 182);

		// initializes buttons
		addButton = new JButton("Add Food");
		removeButton = new JButton("Remove Food");
		packLunchButton = new JButton("Pack Lunch");
		randomButton = new JButton("Fill Random");

		// adds function to the add button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fridge.getItemList().getSelectedIndex() != -1 && fridge.listSize() > 0
						&& lunchBox.listSize() < lunchBox.getMaxCapacity()) {
					lunchBox.addItem(fridge.getSelectedItem());
					fridge.removeSelectedItem();

					cal.setText("Calories: " + lunchBox.getTotalCal());
					pts.setText("Points: " + lunchBox.getTotalPoints());
					
					// highlights food label if capacity reached
					if (lunchBox.listSize() == lunchBox.getMaxCapacity()) {
						cap.setOpaque(true);
					} else {
						cap.setOpaque(false);
					}
					cap.setText("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());
				}
			}
		});

		// adds function to the remove button
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lunchBox.getItemList().getSelectedIndex() != -1 && lunchBox.getModel().size() > 0) {
					fridge.addItem(lunchBox.getSelectedItem());
					lunchBox.removeSelectedItem();

					cal.setText("Calories: " + lunchBox.getTotalCal());
					pts.setText("Points: " + lunchBox.getTotalPoints());
					
					// highlights food label if capacity reached
					if (lunchBox.listSize() == lunchBox.getMaxCapacity()) {
						cap.setOpaque(true);
					} else {
						cap.setOpaque(false);
					}
					cap.setText("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());
				}
			}
		});

		// adds function to the pack lunch button
		packLunchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statsPanel.update();
				lunchBox.clearList();
				if (day.isFriday()) {
					fridge.fillRandom();
				}
				window.getPanel().remove(day.getField());
				day.change();
				window.getPanel().add(day.getField());

				cal.setText("Calories: " + lunchBox.getTotalCal());
				pts.setText("Points: " + lunchBox.getTotalPoints());
				
				// highlights food label if capacity reached
				if (lunchBox.listSize() == lunchBox.getMaxCapacity()) {
					cap.setOpaque(true);
				} else {
					cap.setOpaque(false);
				}
				cap.setText("Food: " + lunchBox.listSize() + " / " + lunchBox.getMaxCapacity());
			}
		});

		// adds function to the fill random button
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fridge.fillRandom();
				lunchBox.clearList();
			}
		});

		// sets color, size & positioning for buttons
		packLunchButton.setBounds(lunchBox.getX() + lunchBox.getWidth() / 2 - btnWidth / 2, 610, btnWidth, btnHeight);
		packLunchButton.setBackground(btnColor);
		packLunchButton.setForeground(Color.WHITE);
		addButton.setBounds(fridge.getX() + ListBox.DEFAULT_WIDTH / 2 - btnWidth / 2,
				fridge.getY() + fridge.getHeight(), btnWidth, btnHeight);
		addButton.setBackground(btnColor);
		addButton.setForeground(Color.WHITE);
		removeButton.setBounds(lunchBox.getX() + ListBox.DEFAULT_WIDTH / 2 - btnWidth / 2,
				lunchBox.getY() + lunchBox.getHeight(), btnWidth, btnHeight);
		removeButton.setBackground(btnColor);
		removeButton.setForeground(Color.WHITE);
		// randomButton.setBounds((WIDTH / 2) - (btnWidth / 2), HEIGHT / 2 +
		// btnWidth, btnWidth, btnHeight);

		window.getPanel().add(addButton);
		window.getPanel().add(removeButton);
		window.getPanel().add(packLunchButton);
	}

	public void loadBackgroundImage() {
		// Checks if background image exists before showing
		if (f.exists()) {
			ImageIcon background = new ImageIcon(path);
			JLabel label = new JLabel();
			label.setBounds(0, 0, WIDTH, HEIGHT);
			label.setIcon(background);

			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.add(label);

			window.getPanel().add(panel);
			panel.setBounds(0, 0, WIDTH, HEIGHT);
		}
	}

}
