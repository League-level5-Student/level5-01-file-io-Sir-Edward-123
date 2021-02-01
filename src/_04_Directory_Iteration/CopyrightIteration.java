package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class CopyrightIteration {
	public static void main(String[] args) {
		JFileChooser jfc = new JFileChooser("src");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		searchDirectory(jfc.getCurrentDirectory());
	}
	
	static void searchDirectory(File dir) {
		File[] files = dir.listFiles();
		if(files != null) {
			for(File f : files) {
				if(f.isDirectory()) {
					searchDirectory(f);
				} else {
					String fileName = f.getName();
					if(fileName.substring(fileName.length() - 5).equals(".java")) {
						try {
							FileWriter fw = new FileWriter(f, true);
							fw.write("\n\n//Copyright © 2019 Riley Zhu");
							fw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}


//Copyright © 2019 Riley Zhu