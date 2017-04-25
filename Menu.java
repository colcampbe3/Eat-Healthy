/**
 * Created by Rick on 3/29/2017.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

    @SuppressWarnings("serial")
    public class Menu extends JPanel {

        private JButton start, cont;
        private int width, height;
        private UserInfo userInfo;
        private Handler handler;
        private BufferedImage background;
        private String path = "/background/title_screen.jpg";
        private File f = new File("res" + path);

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

        /*
         * Enables continue button based on if save file exists
         */

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

        private void contBtnOnClick(ActionEvent e){
            User profile;
            try {
            	// Looks thru save folder for any files ending in .dat using FilenameFilter and stores in a files array
            	File[] files = new File("./saves").listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.endsWith(".dat");
					}
				});
            	
            	// gets a list of filenames to display for user when entering a filename
            	String fileNames = null;
            	for (File file : files) {
            	    if (file.isFile()) {
            	    	if(fileNames == null)
            	    		fileNames = file.getName();
            	    	else
            	    		fileNames = fileNames.concat("\n" + file.getName());
            	    	System.out.println(file.getName());
            	    }
            	}
            	System.out.println("fileNames: " + fileNames);
            	String filename = JOptionPane.showInputDialog("Enter name of file:\n " + fileNames);
            	
            	// truncates input if user entered .dat in filename
            	if(filename.endsWith(".dat")){
            		filename = filename.substring(0, filename.length() - 4);
            		System.out.println(filename);
            	}
            	
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("./saves/" + filename + ".dat"));
                profile = (User)in.readObject();
//                new EatHealthy().start(profile);
                // sets game components to values from save file
                handler.getGame().getFridge().setFoods(profile.getFridge());
                handler.getGame().getLunchBox().setFoods(profile.getLunchBox());
                handler.getGame().setProfile(profile);
                handler.getGame().setDay(profile.getDay());
                handler.getGame().setPoints();

                handler.getGame().updateCalorieCounter();
                handler.changeGameState(State.GAME);
                in.close();
            }
            catch(Exception f) {
                System.out.println("The File Could Not Be Loaded.");
            }
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Font fnt = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt);
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);
            
            // load image only if file exists and build path is set for image folder
            if (f.exists() && getClass().getResource(path) != null) {
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
//            g.setColor(Color.black);
//            g.setFont(new Font("arial", Font.BOLD, 52));
//            g.drawString("Eat Healthy", width/2 - 130 - 2, 180 + 2);
            g.setColor(Color.white);
            g.setFont(fnt);
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
