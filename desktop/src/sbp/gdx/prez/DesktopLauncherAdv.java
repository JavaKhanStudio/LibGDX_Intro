package sbp.gdx.prez;

import static sbp.gdx.prez.gvars.FVars_StartOptions.*;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sbp.gdx.prez.Exemple_A_Default;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncherAdv 
{
	
	
	
	public static void main (String[] arg) 
	{
		
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
		
		
		if(selectedOption != null){
			config.setForegroundFPS(60);
			config.useVsync(true);
			config.setTitle(selectedOption);
			
			config.setWindowedMode(1000, 800);
			
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
                case LE_SHAPE:
            		new Lwjgl3Application(new Exemple_Bb_Shapes(), config);
                    break;  
                case LE_UPDATE:
            		new Lwjgl3Application(new Exemple_C_Update(), config);
                    break;  
                case LE_UPDATE_AVEC_TEXTE:
            		new Lwjgl3Application(new Exemple_Cc_Update_WithBitMap(), config);
                    break;
                case LA_CAMERA:
            		new Lwjgl3Application(new Exemple_D_CameraOrtho(), config);
                    break;
                case LA_CAMERA_MOUSE_CLICK:
            		new Lwjgl3Application(new Exemple_Da_CameraOrtho_MouseClick(), config);
                    break;
                case LES_TYPES_DE_CAMERA:
            		new Lwjgl3Application(new Exemple_Dd_CamerasTypes(), config);
                    break;
                case TEXTURE_FILTER:
            		new Lwjgl3Application(new Exemple_E_TextureFilters(), config);
                    break;
                case ANIMATION_SIMPLE:
            		new Lwjgl3Application(new Exemple_F_AnimationSimple(), config);
                    break;
                case ANIMATION_SIMPLE_ATLAS_SALE:
            		new Lwjgl3Application(new Exemple_Ff_AnimationSimple_AtlasSale(), config);
                    break;
                case EXPLOSION_ON_CLICK_ONE_EXPLOSION : 
                	new Lwjgl3Application(new Exemple_G_AnimationComplexe_OnClick_OneExplosion(), config);
                    break;
                case GRAVITY : 
                	new Lwjgl3Application(new Exemple_H_Gravity(), config);
                    break;
                case GRAVITY_AVEC_FIXTURE : 
                	new Lwjgl3Application(new Exemple_Hh_Gravity_Avec_Fixture(), config);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No option selected!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucune option selectionné.");
        }
	}
}
