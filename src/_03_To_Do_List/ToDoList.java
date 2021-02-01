package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	JFrame window;
	JPanel panel;
	JButton addButton, viewButton, removeButton, saveButton, loadButton;
	
	ArrayList<String> toDoList;
	
	
	public static void main(String[] args) {
		new ToDoList().setup();
		
	}
	
	void setup() {
		toDoList = new ArrayList<String>();
		
		window = new JFrame("To Do List");
		panel = new JPanel();
		addButton = new JButton("Add Task");
		viewButton = new JButton("View Tasks");
		removeButton = new JButton("Remove Task");
		saveButton = new JButton("Save Tasks");
		loadButton = new JButton("Load Tasks");
		
		addButton.addActionListener(this);
		viewButton.addActionListener(this);
		removeButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		
		panel.add(addButton);
		panel.add(viewButton);
		panel.add(removeButton);
		panel.add(saveButton);
		panel.add(loadButton);
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}
	
	void addTask() {
		toDoList.add(JOptionPane.showInputDialog("Enter the task you would like to add."));
	}
	
	void viewTasks() {
		String tasksToPrint = "Tasks: \n";
		
		if(toDoList.size() > 0) {
			for(int i = 0; i < toDoList.size(); i++) {
				tasksToPrint += (i + 1) + ". " + toDoList.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, tasksToPrint);
		} else {
			JOptionPane.showMessageDialog(null, "No Tasks Currently");
		}
	}
	
	void removeTask() {
		try {
			toDoList.remove(Integer.parseInt(JOptionPane.showInputDialog("Enter the task number to remove.")) - 1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid Number or Non-Number Entered. Try Again.");
		}
	}
	
	void saveTasks() {
		try {
			FileWriter writer = new FileWriter("src/_03_To_Do_List/toDoList.txt");
			for(int i = 0; i < toDoList.size(); i++) {
				writer.write(toDoList.get(i) + "\n");
			}
			writer.close();
			JOptionPane.showMessageDialog(null, "Tasks Saved Successfully");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: Save file not found.");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: Error while writing to save file.");
		}
	}
	
	void loadTasks() {
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader("src/_03_To_Do_List/toDoList.txt"));
			toDoList.clear();
			
			String line = buffReader.readLine();
			while(line != null) {
				toDoList.add(line);
				line = buffReader.readLine();
			}
			buffReader.close();
			JOptionPane.showMessageDialog(null, "Tasks Loaded Successfully");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: Save file not found.");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: Error while writing to save file.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			addTask();
		} else if(e.getSource() == viewButton) {
			viewTasks();
		} else if(e.getSource() == removeButton) {
			removeTask();
		} else if(e.getSource() == saveButton) {
			int option = JOptionPane.showConfirmDialog(null, "Warning: Saving Tasks will overwrite all tasks in save file. Continue?");
			if(option == 0) {
				saveTasks();
			} else if (option == 1 || option == 2) {
				JOptionPane.showMessageDialog(null, "Save Cancelled");
			}
		} else if(e.getSource() == loadButton) {
			int option = JOptionPane.showConfirmDialog(null, "Warning: Loading Tasks will overwrite all current tasks. Continue?");
			if(option == 0) {
				loadTasks();
			} else if (option == 1 || option == 2) {
				JOptionPane.showMessageDialog(null, "Load Cancelled");
			}
		}
	}
}


//Copyright © 2019 Riley Zhu