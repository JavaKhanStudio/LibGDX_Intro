package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Exemple_B_Movement extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	float distanceSansDelta = 0 ; 
	float distanceAvecDelta = 0 ;
	float distanceAvecDeltaPropre = 0 ;
	
	final int sizeImg = 150 ;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		
		float delta = Gdx.graphics.getDeltaTime() ;
		float cleanDelta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f) * 60;
	    
		distanceSansDelta += 1 ; 
		distanceAvecDelta += 1 * delta * 60;
		batch.begin();
	
		batch.draw(img, distanceSansDelta, 0, sizeImg, sizeImg);
		batch.draw(img, distanceAvecDelta, sizeImg, sizeImg, sizeImg);
		
		if (cleanDelta > 0) // Vieille facon de faire
    	{	
			distanceAvecDeltaPropre += cleanDelta ; 
			batch.draw(img, distanceAvecDeltaPropre, sizeImg * 2, sizeImg, sizeImg);	
    	} 
		else {
			System.out.println("cleanDelta was negative");
		}
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
