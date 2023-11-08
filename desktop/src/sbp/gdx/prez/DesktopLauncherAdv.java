package sbp.gdx.prez;

import static sbp.gdx.prez.gvars.FVars_StartOptions.*;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sbp.gdx.prez.Exemple_A_Default;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncherAdv 
{
	
	
	
	public static void main (String[] arg) 
	{
		
		
		String[] options = {DEFAUT, LE_MOVEMENT, "option3"};
        
       
        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Choisir l'example souhaité",
                "Option Dialog",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
       
        startThis(selectedOption) ; 
		
	}
	
	public static void startThis(String selectedOption) {
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		
		 if(selectedOption != null){
	            switch (selectedOption) {
	                case DEFAUT:
	                	config.setTitle(DEFAUT);
	            		new Lwjgl3Application(new Exemple_A_Default(), config);
	                    break;
	                case LE_MOVEMENT:
	                	config.setTitle(LE_MOVEMENT);
	                	config.useVsync(false);
	            		new Lwjgl3Application(new Exemple_B_Movement(), config);
	                    break;
	                case "option3":
	                	config.setTitle("HandOnBDX");
	            		new Lwjgl3Application(new Exemple_A_Default(), config);
	                    break;
	                default:
	                    JOptionPane.showMessageDialog(null, "No option selected!");
	            }
	        } else {
	            // User closed the dialog or clicked Cancel
	            JOptionPane.showMessageDialog(null, "No option selected or dialog was cancelled.");
	        }
	}
}
