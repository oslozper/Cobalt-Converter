/*
Course:				CP1340 - Object Oriented Programming
Lab 5:				GUI and Swing
File:				CConverter.java
Description:		Cobalt Converter app that creates a GUI environment, creates a HalfLife object,
					calculates the amount left and returns the value.
Date:				November 26, 2020
Name:				OSCAR LOZANO-PEREZ
Student Number:		20164974
*/

// GUI libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CConverter extends JFrame {

	// Panel objects
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;

	// Static Label objects
	private JLabel amountLabel;
	private JLabel yearsLabel;

	// Mutable Labels
	private JLabel resultLabel;

	// Textfield objects
	private JTextField amountText;
	private JTextField yearsText;

	// Button objects
	private JButton computeButton;
	private JButton quitButton;

	// Icon object (presented on APP window)
	private ImageIcon cConverterAppIcon;

	// decimal flag variable
	private Boolean decimalExistsAmount = false;
	private Boolean decimalExistsYear = false; 

	public CConverter(){
		// Window Title
		super("Cobalt Converter");

		// Sets the application icon
		cConverterAppIcon = new ImageIcon("Images/Cobalt60.jpg");
		setIconImage(cConverterAppIcon.getImage());

		// Application methods to be executed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildGUI();
		pack();
		setVisible(true);
	}

	// Main Method creating an App object
	public static void main(String[] args) throws Exception{
		CConverter cc = new CConverter();
	}

	// Creates all the GUI objects
	private void buildGUI(){		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());


		// TOP Section
		topPanel = new JPanel();

		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcT = new GridBagConstraints();

		gbcT.fill = GridBagConstraints.NONE;
		gbcT.insets = new Insets(10, 10, 10, 10);

		// Amount Label
		gbcT.gridx = 0;
		gbcT.gridy = 0;
		gbcT.gridwidth = 1;
		gbcT.gridheight = 1;

		amountLabel = new JLabel();
		amountLabel.setText("Amount of Cobalt");
		topPanel.add(amountLabel, gbcT);

		// Amount TextField
		gbcT.gridx = 1;
		gbcT.gridy = 0;
		gbcT.gridwidth = 1;
		gbcT.gridheight = 1;

		amountText = new JTextField();
		amountText.setPreferredSize(new Dimension(100,25));
		topPanel.add(amountText, gbcT);
		amountText.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e){
				decimalAmount(amountText.getText());
						char c = e.getKeyChar();
				try{
					if(!(Character.isDigit(c) || 
						(c==KeyEvent.VK_BACK_SPACE) || 
						(c==KeyEvent.VK_DELETE) ||
						(c=='.' && decimalExistsAmount == false))){
						e.consume();
					}
				}
				catch(Exception ee){
					System.out.println("Error");
				}
			}
		});

		// Years Label
		gbcT.gridx = 0;
		gbcT.gridy = 1;
		gbcT.gridwidth = 1;
		gbcT.gridheight = 1;

		yearsLabel = new JLabel();
		yearsLabel.setText("Number of Years");
		topPanel.add(yearsLabel, gbcT);

		// Years TextField
		gbcT.gridx = 1;
		gbcT.gridy = 1;
		gbcT.gridwidth = 1;
		gbcT.gridheight = 1;

		yearsText = new JTextField();
		yearsText.setPreferredSize(new Dimension(100,25));
		topPanel.add(yearsText, gbcT);
		yearsText.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e){
				decimalYear(yearsText.getText());
						char c = e.getKeyChar();
				try{
					if(!(Character.isDigit(c) || 
						(c==KeyEvent.VK_BACK_SPACE) || 
						(c==KeyEvent.VK_DELETE) ||
						(c=='.' && decimalExistsYear == false))){
						e.consume();
					}
				}
				catch(Exception ee){
					System.out.println("Error");
				}
			}
		});

		c.add(topPanel, BorderLayout.NORTH);

		// CENTER Section

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbcC = new GridBagConstraints();
		gbcC.fill = GridBagConstraints.NONE;
		gbcC.insets = new Insets(10,10,10,10);

		gbcC.gridx = 0;
		gbcC.gridy = 0;
		gbcC.gridwidth = 1;
		gbcC.gridheight = 1;

		computeButton = new JButton();
		computeButton.setText("Compute");
		centerPanel.add(computeButton,gbcC);
		ComputeButtonListener cbl = new ComputeButtonListener();
		computeButton.addActionListener(cbl);

		gbcC.gridx = 1;
		gbcC.gridy = 0;
		gbcC.gridwidth = 1;
		gbcC.gridheight = 1;

		quitButton = new JButton();
		quitButton.setText("Quit");
		centerPanel.add(quitButton,gbcC);
		QuitButtonListener qbl = new QuitButtonListener();
		quitButton.addActionListener(qbl);

		c.add(centerPanel, BorderLayout.CENTER);

		// Bottom Section

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbcB = new GridBagConstraints();
		gbcB.fill = GridBagConstraints.NONE;
		gbcB.insets = new Insets(10,10,10,10);

		gbcB.gridx = 0;
		gbcB.gridy = 0;
		gbcB.gridwidth = 2;
		gbcB.gridheight = 1;

		resultLabel = new JLabel();
		resultLabel.setText("Amount Left: ");
		bottomPanel.add(resultLabel,gbcB);

		c.add(bottomPanel, BorderLayout.SOUTH);

	}

	// Computes the Amount left
	private class ComputeButtonListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent event){
			calculate();
		}
	}

	// Quits the Application
	private class QuitButtonListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent event){
			System.exit(0);
		}
	}

	// Creates a HalfLife object to calculate the amount left and set it in the resultLabel
	public void calculate(){
		//if(amountText.)
		double initial = Double.parseDouble(amountText.getText());
		double percentage = 0.12;
		double years = Double.parseDouble(yearsText.getText());

		HalfLife cobalt60 = new HalfLife(initial, percentage, years);
		resultLabel.setText("Amount Left: " + cobalt60.getValue());
	}

	// Checks if there already exists a decimal in the amount textfield
	public void decimalAmount(String aString){
		for(int i = 0 ; i<aString.length();i++){
			if(aString.charAt(i) == '.'){
				decimalExistsAmount = true;
				break;
			}
			else
				decimalExistsAmount = false;
		}
	}

	// Checks if there already exists a decimal in the year textfield
	public void decimalYear(String aString){
		for(int i = 0 ; i<aString.length();i++){
			if(aString.charAt(i) == '.'){
				decimalExistsYear = true;
				break;
			}
			else
				decimalExistsYear = false;
		}
	}
}