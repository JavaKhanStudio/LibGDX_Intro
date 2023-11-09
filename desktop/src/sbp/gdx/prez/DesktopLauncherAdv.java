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
		
		
		String[] options = {DEFAUT, LE_MOVEMENT, LE_MOVEMENT_RAPIDE, LE_UPDATE};
        
       
        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Choisir l'example souhait�",
                "Option Dialog",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
       
        startThis(selectedOption) ; 
		
	}
	
	public static void startThis(String selectedOption) {
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		
		
		if(selectedOption != null){
			config.setForegroundFPS(60);
			config.useVsync(true);
			config.setTitle(selectedOption);
			
            switch (selectedOption) {
                case DEFAUT:
            		new Lwjgl3Application(new Exemple_A_Default(), config);
                    break;
                case LE_MOVEMENT:
                	config.useVsync(false);
            		new Lwjgl3Application(new Exemple_B_Movement(), config);
                    break;
                case LE_MOVEMENT_RAPIDE:
                	config.setForegroundFPS(120);
                	config.useVsync(false);
            		new Lwjgl3Application(new Exemple_B_Movement(), config);
                    break;
                case LE_UPDATE:
            		new Lwjgl3Application(new Exemple_C_Update(), config);
                    break;
                case TEXTURE_FILTER:
            		new Lwjgl3Application(new Exemple_E_TextureFilters(), config);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No option selected!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucune option selectionné.");
        }
	}
}
