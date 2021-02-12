package ca.mcgill.ecse.smss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.model.ClassType;
import ca.mcgill.ecse.smss.model.Condition;
import ca.mcgill.ecse.smss.model.Fragment;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.Method;
import ca.mcgill.ecse.smss.model.Operand;
import ca.mcgill.ecse.smss.model.ReceiverObject;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.model.SenderObject;
import ca.mcgill.ecse.smss.model.SpecificElement;
import ca.mcgill.ecse.smss.model.SpecificMessage;

public class SmssController {
	
	public SmssController() {
		
	}
	// you need to create a class before creating a method!!! so to address this, i changed 
	// multiplicity of class to method so that cant have a method without it being linked to a class
	// so need to look into this tomorrow
	// whats the difference between the MAIN Myclass and the MyClass1,MyClass2...
	
	// also, i decided not to create the smss object from the ui, its already instantiated 
	// when starting the application automatically
	
	// also i added a composition from class to method
	
	// confused on how to create sender right from the beginning without having a class!! 
	// so i tried adding a composition from smss to sender, since it will have 1. 
	// but this did not work. when trying to use smss.setSender(), theres a weird thing that says:
	// "Unable to setSenderObject, as existing senderObject would become an orphan"
	
	// CREATE--------------------------------------------------------------------------------------------------------------------------------
	public static void createSmss() throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		smss.addClassType(new ClassType("smss", smss));
	}
	
	public static void createMethod(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			// smss class will always be the first one since thats the first one automatically created
			smss.setMethod(new Method(name, smss.getClassType(0), smss));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createClassType(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.addClassType(new ClassType(name, smss));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createSender(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			smss.getClassType(0).addObject(new SenderObject(smss.getClassType(0), name));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createReceiver(Integer classId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			
			smss.getClassType(0).addObject(new ReceiverObject(smss.getClassType(classId)));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	

//			Message message = new Message(messageName, getSenderObject(), getReceiverById(receiverId));
//			smss.getMethod().addSpecificElement(new SpecificElement(message, smss.getMethod()));

	
	public static void createMessage(String messageName, Integer receiverId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Message message = new Message(messageName, getSenderObject(), getReceiverById(receiverId));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createSpecificMessage(String messageName, Integer receiverId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			SpecificMessage specific = new SpecificMessage();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createFragment(String messageName, Integer receiverId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Fragment fragment = new Fragment();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createOperand(String condition) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Operand operand = new Operand(condition);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	// create message, operand
	// create specific message with both of those
	// then when specifying fragment add specific message which has operand and fragment linked to it
	
	// whrn add to editoor: select message OR fragment
	// then add specific message: which has operand and message
	/// adding operant and fragment to a message
	// then adding specific element to the method
	
	// add message the editor
	// create fragment
	// add message to editor: creates specific element attached to a message
	// GETTERS--------------------------------------------------------------------------------------------------------------------------------

	public static String getMethodName() {
		return SmssApplication.getSmss().getMethod().getName();
	}
	public static SenderObject getSenderObject() throws InvalidInputException {
		// get all objects
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		SenderObject sender = null;
		// find sender object
		for(Object o : objects) {
			if(o instanceof SenderObject) {
				sender = (SenderObject) o;
			}
		}
		if(sender != null) {
			return sender;
		}
		return null;
		
	}
	
	public static String getSenderName() throws InvalidInputException {
		// get all objects
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		SenderObject sender = null;
		// find sender object
		for(Object o : objects) {
			if(o instanceof SenderObject) {
				sender = (SenderObject) o;
			}
		}
		if(sender != null) {
			return sender.getName();
		}
		// return error message saying to add a sender!!
		return null;
		
	}
	public static List<ClassType> getClassTypes() {
		return SmssApplication.getSmss().getClassTypes();
	}
	public static HashMap<Integer, ReceiverObject> getReceivers() {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		HashMap<Integer, ReceiverObject> receivers = new HashMap<>();
		
		for(Object r : objects) {
			if(r instanceof ReceiverObject) {
				ReceiverObject receiver = (ReceiverObject) r;
				receivers.put(receiver.getId(), receiver);
			}
		}
		return receivers;	
	}
	public static ReceiverObject getReceiverById(Integer receiverId) {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
				
		for(Object r : objects) {
			if(r instanceof ReceiverObject && ((ReceiverObject) r).getId() == receiverId) {
				return (ReceiverObject) r;
			}
		}
		// return error message saying to add a sender!!
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
