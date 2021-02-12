package ca.mcgill.ecse.smss.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.HashMap;

import javax.swing.DefaultListModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse.smss.controller.SmssController;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.model.SenderObject;
import ca.mcgill.ecse.smss.model.ClassType;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.ReceiverObject;
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
	
	private int selectedClassType1 = -1;
	private int selectedClassType2 = -1;
	private int selectedReceiver1 = -1;
	private int selectedReceiver2 = -1;
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
	
    private DefaultListModel<String> listModel;

	private HashMap<Integer, ClassType> classes;
	private HashMap<Integer, ReceiverObject> receivers;

	public SmssPage() throws InvalidInputException {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);	
		
		emptyLabel = new JLabel();
		
		 listModel = new DefaultListModel<String>();
	     
		
		
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
		
		classTypesDropdown1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedClassType1 = cb.getSelectedIndex();
			}
		});
		
		
		receiverLabel = new JLabel();
		receiverTextField = new JTextField();
		createReceiverButton = new JButton();
		classTypesDropdown2 = new JComboBox<String>(new String[0]);
		receiverDropdown1 = new JComboBox<String>(new String[0]);
		
		receiverDropdown1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedReceiver1 = cb.getSelectedIndex();
			}
		});
		
		classTypesDropdown2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedClassType2 = cb.getSelectedIndex();
			}
		});
		
		messageLabel = new JLabel();
		senderDropdown = new JComboBox<String>(new String[0]);
		messageTextfield = new JTextField();
		receiverDropdown2 = new JComboBox<String>(new String[0]);
		createMessageButton = new JButton();
		
		receiverDropdown2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedReceiver2 = cb.getSelectedIndex();
			}
		});
		
		operandLabel = new JLabel();
		operandCondition = new JTextField();
		operandDropdown = new JComboBox<String>(new String[0]);
		createOperandButton = new JButton();
		
		messageList1 = new JList<String>(listModel);
        messageList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );

        messageList1.setSelectedIndex(0);
        //messageList1.addMessageListSelectionListener(this);
        messageList1.setVisibleRowCount(5);
        JScrollPane messageListScrollPane = new JScrollPane(messageList1);
		
		fragmentLabel = new JLabel();
		fragmentTypeDropdown = new JComboBox<String>(new String[0]);
		createFragmentButton = new JButton();
		
		operandList = new JList<String>(listModel);
		operandList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		
		operandList.setSelectedIndex(0);
		//operandList.addOperandListSelectionListener(this);
		operandList.setVisibleRowCount(5);
        JScrollPane operandListScrollPane = new JScrollPane(operandList);
		
		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("SMSS Application");
		
		emptyLabel.setText("");

		// for method
		smssLabel.setText("SMSS Class Name: ");
		smssCreateButton.setText("Create SMSS");
		smssCreateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createSMSSButtonActionPerformed(evt);
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
		classLabel.setText("Class:");
		classCreateButton.setText("Create Class");
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
				createReceiverButtonActionPerformed(evt);
			}
		});

		// for message
		messageLabel.setText("Messages:");
		createMessageButton.setText("Create Message");
		createMessageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createMessageButtonActionPerformed(evt);
			}
		});		

		// for operand
		operandLabel.setText("Operand:");
		createOperandButton.setText("Create Operand");
		createOperandButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createOperandButtonActionPerformed(evt);
			}
		});		

		// for fragment
		fragmentLabel.setText("Fragments:");
		createFragmentButton.setText("Create Fragment");
		createFragmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createFragmentButtonActionPerformed(evt);
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
										.addComponent(messageListScrollPane, 200, 200, 400)
										.addComponent(operandListScrollPane, 200, 200, 400))
								.addGroup(layout.createParallelGroup()
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(classTypesDropdown1, 200, 200, 400)
										.addComponent(createReceiverButton, 200, 200, 400)
										.addComponent(receiverDropdown2, 200, 200, 400)
										.addComponent(createOperandButton, 200, 200, 400)
										.addComponent(createFragmentButton, 200, 200, 400))
								.addGroup(layout.createParallelGroup()
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(receiverDropdown1, 200, 200, 400)
										.addComponent(createMessageButton, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400)
										.addComponent(emptyLabel, 200, 200, 400))
										
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
								.addComponent(messageListScrollPane)
								.addComponent(createOperandButton)
								.addComponent(emptyLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(fragmentLabel)
								.addComponent(fragmentTypeDropdown)
								.addComponent(operandListScrollPane)
								.addComponent(createFragmentButton)
								.addComponent(emptyLabel))
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
			classTextField.setText("");
		try{
			if(SmssController.hasSenderObject()) {
				senderDropdown.addItem(SmssController.getSenderName());
				senderDropdown.setSelectedIndex(0);
			}
			if(SmssController.hasClassTypes()) {
				classes = new HashMap<Integer, ClassType>();
				classTypesDropdown1.removeAllItems();
				classTypesDropdown2.removeAllItems();
				Integer index = 0;
				for (ClassType classType : SmssController.getClassTypes()) {
					classes.put(index, classType);
					classTypesDropdown1.addItem(classType.getName());
					classTypesDropdown2.addItem(classType.getName());
					index++;
				};
				selectedClassType1 = -1;
				selectedClassType2 = -1;
				classTypesDropdown1.setSelectedIndex(selectedClassType1);
				classTypesDropdown2.setSelectedIndex(selectedClassType2);

			}
			
			if(SmssController.hasReceivers()) {
				receivers = new HashMap<Integer, ReceiverObject>();
				receiverDropdown1.removeAllItems();
				receiverDropdown2.removeAllItems();
				Integer index = 0;
				for (ReceiverObject receiver : SmssController.getReceiversNonHash()) {
					receivers.put(index, receiver);
					receiverDropdown1.addItem(String.valueOf(receiver.getId()));
					receiverDropdown2.addItem(String.valueOf(receiver.getId()));
					index++;
				};
				selectedReceiver1 = -1;
				selectedReceiver2 = -1;
				receiverDropdown1.setSelectedIndex(selectedClassType1);
				receiverDropdown2.setSelectedIndex(selectedClassType2);
			}
			
			if(SmssController.hasMessages()) {
				for (Message message : SmssController.getMessages()) {
					listModel.addElement(message.getName());
				};
		        messageList1.setSelectedIndex(-1);
			}
			
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}

		
		// editor table
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(overviewColumnNames);
		overviewTable.setModel(overviewDtm);
		
		if(SmssController.getClassTypes().size() > 0) {
			Object[] obj = {SmssController.getSmssClass(0)};
			overviewDtm.addRow(obj);
		}
		if(SmssController.hasMethod()) {
			Object[] obj = {"   " + SmssController.getMethodName()};
			overviewDtm.addRow(obj);
		}
			
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
	}
		
	// button listeners
	private void createSMSSButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			SmssController.createSmss(smssTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
				
		// update visuals
		refreshData();
	}
	
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
	
	private void createClassButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			if(classTextField.getText().equals("")) {
				throw(new InvalidInputException("Class Name cannot be empty"));
			}else {
				SmssController.createClassType(classTextField.getText());
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	private void createReceiverButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			if(classTextField.getText().equals("")) {
				throw(new InvalidInputException("Receiver name cannot be empty"));
			}else {
				ClassType classType = SmssController.getClassTypeByName(classes.get(selectedClassType2).getName());
				//SmssController.createReceiver(receiverTextField.getText(), classType.getName());
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	private void createMessageButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			if(messageTextfield.getText().equals("")) {
				throw(new InvalidInputException("Message name cannot be empty"));
			}else {
				ReceiverObject receiverObj = SmssController.getReceiverById(receivers.get(selectedReceiver1).getId());
				SmssController.createMessage(messageTextfield.getText(), receiverObj.getId());
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	private void createOperandButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			if(operandCondition.getText().equals("")) {
				throw(new InvalidInputException("Message name cannot be empty"));
			}else {
				ReceiverObject receiverObj = SmssController.getReceiverById(receivers.get(selectedReceiver1).getId());
				SmssController.createMessage(messageTextfield.getText(), receiverObj.getId());
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
	
	private void createFragmentButtonActionPerformed(ActionEvent evt) {
		// clear error message	
		error = null;
		
		// call the controller
		try {
			if(messageTextfield.getText().equals("")) {
				throw(new InvalidInputException("Message name cannot be empty"));
			}else {
				ReceiverObject receiverObj = SmssController.getReceiverById(receivers.get(selectedReceiver1).getId());
				SmssController.createMessage(messageTextfield.getText(), receiverObj.getId());
			}
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
		


}
