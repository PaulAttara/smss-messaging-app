package ca.mcgill.ecse.smss.application;
import ca.mcgill.ecse.smss.view.SmssPage;
import ca.mcgill.ecse.smss.model.SMSS;

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
			// load model
			smss = new SSMS();
		}
 		return ssms;
	}
}
