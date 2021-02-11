package ca.mcgill.ecse.smss.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.smss.controller.SmssController;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.model.ClassType;
import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.controller.InvalidInputException;

public class SmssPage extends JFrame {
	
	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JLabel errorMessage;	
	
	// SMSS: going to remove smss category completely: no need for it.
//	private JLabel ssmsLabel;
//	private JTextField ssmsNameTextField;
//	private JButton ssmsCreateButton;
//	private JLabel ssmsNameLabel;

	// Method
	private JLabel methodLabel;
	private JTextField methodNameTextField;
	private JButton methodCreateButton;
	private JLabel methodNameLabel;
	
	// Sender
	private JLabel senderLabel;
	private JTextField senderNameTextField;
	private JButton senderCreateButton;
	private JLabel senderNameLabel;

	// Class
	private JLabel classLabel;
	private JTextField classNameTextField;
	private JButton classCreateButton;
	private JComboBox<String> classTypes1;

	// Receiver
	private JLabel receiverLabel1;
	private JTextField receiverNameTextField;
	private JComboBox<String> classTypes2;
	private JButton senderReceiverButton;
	private JLabel receiverLabel2;
	private JComboBox<String> receiverList;
	
	// Messages...
	// Fragments...
	

	// data elements
	private String error = null;
	private HashMap<Integer, ClassType> classTypes;

	public SmssPage() throws InvalidInputException {
		initComponents();
		refreshData();
		try {
			SmssController.createSmss();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
	}
	
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);		
		
		// elements for smss
//		ssmsLabel = new JLabel();
//		ssmsNameTextField = new JTextField();
//		ssmsCreateButton = new JButton();
//		ssmsNameLabel = new JLabel();
		
		// elements for method
		methodLabel = new JLabel();
		methodNameTextField = new JTextField();
		methodCreateButton = new JButton();
		methodNameLabel = new JLabel();
		
		// elements for sender
		senderLabel = new JLabel();
		senderNameTextField = new JTextField();
		senderCreateButton = new JButton();
		senderNameLabel = new JLabel();
		
		// elements for class
		classLabel = new JLabel();
		classNameTextField = new JTextField();
		classCreateButton = new JButton();
		classTypes1 = new JComboBox();
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("SMSS Application");
		
		// for method
		methodLabel.setText("Method:");
		methodCreateButton.setText("Create Method");
		methodCreateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createMethodButtonActionPerformed(evt);
			}
		});
		
		// for sender
		senderLabel.setText("Sender:");
		senderCreateButton.setText("Create Sender");
		senderCreateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createSenderButtonActionPerformed(evt);
			}
		});
		
		// for class
		classLabel.setText("Sender:");
		classCreateButton.setText("Create Sender");
		classCreateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createClassButtonActionPerformed(evt);
			}
		});
		
		
		// UI Layout

		pack();

							
	}



	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// clear text fields
			methodNameTextField.setText("");
			senderNameTextField.setText("");			
			classNameTextField.setText("");
			
			// set values of labels 
//			methodNameLabel.setText(SmssController.getMethodName());
			//senderNameLabel.setText(SmssController.getSenderName());
			
			// set values for combo boxes
			classTypes = new HashMap<Integer, ClassType>();
			Integer index = 0;
			for(ClassType classType : SmssController.getClassTypes()) {
				classTypes.put(index, classType);
				index++;
			}
		
		}
		pack();
	}
		
	// button listeners
	private void createMethodButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			SmssController.createMethod(methodNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
				
		// update visuals
		refreshData();
	}
	
	private void createClassButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			SmssController.createClassType(senderNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	
	private void createSenderButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			SmssController.createSender(senderNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
		


}
