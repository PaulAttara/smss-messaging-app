package ca.mcgill.ecse.smss.controller;

import java.util.List;

import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.model.ClassType;
import ca.mcgill.ecse.smss.model.Method;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.model.SenderObject;

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
	
	
	public static void createMethod(String name) throws InvalidInputException {
		SMSS smss = SmssApplication.getSmss();
		try {
			// FOR NOW GOING TO CREATE A CLASS SO THAT I CAN CREATE METHOD
			smss.addClassType(new ClassType("main", smss));
			smss.addMethod(new Method(name, smss.getClassType(0), smss));
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
			// for now, just to be able to create a sender, i will do hack
			smss.getClassType(0).addObject(new SenderObject(smss.getClassType(0), "the cheese", smss));
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static String getMethodName() {
		return SmssApplication.getSmss().getMethod(0).getName();
	}
	public static String getSenderName() {
		return SmssApplication.getSmss().getSenderObject().getName();
	}
	public static List<ClassType> getClassTypes() {
		return SmssApplication.getSmss().getClassTypes();
	}
}
