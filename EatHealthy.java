package eathealthy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EatHealthy {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9; // aspect ratio 12:9 format

	private int btnWidth = 120;
	private int btnHeight = 40;

	private String path = "res/background/fridgeScene.jpg";
	private File f = new File(path);

	/*
	 * Method that starts the game by initializing frame and all components
	 */
	public void start() {
		// creates all food objects
		FoodAssets.init();

		// creates frame
		JFrame frame = new JFrame("Eat Healthy");

		// initializes buttons
		JButton addButton = new JButton("Add Food");
		JButton removeButton = new JButton("Remove Food");
		JButton packLunchButton = new JButton("Pack Lunch");
		JButton randomButton = new JButton("Fill Random");

		// initializes scrollable lists for fridge and lunchbox
		ListBox fridge = new ListBox();
		ListBox lunchBox = new ListBox();

		Timer day = new Timer();

		FoodStatsPanel statsPanel = new FoodStatsPanel(day, lunchBox);

		// fills fridge list with random foods on startup
		fridge.fillRandom();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// close operation

		// sets frame's size
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // positions window in middle of
											// screen instead of in left corner

		// adds function to the add button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fridge.getItemList().getSelectedIndex() != -1 && fridge.getModel().size() > 0) {
					lunchBox.addItem(fridge.getSelectedItem());
					fridge.removeSelectedItem();
				}

			}
		});

		// adds function to the remove button
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lunchBox.getItemList().getSelectedIndex() != -1 && lunchBox.getModel().size() > 0) {
					fridge.addItem(lunchBox.getSelectedItem());
					lunchBox.removeSelectedItem();
				}
			}
		});

		// adds function to the pack lunch button
		packLunchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ArrayList<FoodObject> stuff = lunchBox.getFoods();
				// System.out.printf("\n%-24s %8s %12s\n", "Name", "Calories",
				// "Points");
				// System.out.println("-------------------------------------------------");
				// for (int i = 0; i < stuff.size(); i++) {
				// System.out.printf("%-24s %8s %12s\n", stuff.get(i).getName(),
				// stuff.get(i).getCalories(),
				// stuff.get(i).getValue());
				// }
				statsPanel.update();
				lunchBox.clearList();
				if (day.isFriday()) {
					fridge.fillRandom();
				}
				frame.remove(day.getField());
				day.change();
				frame.add(day.getField());
			}
		});

		// adds function to the fill random button
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fridge.fillRandom();
				lunchBox.clearList();
			}
		});

		// enables manual positioning for all components on frame
		frame.setLayout(null);

		// attach GUI components to frame
		frame.add(statsPanel);
		frame.add(fridge);
		frame.add(lunchBox);
		frame.add(addButton);
		frame.add(removeButton);
		frame.add(packLunchButton);
		frame.add(randomButton);
		frame.add(day.getField());

		// Checks if background image exists before showing
		if (f.exists()) {
			ImageIcon background = new ImageIcon(path);
			JLabel label = new JLabel();
			label.setBounds(0, 0, WIDTH, HEIGHT);
			label.setIcon(background);

			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.add(label);

			frame.add(panel);
			panel.setBounds(0, 0, WIDTH, HEIGHT);
			panel.setBackground(Color.BLACK);
		}

		// sets size & positioning for buttons
		addButton.setBounds(WIDTH / 2 - 300, HEIGHT / 2 - 60, btnWidth, btnHeight);
		removeButton.setBounds(WIDTH / 2 - 300, HEIGHT / 2, btnWidth, btnHeight);
		packLunchButton.setBounds(20 + (lunchBox.WIDTH / 2 - btnWidth / 2), HEIGHT / 2 + 120, btnWidth, btnHeight);
		randomButton.setBounds((WIDTH / 2) - (btnWidth / 2), HEIGHT / 2 + btnWidth, btnWidth, btnHeight);

		// sets size & positioning for GUI components
		fridge.setBounds(WIDTH / 2 - (fridge.WIDTH / 2), HEIGHT / 2 - (fridge.HEIGHT / 2 + 50), fridge.WIDTH,
				fridge.HEIGHT);
		lunchBox.setBounds(20, HEIGHT / 2 - (lunchBox.HEIGHT / 2 + 50), lunchBox.WIDTH, lunchBox.HEIGHT);
		statsPanel.setBounds(WIDTH / 2 - (statsPanel.getWidth() / 2), HEIGHT / 2 + 200, statsPanel.getWidth(),
				statsPanel.getHeight());

		frame.setVisible(true);

		String popupTxt = "<html><body width='220'>"
				+ "<h1>Eat Healthy</h1>"
				+ "<p>Welcome to Eat Healthy. A game that simulates packing a lunch.</p><br>"
				+ "<p><u>How To Play:</u> &nbsp&nbsp Pick foods from the list on the right to pack into the lunch box."
				+ " Try picking an assortment of healthy foods to score big.</p>";

		// show how to play dialog on start
		JOptionPane.showMessageDialog(null, popupTxt);
	}

	public static void main(String[] args) {

		new EatHealthy().start();

	}

}
