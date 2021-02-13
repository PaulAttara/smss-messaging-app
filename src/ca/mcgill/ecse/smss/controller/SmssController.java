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
import ca.mcgill.ecse.smss.model.SpecificOperand;

public class SmssController {
	
	public SmssController() {
		
	}
			
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
		if(name == "") {
			throw new InvalidInputException("Sender cannnot be null");
		}
		try {
			smss.getClassType(0).addObject(new SenderObject(smss.getClassType(0), name));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createReceiver(String className, String receiverName) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			
			smss.getClassType(0).addObject(new ReceiverObject(getClassTypeByName(className), receiverName));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	public static void createMessage(String messageName, String receiverName) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Message message = new Message(messageName, getSenderObject(), getReceiverByName(receiverName));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	// Unable to create Fragment. MUST HAVE AT LEAST 2 SPECIFIC MESSAGES
	public static void createFragment(String fragmentType, List<SpecificOperand> specificOperands) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			if(specificOperands.size() < 2) {
				throw(new InvalidInputException("Need at least 2 operands"));
			}else {
			SpecificElement specificElement = new SpecificElement(smss.getMethod());
			
			// alternative fragment
			if(fragmentType == "alt") {
				AlternativeFragment frag = new AlternativeFragment(smss, specificElement, specificOperands.get(0), specificOperands.get(1));
				if(specificOperands.size() > 2) {
					for(int i = 2; i < specificOperands.size(); i++) {
						frag.addSpecificOperand(specificOperands.get(i));
					}
				}
				specificElement.setFragment(frag);
			}
			// parallel fragment
			else {
				ParallelFragment frag = new ParallelFragment(smss, specificElement, specificOperands.get(0), specificOperands.get(1));
				if(specificOperands.size() > 2) {
					for(int i = 2; i < specificOperands.size(); i++) {
						frag.addSpecificOperand(specificOperands.get(i));
					}
				}
				specificElement.setFragment(frag);
			}
			SmssApplication.getSmss().getMethod().addSpecificElement(specificElement);
			// QUESTION: DOES THE COMPOSITION FROM SMSS TO FRAGMENT MESS UP WITH THIS? so far, no!
			smss.getMethod().addSpecificElement(specificElement);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
//	public static void createSpecificElement(String fragmentType, List<SpecificOperand> specificOperands) throws InvalidInputException {
//		SMSS smss = SmssApplication.getSmss();
//		try {
//			SpecificElement specificElement = new SpecificElement(smss.getMethod());
//			
//			// alternative fragment
//			if(fragmentType == "alt") {
//				AlternativeFragment frag = new AlternativeFragment(smss, specificElement, specificOperands);
//				
//			}
//			// parallel fragment
//			else {
//				ParallelFragment frag = new ParallelFragment(smss, specificElement, specificOperands);
//			}
//			
//			// QUESTION: DOES THE COMPOSITION FROM SMSS TO FRAGMENT MESS UP WITH THIS? so far, no!
//			smss.getMethod().addSpecificElement(specificElement);
//		}
//		catch (RuntimeException e) {
//			throw new InvalidInputException(e.getMessage());
//		}
//	}
	
	public static void createSpecificOperand(String condition, List<Message> messages) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			Operand operand = new Operand(condition, smss);
			smss.addOperand(operand);
			SpecificOperand specificOperand = new SpecificOperand(operand);
			for(Message m : messages) {
				specificOperand.addMessage(m);
			}
			specificOperand.setOperand(operand);
			
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//messasges list then create operand
	// specific operand.getmesaages
			
	

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
	
	public static List<ClassType> getClassTypes() {
		return SmssApplication.getSmss().getClassTypes();
	}
	
	// this method is used for testing, not needed for ui, use the other getReceivers for ui
	public static List<ReceiverObject> getReceivers() { 
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
	public static HashMap<Integer, ReceiverObject> getReceiversHash() {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
		HashMap<Integer, ReceiverObject> receivers = new HashMap<>();
		
		for(Object r : objects) {
			if(r instanceof ReceiverObject) {
				ReceiverObject receiver = (ReceiverObject) r;
				//receivers.put(receiver.getId(), receiver);
			}
		}
		return receivers;
	}
	public static ReceiverObject getReceiverByName(String receiverName) {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
				
		for(Object r : objects) {
			if(r instanceof ReceiverObject && ((ReceiverObject) r).getName() == receiverName) {
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
	
	public static Message getMessageByName(String messageName) throws InvalidInputException {
		try {
			for(Message message : getSenderObject().getMessages()) {
				if(message.getName() == messageName) {
					return message;
				}
			}
		}
		catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
		}
		return null;
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
	//TODO
	// get specific operand and get fragment
	
	public static SpecificOperand getSpecificOperand(int specificOperandId) {
		
		List<Operand> operands = SmssApplication.getSmss().getOperands();
	
		for(Operand operand : operands) {
			for(SpecificOperand s : operand.getSpecificOperands()) {
				if(specificOperandId == s.getId()) {
					return s;
				}
			}
		}
		return null;
	}
	
	public static List<SpecificOperand> getSpecificOperands() {
		
		List<SpecificOperand> specificOperands = new ArrayList<>();
		List<Operand> operands = SmssApplication.getSmss().getOperands();
	
		for(Operand operand : operands) {
			for(SpecificOperand s : operand.getSpecificOperands()) {
				specificOperands.add(s);
			}
		}
		return specificOperands;
	}
	
	public static List<Fragment> getFragments() {
		return SmssApplication.getSmss().getFragments();	
	}
	
	public static Fragment getFragment(int fragmentId) {
		return SmssApplication.getSmss().getFragments().get(fragmentId);	
	}
	
	// CHECKERS--------------------------------------------------------------------------------------------------------------------------------

	public static boolean hasSenderObject() throws InvalidInputException {
		// get all objects
		if(SmssController.getClassTypes().size() == 0) {
			return false;
		}
		else {
			List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
		
			// find sender object
			for(Object o : objects) {
				if(o instanceof SenderObject) {
					return true;
				}
			}
			return false;	
		}
	}
	
	public static boolean hasClassTypes() {
		return SmssApplication.getSmss().getClassTypes().size() > 0;
	}
	
	public static boolean hasReceivers() {
		if(SmssController.getClassTypes().size() == 0) {
			return false;
		}
		else {
		List<ca.mcgill.ecse.smss.model.Object> objects = SmssApplication.getSmss().getClassType(0).getObjects();
				
		for(Object r : objects) {
			if(r instanceof ReceiverObject) {
				return true;
			}
		}
		return false;
		}
	}
	
	public static boolean hasMessages() throws InvalidInputException {
		try {
			if(getSenderObject() != null && getSenderObject().getMessages().size() > 0) {
				return true;
			}else {
				return false;
			}
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static boolean hasSpecificOperands() throws InvalidInputException {
		List<Operand> operands = SmssApplication.getSmss().getOperands();
		for(Operand operand : operands) {
			if(operand.hasSpecificOperands()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
