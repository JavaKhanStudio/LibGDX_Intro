package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_Cc_Update_WithBitMap extends ApplicationAdapter {
	
	SpriteBatch batch;
	BitmapFont font ;
	
	float timeSinceStart ;
	float frameRate ;
	
	boolean isPaused = false ; 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont() ; 
	}

	@Override
	public void render () {
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
	        isPaused = !isPaused; 
	    }
		
		ScreenUtils.clear(0, 0, 0, 1);
	    
	    float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
	    
	    if (!isPaused) {
	        update(delta); 
	    }
	    
	    doRender();
	}

	public void update(float delta) {
		timeSinceStart += Gdx.graphics.getDeltaTime();
	    frameRate = 1 / Gdx.graphics.getDeltaTime();
	}
	
	public void doRender() {
		batch.begin();
		font.draw(batch, "Mon Texte", 100, 100);
		font.draw(batch, "Current frame rate: " + (int)frameRate, Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2 + 10);
		font.draw(batch, "Seconds since start: " + timeSinceStart, Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2 - 10);
		batch.end();		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
