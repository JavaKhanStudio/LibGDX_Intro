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

public class Exemple_G_AnimationComplexe extends ApplicationAdapter {
    Animation<TextureRegion> runningAnimation;
    TextureAtlas atlas;
    float stateTime;
    SpriteBatch batch;

    @Override
    public void create() {
        atlas = new TextureAtlas(Gdx.files.internal("atlas/girl_running_dirty.atlas"));
        Array<TextureAtlas.AtlasRegion> runningFrames = new Array<TextureAtlas.AtlasRegion>();
        runningFrames.addAll(atlas.findRegion("spritesheet-0"), atlas.findRegion("spritesheet-1"), 
                             atlas.findRegion("spritesheet-2"), atlas.findRegion("spritesheet-3"));
        runningAnimation = new Animation<TextureRegion>(0.1f, runningFrames, Animation.PlayMode.LOOP);
        stateTime = 0f;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime(); 
        TextureRegion currentFrame = runningAnimation.getKeyFrame(stateTime, true);

        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        Gdx.graphics.getGL20().glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        batch.begin();
        batch.draw(currentFrame, 50, 50);
        batch.end();
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}