package eathealthy;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class EHPanel {

	protected int width, height;
	protected JPanel panel;
	
	public EHPanel(int width, int height){
		this.width = width;
		this.height = height;
		
		panel = new JPanel();
		setSize(width, height);
	}
	
	public abstract void initComponents();
	
	public void setSize(int width, int height){
		panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
	}
	
	public void attach(Component c){
		panel.add(c);
	}
	
	public JPanel getPanel(){
		return panel;
	}

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
