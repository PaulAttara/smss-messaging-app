package ca.mcgill.ecse.smss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.model.AlternativeFragment;
import ca.mcgill.ecse.smss.model.ClassType;
import ca.mcgill.ecse.smss.model.Fragment;
import ca.mcgill.ecse.smss.model.Message;
import ca.mcgill.ecse.smss.model.Method;
import ca.mcgill.ecse.smss.model.Operand;
import ca.mcgill.ecse.smss.model.ParallelFragment;
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
	public static void createSmss(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		smss.addClassType(new ClassType(name, smss));
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
	
	public static void createReceiver(String className) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			
			smss.getClassType(0).addObject(new ReceiverObject(getClassTypeByName(className)));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	

//			Message message = new Message(messageName, getSenderObject(), getReceiverById(receiverId));
//			smss.getMethod().addSpecificElement(new SpecificElement(message, smss.getMethod()));

	
	public static void createMessage(String messageName, int receiverId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Message message = new Message(messageName, getSenderObject(), getReceiverById(receiverId));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	// Unable to create Fragment. MUST HAVE AT LEAST 2 SPECIFIC MESSAGES
	public static void createFragment(String fragmentType, int operandId1, int operandId2, int receiverId) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			SpecificMessage specificMessage1 = new SpecificMessage(getOperand(operandId1));
			SpecificMessage specificMessage2 = new SpecificMessage(getOperand(operandId2));
			SpecificElement specificElement = new SpecificElement(smss.getMethod());
			
			// alternative fragment
			if(fragmentType == "alt") {
				AlternativeFragment frag = new AlternativeFragment(smss, specificElement,specificMessage1, specificMessage2);
				
			}
			// parallel fragment
			else {
				ParallelFragment frag = new ParallelFragment(smss, specificElement, specificMessage1, specificMessage2);
			}
			
			// QUESTION: DOES THE COMPOSITION FROM SMSS TO FRAGMENT MESS UP WITH THIS? so far, no!
			smss.getMethod().addSpecificElement(specificElement);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createOperand(String condition) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Operand operand = new Operand(condition, smss);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	//messasges list then create operand
	
	
	
	
	
	
	
	

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

	public static String getSmssClass(int index) {
		return SmssApplication.getSmss().getClassType(index).getName();
	}
	
	public static boolean hasMethod() {
		return SmssApplication.getSmss().hasMethod();
	}
	
	public static String getMethodName() {
		return SmssApplication.getSmss().getMethod().getName();
	}
	public static SenderObject getSenderObject() throws InvalidInputException {
		// get all objects
		if(SmssController.getClassTypes().size() > 0) {
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
		else {
			return null;
		}
	}
	
	public static boolean hasSenderObject() throws InvalidInputException {
		// get all objects
		if(SmssController.getClassTypes().size() > 0) {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		// find sender object
		for(Object o : objects) {
			if(o instanceof SenderObject) {
				return true;
			}
		}
		
		return false;	
		}
		else {
			return false;
		}
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
	public static ClassType getClassTypeByName(String className) {
		for(ClassType type : SmssApplication.getSmss().getClassTypes()) {
			if(type.getName() == className) {
				return type;
			}
		}
		return null;
	}

	public static boolean hasClassTypes() {
		return SmssApplication.getSmss().getClassTypes().size() > 0;
	}
	
	
	public static List<ClassType> getClassTypes() {
		return SmssApplication.getSmss().getClassTypes();
	}
	
	// this method is used for testing, not needed for ui, use the other getReceivers for ui
	public static List<ReceiverObject> getReceiversNonHash() { 
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		List<ReceiverObject> receivers = new ArrayList<>();
		
		for(Object r : objects) {
			if(r instanceof ReceiverObject) {
				ReceiverObject receiver = (ReceiverObject) r;
				receivers.add((ReceiverObject) r);
			}
		}
		return receivers;
	}
	
	public static boolean hasReceivers() {
		if(SmssController.getClassTypes().size() > 0) {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
				
		for(Object r : objects) {
			if(r instanceof ReceiverObject) {
				return true;
			}
		}
		return false;
		}
		else {
			return false;
		}
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
	public static ReceiverObject getReceiverById(int receiverId) {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
				
		for(Object r : objects) {
			if(r instanceof ReceiverObject && ((ReceiverObject) r).getId() == receiverId) {
				return (ReceiverObject) r;
			}
		}
		// return error message saying to add a sender!!
		return null;
	}
	
	public static List<Message> getMessages() throws InvalidInputException {
		try {
			return getSenderObject().getMessages();
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static boolean hasMessages() throws InvalidInputException {
		try {
			return getSenderObject().getMessages().size() > 0;
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	// MAYBE COMPOSITION FROM SMSS TO OPERAND!! TO AVOID ALL THIS CODE
	public static List<Operand> getOperands() {
		List<Operand> operands = new ArrayList<Operand>();
		List<Fragment> fragments = SmssApplication.getSmss().getFragments();
		for(Fragment fragment : fragments) {
			List<SpecificMessage> specificMessages = fragment.getSpecificMessages();
			for (SpecificMessage specificMessage : specificMessages) {
				operands.add(specificMessage.getOperand());
			}
		}
		return operands;
	}
	
	public static Operand getOperand(int operandId) {
		List<Operand> operands = SmssApplication.getSmss().getOperands();
		for(Operand operand : operands) {
			if(operand.getId() == operandId) {
				return operand;
			}
		}
		return null;
	}
	
	public static List<Fragment> getFragments() {
		return SmssApplication.getSmss().getFragments();	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
