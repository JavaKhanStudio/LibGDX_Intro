package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGreenWorld extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	private FPSLogger fpsLogger;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		fpsLogger = new FPSLogger();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		
		fpsLogger.log();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
