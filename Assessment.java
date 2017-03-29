package eathealthy;

import javax.swing.JButton;
import javax.swing.JLabel;

import eathealthy.ListBox.Nutrient;

public class Assessment extends EHPanel {

	private Handler handler;
	private JButton closeBtn;
	private JLabel calLabel, protLabel, vitaCLabel, vitaKLabel, sodLabel, sugLabel, pointsLabel;
	private double totalCals, totalPoints, totalSodium, totalSugar, totalProtein, totalVitaC, totalVitaK;

	public Assessment(int width, int height, Handler handler) {
		super(width, height);
		this.handler = handler;

		initComponents();
	}

	@Override
	public void initComponents() {

		calLabel = new JLabel("Calories: ");
		protLabel = new JLabel("Protein: ");
		vitaCLabel = new JLabel("Vitamin C: ");
		vitaKLabel = new JLabel("Vitamin K: ");
		sodLabel = new JLabel("Sodium: ");
		sugLabel = new JLabel("Sugar: ");
		pointsLabel = new JLabel("Points: ");

		closeBtn = new JButton("Close");

		attach(calLabel);
		attach(protLabel);
		attach(vitaCLabel);
		attach(vitaKLabel);
		attach(sodLabel);
		attach(sugLabel);
		attach(pointsLabel);
		attach(closeBtn);

	}

	public void update() {
		ListBox tempList = handler.getGame().getLunchBox();
		
		totalCals = handler.getGame().getLunchBox().getTotalCal();
		totalProtein = handler.getGame().getLunchBox().getTotal(Nutrient.PROTEIN);
		totalSodium = handler.getGame().getLunchBox().getTotal(Nutrient.SODIUM);
		totalPoints = tempList.getTotalPoints(handler.getUser().getWeight(), handler.getUser().getAge(), handler.getUser().getSex());
		
		calLabel.setText("Calories: " + totalCals);
		protLabel.setText("Protein: " + totalProtein);
		vitaCLabel.setText("Vitamin C: " + totalVitaC);
		vitaKLabel.setText("Vitamin K: " + totalVitaK);
		sodLabel.setText("Sodium: " + totalSodium);
		sugLabel.setText("Sugar: " + totalSugar);
		pointsLabel.setText("Points: " + totalPoints);
	}
}
