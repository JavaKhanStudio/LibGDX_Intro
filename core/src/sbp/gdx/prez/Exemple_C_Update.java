package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_C_Update extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	float distanceRapide = 0 ; 
	float distanceLent = 0 ;
	
	final int sizeImg = 200 ;
	
	boolean isPaused = false ; 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(FVars_Texture.BDXIO_LOGO);
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
	    distanceLent += 1 * delta;
	    distanceRapide += 1 * delta * 60;
	}
	
	public void doRender() {
		batch.begin();
		
		batch.draw(img, distanceRapide, 0, sizeImg, sizeImg);
		batch.draw(img, distanceLent, sizeImg, sizeImg, sizeImg);
		
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
