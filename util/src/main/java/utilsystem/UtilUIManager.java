package utilsystem;

import java.awt.Component;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class UtilUIManager {

	private static final String APPLE = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	private static final String WINDOW = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	public static void setAppleUI(Component component){
		try {
			UIManager.setLookAndFeel(APPLE);
			SwingUtilities.updateComponentTreeUI(component);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void setWindowUI(Component component){
		try {
			UIManager.setLookAndFeel(WINDOW);
			SwingUtilities.updateComponentTreeUI(component);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
