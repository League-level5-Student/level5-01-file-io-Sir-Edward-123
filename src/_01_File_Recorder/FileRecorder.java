package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("src/_01_File_Recorder/writeFile.txt");
			writer.write(JOptionPane.showInputDialog("What should be written to the file?"));
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}


//Copyright © 2019 Riley Zhu