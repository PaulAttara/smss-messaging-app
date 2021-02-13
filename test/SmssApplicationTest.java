import ca.mcgill.ecse.smss.application.SmssApplication;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.controller.SmssController;
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

public class SmssApplicationTest {
	
	public static void main(String [] args) {
		SMSS smss = SmssApplication.getSmss();
		try {
			SmssController.createSmss("smss");
			SmssController.createMethod("MAIN");
			SmssController.createSender("the only sender");
			SmssController.createClassType("cool class");
			List<ClassType> types = smss.getClassTypes();
			
			System.out.println("LIST OF CLASS TYPES");
			for(ClassType type : types) {
				System.out.println("Name: " + type.getName());
			}
			SmssController.createReceiver("cool class");
			
			System.out.println("\nLIST OF RECEIVERS IDS");
			for(ReceiverObject receiver : SmssController.getReceiversNonHash()) {
				System.out.println("ID: " + receiver.getId());
			}
			
			SmssController.createMessage("first message", 1);
			
			System.out.println("\nLIST OF MESSAGES");
			for(Message message : SmssController.getMessages()) {
				System.out.println("Name: " + message.getName());
			}
			
			SmssController.createOperand("useless condition");
			SmssController.createOperand("more useful condition");
			
			System.out.println("\nLIST OF OPERANDS");
			for(Operand operand : smss.getOperands()) {
				System.out.println("ID: " + operand.getId() + ", Name: " + operand.getCondition());
			}
			
			SmssController.createFragment("alt", 1, 2, 1);
			
			System.out.println("\nLIST OF FRAGMENTS");
			for(Fragment fragment : SmssController.getFragments()) {
				System.out.println("ID: " + fragment.getId());
			}
			
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());

		}
		
	}
	

}
