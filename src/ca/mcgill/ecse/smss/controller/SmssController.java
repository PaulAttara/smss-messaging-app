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
			getClassTypeByName(className).addObject(new ReceiverObject(getClassTypeByName(className), receiverName));
			System.out.println(getClassTypeByName(className));
			System.out.println(getClassTypeByName(className).getObjects());

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
			if(fragmentType == "Alternative") {
				AlternativeFragment frag = new AlternativeFragment(smss, specificElement, specificOperands.get(0), specificOperands.get(1));
				if(specificOperands.size() > 2) {
					for(int i = 2; i < specificOperands.size(); i++) {
						frag.addSpecificOperand(specificOperands.get(i));
					}
				}
				specificElement.setFragment(frag);
			}
			// parallel fragment
			else if(fragmentType == "Parallel"){
				ParallelFragment frag = new ParallelFragment(smss, specificElement, specificOperands.get(0), specificOperands.get(1));
				if(specificOperands.size() > 2) {
					for(int i = 2; i < specificOperands.size(); i++) {
						frag.addSpecificOperand(specificOperands.get(i));
					}
				}
				specificElement.setFragment(frag);
			}
			smss.getMethod().addSpecificElement(specificElement);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	// 1- 1 message
	// 2- 1 fragment
	// has specific fragments
	
	
	public static void createSpecificElement(Message message) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			SpecificElement specificElement = new SpecificElement(smss.getMethod());
			specificElement.setMessage(message);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void createSpecificElement(Fragment fragment) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			SpecificElement specificElement = new SpecificElement(smss.getMethod());
			specificElement.setFragment(fragment);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
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
	
	public static List<ReceiverObject> getReceivers() { 
		
		List<ClassType> classes = SmssApplication.getSmss().getClassTypes();
		List<ReceiverObject> receivers = new ArrayList<>();
		for(ClassType c : classes) {
			for(Object r : c.getObjects()) {
				if(r instanceof ReceiverObject) {
					receivers.add((ReceiverObject) r);
				}
			}
		}
		return receivers;
	}
	
	
	public static ReceiverObject getReceiverByName(String receiverName) throws InvalidInputException {
		
		List<ClassType> classes = SmssApplication.getSmss().getClassTypes();
		List<ReceiverObject> receivers = new ArrayList<>();
		for(ClassType c : classes) {
			for(Object r : c.getObjects()) {
				if(r instanceof ReceiverObject && ((ReceiverObject) r).getName() == receiverName) {
					return (ReceiverObject) r;
				}
			}
		}
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
	
	public static SpecificOperand getSpecificOperandById(int specificOperandId) {
		
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
			List<ClassType> classes = SmssApplication.getSmss().getClassTypes();
			for(ClassType c : classes) {
				for(Object r : c.getObjects()) {
					if(r instanceof ReceiverObject) {
						return true;					
					}
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
	
	public static boolean hasSpecificElements() throws InvalidInputException {
		if(SmssApplication.getSmss().getMethod() != null) {
			return SmssApplication.getSmss().getMethod().getSpecificElements().size() > 0;
		}else {
			return false;
		}
			
	}
	
	public static boolean hasFragments() throws InvalidInputException {
		
		if( SmssApplication.getSmss().getFragments().size() > 0) {
			return true;
		}else {
			return false;
		}
}
}
