package eathealthy;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	public JFrame frame;
	private JPanel panel;
	private String title;
	private int width, height;

	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;

		createWindow();
	}

	public void createWindow() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		
		// sets panel's size
		panel.setMinimumSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
		panel.setPreferredSize(new Dimension(width, height));
		// enables manual positioning for all components on frame
		panel.setLayout(null);
		panel.setFocusable(false);

		frame.add(panel);
//		frame.pack();
//		frame.setVisible(true);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
