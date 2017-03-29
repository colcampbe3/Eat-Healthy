package eathealthy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileTools {

	public static final String DEFAULT_FILENAME = "saveFile";
	
	public static void writeObjectToFile(String fileName, Object[] object) {
		File f = new File(fileName);
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(object);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Object[] readFile(String fileName) {
		Object[] temp = new Object[4]; // read in an array that holds user, fridgelist, lunchList, and day
		File f = new File(fileName);
		
		if (f.exists()) {
			ObjectInputStream ois;
			
			try {
				ois = new ObjectInputStream(new FileInputStream(f));
				temp = (Object[]) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// return object;
			return temp;
		} else {
			return null;
		}

	}

	public static boolean fileExists(String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

}
