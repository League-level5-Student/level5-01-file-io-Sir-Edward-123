package _02_File_Encrypt_Decrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("src/_02_File_Encrypt_Decrypt/writeFile.txt");
			String decryptedMessage = "";
			int shift = FileEncryptor.shift;
			
			int asciiValue = reader.read();
			while(asciiValue != -1) {
				if(asciiValue >= 97 && asciiValue <= 122) {
					for(int j = 0; j < shift; j++) {
						asciiValue--;
						if(asciiValue < 97) {
							asciiValue = 122;
						}
					}
				} else if(asciiValue >= 65 && asciiValue <= 90) {
					for(int j = 0; j < shift; j++) {
						asciiValue--;
						if(asciiValue < 65) {
							asciiValue = 90;
						}
					}
				}
				decryptedMessage += Character.toString((char)asciiValue);
				asciiValue = reader.read();
			}
			JOptionPane.showMessageDialog(null, "The decrypted message is:\n" + decryptedMessage);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


//Copyright © 2019 Riley Zhu