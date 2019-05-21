package fr.isiz.converter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowGui extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JTextField feetsField = new JTextField("");
	private JTextField centimeterField = new JTextField("");
	
	private JButton drawButton = new JButton("Convert");
	private JLabel result = new JLabel("");
	
	/**
	 * Call the constructor and open the window with the settings
	 */
	public WindowGui() {
		// Draw the Window
		this.setTitle("ConvertApplication create by IsiZ");
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		drawButton.addActionListener(this);
		drawButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		container.setBackground(Color.WHITE);
		container.setLayout(new BorderLayout());
		
		centimeterField.setPreferredSize(new Dimension(30, 30));
		feetsField.setPreferredSize(new Dimension(30, 30));
		
		JPanel top = new JPanel();
		top.add(new JLabel("Centimeter"));
		top.add(centimeterField);
		top.add(new JLabel("Feets"));
		top.add(feetsField);
		top.add(new JLabel(" "));
		top.add(drawButton);
		top.add(new JLabel(" "));
		top.add(new JLabel("|"));
		top.add(new JLabel("Result:"));
		top.add(result);
		
		container.add(top, BorderLayout.NORTH);
		this.setContentPane(container);
		
		// Finish at
		this.setVisible(true);
		
		validate();
	}

	@Override
	// Listener when the user use an button
	public void actionPerformed(ActionEvent event) {
		Object o = event.getSource();
		if (o == drawButton) {
			try {
				if (!centimeterField.getText().isEmpty() && feetsField.getText().isEmpty()) {
					double inputCm = Double.parseDouble(centimeterField.getText());
					result.setText(arrondi(convertCmToFeets(inputCm), 1) + " (Feets)");
					centimeterField.setText("");
					return;
				} else if (centimeterField.getText().isEmpty() && !feetsField.getText().isEmpty()){
					double inputFeets = Double.parseDouble(feetsField.getText());
					result.setText(arrondi(convertFeetsToCm(inputFeets), 1) + " (Centimeters)");
					feetsField.setText("");
					return;
				}
			} catch (NumberFormatException | NullPointerException e) {
				result.setText("Put an number !");
				return;
			}
		}
	}
	
	/*
	 * Calcul
	 * Basic value: 30,48 (Google)
	 */
	
	/**
	 * Value correct
	 * @param A
	 * @param B
	 * @return
	 */
	protected double arrondi(double A, int B) {
		return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}
	
	/**
	 * Convert the feets to the centimeters
	 * @param feets
	 * @return
	 */
	private double convertFeetsToCm(double feets) {
		return feets * 30.48D;
	}
	
	/**
	 * Convert the centimeters to the feets
	 * @param cm
	 * @return
	 */
	private double convertCmToFeets(double cm) {
		return cm / 30.48D;
	}
	
}
