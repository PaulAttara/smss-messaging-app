package ca.mcgill.ecse.smss.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SmssPage extends JFrame {
	
	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JLabel errorMessage;	
	private JLabel sender;
	
	// data elements
	private String error = null;
	

	public SmssPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);		
		
		// elements for sender
		sender = new JLabel();
	}
	
	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
		
	}



}
}