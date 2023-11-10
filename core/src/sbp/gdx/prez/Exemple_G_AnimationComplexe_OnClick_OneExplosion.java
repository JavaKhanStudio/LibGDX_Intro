package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;
import sbp.gdx.prez.gvars.GVars_AnimationExplosition;

public class Exemple_G_AnimationComplexe_OnClick_OneExplosion extends ApplicationAdapter {
    Animation<TextureRegion> runningAnimation;
    TextureAtlas atlas;
    float stateTime;
    float stateTimeExplosion;
    SpriteBatch batch;
    
    TextureRegion currentFrameExplosion ;
    Animation<TextureRegion> explosion1 ;
    Vector2 clickPosition ; 
    
    // Cet example est volontairement sale, pour vous montrer comment fonctionne les choses (même si elles sont mal faites)
    
    @Override
    public void create() {
        atlas = new TextureAtlas(Gdx.files.internal("atlas/girl_running_dirty.atlas"));
        Array<TextureAtlas.AtlasRegion> runningFrames = new Array<TextureAtlas.AtlasRegion>();
        runningFrames.addAll(atlas.findRegion("spritesheet-0"), atlas.findRegion("spritesheet-1"), 
                             atlas.findRegion("spritesheet-2"), atlas.findRegion("spritesheet-3"));
        runningAnimation = new Animation<TextureRegion>(0.1f, runningFrames, Animation.PlayMode.LOOP);
        stateTime = 0f;
        stateTimeExplosion = 0f ; 
        
        
        batch = new SpriteBatch();
        
        
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                clickPosition = new Vector2(screenX, screenY);
                explosion1 = GVars_AnimationExplosition.getExplosion1() ;
                stateTimeExplosion = 0 ; 
                return true;
            }
        });
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
        if(explosion1 != null && !explosion1.isAnimationFinished(stateTimeExplosion))
        {
        	currentFrameExplosion = explosion1.getKeyFrame(stateTimeExplosion, true) ; 
        	
        	batch.draw(currentFrameExplosion, Gdx.graphics.getWidth() - clickPosition.x, Gdx.graphics.getHeight() - clickPosition.y);
        	stateTimeExplosion += Gdx.graphics.getDeltaTime() ; 
        }
        	
        batch.end();
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}