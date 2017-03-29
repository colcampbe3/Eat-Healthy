package eathealthy;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame frame;
	private JPanel panel;
	private String title;
	private int width, height;
	private BufferedImage background;
	private String path = "/background/fridge_closed.jpg";
	private File f = new File("res" + path);

	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;

		createWindow();
	}

	@SuppressWarnings("serial")
	public void createWindow() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		panel = new JPanel() {

			// draws background image
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				// load image only if file exists
				if (f.exists()) {
					try {
						background = ImageIO.read(getClass().getResource(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (background != null) {
						g.drawImage(background, 0, 0, null);
					}
				}

			}
		};

		// sets panel's size
		panel.setMinimumSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
		panel.setPreferredSize(new Dimension(width, height));
		// enables manual positioning for all components on frame
//		panel.setLayout(null);
		
		FlowLayout f = (FlowLayout) panel.getLayout();
		f.setVgap(0); // fixes small gap on top of panel
		
		panel.setFocusable(false);

		frame.add(panel);
		// frame.pack();
		// frame.setVisible(true);
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public void attach(Component c){
		panel.add(c);
	}

	public JFrame getFrame() {
		return frame;
	}
}
