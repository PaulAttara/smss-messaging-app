package ca.mcgill.ecse.smss.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
	private JLabel smssLabel;
	private JTextField smssTextField;
	private JButton smssCreateButton;
	
	private JLabel methodLabel;
	private JTextField methodTextField;
	private JButton methodCreateButton;
	
	// Sender
	private JLabel senderLabel;
	private JTextField senderTextField;
	private JButton senderCreateButton;

	// Class
	private JLabel classLabel;
	private JTextField classTextField;
	private JButton classCreateButton;
	private JComboBox<String> classTypesDropdown1;

	// Receiver
	private JLabel receiverLabel;
	private JTextField receiverTextField;
	private JComboBox<String> classTypesDropdown2;
	private JButton createReceiverButton;
	private JComboBox<String> receiverDropdown1;
	
	//messages
	private JLabel messageLabel;
	private JComboBox<String> senderDropdown;
	private JTextField messageTextfield;
	private JComboBox<String> receiverDropdown2;
	private JButton createMessageButton;

	private JLabel operandLabel;
	private JTextField operandCondition; 
	private JList messageList1;
	private JComboBox<String> operandDropdown;
	private JButton createOperandButton;

	private JLabel fragmentLabel;
	private JComboBox<String> fragmentTypeDropdown; 
	private JList operandList;
	private JList messageList2;
	private JButton createFragmentButton;
	
	private JTable overviewTable;
	private JScrollPane overviewScrollPane;
	
	private DefaultTableModel overviewDtm;
	private String overviewColumnNames[] = {"Editor"};
	private static final int HEIGHT_OVERVIEW_TABLE = 200;
	
	
	// data elements
	private String error = null;
	private HashMap<Integer, ClassType> classTypes;
 
	private JLabel emptyLabel;
	
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
		
		emptyLabel = new JLabel();
		// elements for smss
		smssLabel = new JLabel();
		smssTextField = new JTextField();
		smssCreateButton = new JButton();
		
		// elements for method
		methodLabel = new JLabel();
		methodTextField = new JTextField();
		methodCreateButton = new JButton();
		
		// elements for sender
		senderLabel = new JLabel();
		senderTextField = new JTextField();
		senderCreateButton = new JButton();
		
		// elements for class
		classLabel = new JLabel();
		classTextField = new JTextField();
		classCreateButton = new JButton();
		classTypesDropdown1 = new JComboBox<String>(new String[0]);
		
		receiverLabel = new JLabel();
		receiverTextField = new JTextField();
		createReceiverButton = new JButton();
		classTypesDropdown2 = new JComboBox<String>(new String[0]);
		receiverDropdown1 = new JComboBox<String>(new String[0]);
		
		messageLabel = new JLabel();
		senderDropdown = new JComboBox<String>(new String[0]);
		messageTextfield = new JTextField();
		receiverDropdown2 = new JComboBox<String>(new String[0]);
		createMessageButton = new JButton();
		
		operandLabel = new JLabel();
		operandCondition = new JTextField();
		messageList1 = new JList<String>();
		operandDropdown = new JComboBox<String>(new String[0]);
		createOperandButton = new JButton();
		
		fragmentLabel = new JLabel();
		fragmentTypeDropdown = new JComboBox<String>(new String[0]);
		operandList = new JList<String>();
		messageList2 = new JList<String>();
		createFragmentButton = new JButton();
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("SMSS Application");
		
		emptyLabel.setText("");

		// for method
		smssLabel.setText("SMSS Class Name: ");
		smssCreateButton.setText("Create SMSS");
		smssCreateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createMethodButtonActionPerformed(evt);
			}
		});
		
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
		
		// for receiver
		receiverLabel.setText("Receiver:");
		createReceiverButton.setText("Create Receiver");
		createReceiverButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createClassButtonActionPerformed(evt);
			}
		});

		// for message
		messageLabel.setText("Messages:");
		createMessageButton.setText("Create Message");
		createMessageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createClassButtonActionPerformed(evt);
			}
		});		

		// for operand
		operandLabel.setText("Operand:");
		createOperandButton.setText("Create Operand");
		createOperandButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createClassButtonActionPerformed(evt);
			}
		});		

		// for fragment
		fragmentLabel.setText("Fragments:");
		createFragmentButton.setText("Create Fragment");
		createFragmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createClassButtonActionPerformed(evt);
			}
		});		
		
		overviewTable = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (!c.getBackground().equals(getSelectionBackground())) {
					Object obj = getModel().getValueAt(row, column);
					if (obj instanceof java.lang.String) {
						String str = (String)obj;
						c.setBackground(str.endsWith("sick)") ? Color.RED : str.endsWith("repair)") ? Color.YELLOW : Color.WHITE);
					}
					else {
						c.setBackground(Color.WHITE);
					}
				}
				return c;
			}
		};

		overviewScrollPane = new JScrollPane(overviewTable);
		this.add(overviewScrollPane);
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineMiddle = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(errorMessage)
						.addComponent(horizontalLineTop)
						.addComponent(horizontalLineMiddle)
						.addComponent(horizontalLineBottom)
						.addComponent(overviewScrollPane)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(smssLabel)
										.addComponent(methodLabel)
										.addComponent(senderLabel)
										.addComponent(classLabel)
										.addComponent(receiverLabel)
										.addComponent(messageLabel)
										.addComponent(operandLabel)
										.addComponent(fragmentLabel))
								.addGroup(layout.createParallelGroup()
										.addComponent(smssTextField, 200, 200, 400)
										.addComponent(methodTextField, 200, 200, 400)
										.addComponent(senderTextField, 200, 200, 400)
										.addComponent(classTextField, 200, 200, 400)
										.addComponent(receiverTextField, 200, 200, 400)
										.addComponent(senderDropdown, 200, 200, 400)
										.addComponent(operandCondition, 200, 200, 400)
										.addComponent(fragmentTypeDropdown, 200, 200, 400))
								.addGroup(layout.createParallelGroup()
										.addComponent(smssCreateButton, 200, 200, 400)
										.addComponent(methodCreateButton, 200, 200, 400)
										.addComponent(senderCreateButton, 200, 200, 400)
										.addComponent(classCreateButton, 200, 200, 400)
										.addComponent(classTypesDropdown2, 200, 200, 400)
										.addComponent(messageTextfield, 200, 200, 400)
										.addComponent(messageList1, 200, 200, 400)
										.addComponent(operandList, 200, 200, 400))
								.addGroup(layout.createParallelGroup()
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(classTypesDropdown1, 200, 200, 400)
										.addComponent(createReceiverButton, 200, 200, 400)
										.addComponent(receiverDropdown2, 200, 200, 400)
										.addComponent(createOperandButton, 200, 200, 400)
										.addComponent(messageList2, 200, 200, 400))
								.addGroup(layout.createParallelGroup()
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(receiverDropdown1, 200, 200, 400)
										.addComponent(createMessageButton, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(createFragmentButton, 200, 200, 400))
										
										)));
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(errorMessage)
						.addGroup(layout.createParallelGroup()
								.addComponent(smssLabel)
								.addComponent(smssTextField)
								.addComponent(smssCreateButton)
								.addComponent(emptyLabel)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(methodLabel)
								.addComponent(methodTextField)
								.addComponent(methodCreateButton)
								.addComponent(emptyLabel)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(senderLabel)
								.addComponent(senderTextField)
								.addComponent(senderCreateButton)
								.addComponent(emptyLabel)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineTop))
						.addGroup(layout.createParallelGroup()
								.addComponent(classLabel)
								.addComponent(classTextField)
								.addComponent(classCreateButton)
								.addComponent(classTypesDropdown1)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(receiverLabel)
								.addComponent(receiverTextField)
								.addComponent(classTypesDropdown2)
								.addComponent(createReceiverButton)
								.addComponent(receiverDropdown1))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineMiddle))
						.addGroup(layout.createParallelGroup()
								.addComponent(messageLabel)
								.addComponent(senderDropdown)
								.addComponent(messageTextfield)
								.addComponent(receiverDropdown2)
								.addComponent(createMessageButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(operandLabel)
								.addComponent(operandCondition)
								.addComponent(messageList1)
								.addComponent(createOperandButton)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(fragmentLabel)
								.addComponent(fragmentTypeDropdown)
								.addComponent(operandList)
								.addComponent(messageList2)
								.addComponent(createFragmentButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineBottom))
						.addComponent(overviewScrollPane))
				
				);
		pack();

							
	}



	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// clear text fields
			methodTextField.setText("");
			senderTextField.setText("");			
			classTextField.setText("");
			
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
		
		
		// editor table
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(overviewColumnNames);
		overviewTable.setModel(overviewDtm);
				
				/*for (RouteAssignment assignment : BtmsController.getAssignmentsForDate((Date) overviewDatePicker.getModel().getValue())) {
						BusVehicle bus = assignment.getBus();
						String busText = bus.getLicencePlate();
						if (bus.getRepairStatus().equals(RepairStatus.InRepairShop)) {
							busText = busText + " (in repair)";
						}
						if (assignment.getDriverSchedules().size() == 0) {
							Object[] obj = {assignment.getRoute().getNumber(), busText, "---", "---"};
							overviewDtm.addRow(obj);
						}
						else {
							for (DriverSchedule schedule : assignment.getDriverSchedules()) {
								Driver driver = schedule.getDriver();
								String driverText = "#" + driver.getId() + " " + driver.getName();
								if (driver.getSickStatus().equals(SickStatus.Sick)) {
									driverText = driverText + " (sick)";
								}
								Object[] obj = {assignment.getRoute().getNumber(), busText, schedule.getShift(), driverText};
								overviewDtm.addRow(obj);
							}
						}
					}*/
				
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
					
		pack();
	}
		
	// button listeners
	private void createMethodButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			SmssController.createMethod(methodTextField.getText());
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
			SmssController.createClassType(senderTextField.getText());
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
			SmssController.createSender(senderTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
		


}
