package sbp.gdx.prez;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sbp.gdx.prez.Exemple_A_Default;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncherAdv {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		
		String[] options = {"option1", "option2", "option3"};
        
        // Input dialog with a drop down box
        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Choisir l'example souhaité",
                "Option Dialog",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        // Check if a selection was made
        if(selectedOption != null){
            // Switch case based on the selected value
            switch (selectedOption) {
                case "option1":
                	config.setTitle("HandOnBDX");
            		new Lwjgl3Application(new Exemple_A_Default(), config);
                    break;
                case "option2":
                    // Handle option2
                    JOptionPane.showMessageDialog(null, "You selected option 2!");
                    break;
                case "option3":
                    // Handle option3
                    JOptionPane.showMessageDialog(null, "You selected option 3!");
                    break;
                default:
                    // Handle other cases
                    JOptionPane.showMessageDialog(null, "No option selected!");
            }
        } else {
            // User closed the dialog or clicked Cancel
            JOptionPane.showMessageDialog(null, "No option selected or dialog was cancelled.");
        }
		
		
		
	}
}
