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
            	
            	// exit method if no files found
            	if(!(files.length > 0)){
        			JOptionPane.showMessageDialog(this, "No save files found.");
        			return;
        		}
            	
            	// gets a list of filenames to display for user when entering a filename
            	String[] fileNames = new String[files.length]; 
            	for(int i = 0; i < files.length; i++){
            		File file = files[i];
            		if(file.isFile()){
            			fileNames[i] = file.getName().substring(0, file.getName().length() - 4);
            		}
            	}
            	
            	String filename = (String)JOptionPane.showInputDialog(this, 
            			"Select Save File:", 
            			"Open File", 
            			JOptionPane.PLAIN_MESSAGE, 
            			null, 
            			fileNames, 
            			fileNames[0]);
            	
            	
            	// exit if no filename selected
            	if(filename == null || filename.length() <= 0){
            		return;
            	}
            	
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("./saves/" + filename + ".dat"));
                profile = (User)in.readObject();
//                new EatHealthy().start(profile);
                // sets game components to values from save file
                handler.getGame().getFridge().setFoods(profile.getFridge());
                handler.getGame().getLunchBox().setFoods(profile.getLunchBox());
                handler.getGame().setProfile(profile);
                handler.getGame().setDay(profile.getDay());

                handler.getGame().setScore(profile.getWeeklyScore());
                handler.getGame().setHighScore(profile.getWeeklyHigh());
                handler.getGame().updateCalorieCounter();
                handler.changeGameState(State.GAME);
                in.close();
            }
            catch(Exception f) {
            	JOptionPane.showMessageDialog(this, "No save files found.");
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
