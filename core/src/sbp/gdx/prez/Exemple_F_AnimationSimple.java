package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_F_AnimationSimple extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0;

	@Override
	public void create() {
	    batch = new SpriteBatch();

	    atlas = new TextureAtlas(Gdx.files.internal("atlas/girl_running_clean.atlas"));
	    Array<TextureAtlas.AtlasRegion> regions = atlas.findRegions("spritesheet");
	    animation = new Animation<TextureRegion>(0.1f, regions, Animation.PlayMode.LOOP);
	}
	
	@Override
	public void render() {
	    elapsedTime += Gdx.graphics.getDeltaTime(); 
	
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	    TextureRegion currentFrame = animation.getKeyFrame(elapsedTime);
	
	    batch.begin();
	    batch.draw(currentFrame, 50, 50);
	    batch.end();
	}
	
	@Override
	public void dispose() {
	    batch.dispose();
	    atlas.dispose();
	}
}