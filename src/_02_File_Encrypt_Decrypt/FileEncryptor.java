package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a messager.
	 * Use any key you want (1 - 25) to shift each letter in the users input and save the final result to a file.
	 */
	static int shift = 3;
	
	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("src/_02_File_Encrypt_Decrypt/writeFile.txt");
			String message = JOptionPane.showInputDialog("What message should be encrypted?");
			String encryptedMessage = "";
			
			for(int i = 0; i < message.length(); i++) {
				int asciiValue = (int)message.charAt(i);
				
				if(asciiValue >= 97 && asciiValue <= 122) {
					for(int j = 0; j < shift; j++) {
						asciiValue++;
						if(asciiValue > 122) {
							asciiValue = 97;
						}
					}
				} else if(asciiValue >= 65 && asciiValue <= 90) {
					for(int j = 0; j < shift; j++) {
						asciiValue++;
						if(asciiValue > 90) {
							asciiValue = 65;
						}
					}
				}
				encryptedMessage += Character.toString((char)asciiValue);
			}
			writer.write(encryptedMessage);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
