package ca.mcgill.ecse.smss.application;
import ca.mcgill.ecse.smss.controller.InvalidInputException;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.view.SmssPage;;

public class SmssApplication {
	
	private static SMSS smss;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new SmssPage().setVisible(true);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });   
	}

	public static SMSS getSmss() {
		if (smss == null) {
			smss = new SMSS();
		}
 		return smss;
	}
	

}
