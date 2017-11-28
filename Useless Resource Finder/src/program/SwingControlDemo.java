package program;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import program.FileWalker;

public class SwingControlDemo {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	static int number = 0;
	public void NewFile(){
		try {
			
		      File file = new File("/URF-log.txt");

		      if (file.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }

	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}

	public SwingControlDemo() {
		prepareGUI();
	}

	public static void main(String[] args) {
		SwingControlDemo swingControlDemo = new SwingControlDemo();
		swingControlDemo.showTextFieldDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Useless Rosource Finder");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.LEFT);
		statusLabel.setSize(350, 100);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showTextFieldDemo() {
		
		headerLabel.setText("Find useless resource files in a project");
		String resourceDir = "/Users/Santander/projetos/wokspace-gapzero/gapzero-android/app/src/main/res/layout/";
		String classesDir = "/Users/Santander/projetos/wokspace-gapzero/gapzero-android/app/src/main/java/";
		String layoutDir = "/Users/Santander/projetos/wokspace-gapzero/gapzero-android/app/src/main/res/java/";
		
		JLabel namelabel = new JLabel("Resources directory: ", JLabel.LEFT);
		JLabel passwordLabel = new JLabel("Classes's directory: ", JLabel.LEFT);
		JLabel passwordLabel2 = new JLabel("Layout's directory: ", JLabel.LEFT);
		final JTextField userText = new JTextField(20);
		userText.setText(resourceDir);
		final JTextField passwordText = new JTextField(20);
		passwordText.setText(classesDir);
		final JTextField passwordText2 = new JTextField(20);
		passwordText2.setText(layoutDir);
		JButton loginButton = new JButton("Search");
		loginButton.addActionListener(new ActionListener() {
			JPanel container = new JPanel();
			JScrollPane scrPane = new JScrollPane(container);
			public void actionPerformed(ActionEvent e) {
				String data = "";
				FileWalker fw = new FileWalker();
		        List<File> images = fw.walk(resourceDir, 0);
		        FileWalker fwc = new FileWalker();
		        List<File> classes = fwc.walk(classesDir, 1);
		        FileWalker fwx = new FileWalker();
		        List<File> xml = fwc.walk(layoutDir, 2);
		        fw.Search(images, classes);
		        fw.Search(images, xml);
		        List<File> result = fw.Verification();
		        for (File fl : result) {
		            data += fl.getName();
		        }
		        System.out.println("fim, "+result.size()+" arquivos nao refereciandos em classes ou xml do total de "+images.size()+" arquivos");
			}
		});
		controlPanel.add(namelabel);
		controlPanel.add(userText);
		controlPanel.add(passwordLabel);
		controlPanel.add(passwordText);
		controlPanel.add(passwordLabel2);
		controlPanel.add(passwordText2);
		controlPanel.add(loginButton);
		mainFrame.setVisible(true);
	}
}