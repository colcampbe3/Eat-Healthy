package eathealthy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {

	private JButton start, cont;
	private int width, height;
	private UserInfo userInfo;
	private Handler handler;
	
	public Menu(int width, int height, Handler handler){
		
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		
		setOpaque(false);
		
		initComponents();
	}

	private void initComponents() {
		Color btnColor = new Color(59, 89, 182);
		
		start = new JButton("NEW GAME");
		start.setFont(new Font("arial", 1, 20));
		start.setBackground(btnColor);
		start.setForeground(Color.white);
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				startBtnOnClick(e);
			}
			
		});
		
		cont = new JButton("CONTINUE");
		cont.setFont(new Font("arial", 1, 20));
		cont.setBackground(btnColor);
		
		// sets continue button available based on if save file exists
		enableContBtn();
		
		cont.setForeground(Color.white);
		
		cont.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				contBtnOnClick(e);
			}
			
		});
		
		JPanel j = new JPanel(new GridLayout(2, 1, 2, 10));
		j.setOpaque(false);
		j.add(start);
		j.add(cont);
		
		setLayout(new GridBagLayout());
		
		add(j);
	}
	
	private boolean fileExists(){
		return FileTools.fileExists("saveFile");
	}
	
	/*
	 * Enables continue button based on if save file exists
	 */
	public void enableContBtn(){
		if(fileExists()){
			cont.setEnabled(true);
		} else {
			cont.setEnabled(false);
		}
	}
	
	/*
	 * Opens user input frame
	 */
	private void startBtnOnClick(ActionEvent e){
		
		if(userInfo == null){
			userInfo = new UserInfo(handler);
	        userInfo.setVisible(true);
		} else {
			userInfo.setVisible(true);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void contBtnOnClick(ActionEvent e){
		Object[] tempObject;
		User tempUser = null;
		ArrayList<FoodObject> tempFridge;
		ArrayList<FoodObject> tempLunch;
		String tempDay;
		
		tempObject = FileTools.readFile(FileTools.DEFAULT_FILENAME);
		
		tempUser = (User) tempObject[0]; // contains player class
		tempFridge = (ArrayList<FoodObject>) tempObject[1]; // contains fridge list
		tempLunch = (ArrayList<FoodObject>) tempObject[2]; // contains lunch box list
		tempDay = (String) tempObject[3]; // contains day string
		
		// sets game components to values from save file
		handler.getGame().getFridge().setFoods(tempFridge);
		handler.getGame().getLunchBox().setFoods(tempLunch);
		handler.getGame().setProfile(tempUser);
		handler.getGame().setDay(tempDay);
		
		handler.getGame().updateCalorieCounter();
		handler.changeGameState(State.GAME);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font fnt = new Font("arial", 1, 50);
		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.white);
		g.drawString("Eat Healthy", width/ 2 - 130, 180);
	}

	// GET & SET METHODS
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
