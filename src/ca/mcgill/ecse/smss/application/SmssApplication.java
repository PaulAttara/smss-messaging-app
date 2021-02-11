package ca.mcgill.ecse.smss.application;
import ca.mcgill.ecse.smss.model.SMSS;
import ca.mcgill.ecse.smss.view.SmssPage;;

public class SmssApplication {
	
	private static SMSS smss;
	
	public static void main(String[] args) {
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmssPage().setVisible(true);
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
