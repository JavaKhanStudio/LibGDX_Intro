package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_Da_CameraOrtho_MouseClick extends ApplicationAdapter {
    SpriteBatch batch;
    OrthographicCamera camera;

    float distanceRapide = 0;
    float distanceLent = 0;
    final int sizeImg = 200;
    boolean isPaused = true;
    
   
    
    @Override
    public void create() {
        batch = new SpriteBatch();

        // Main camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.update();
        
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                clickPosition = new Vector2(screenX, screenY);
                worldClickPosition = new Vector3(screenX, screenY, 0) ; 
                worldClickPosition = camera.unproject(worldClickPosition);

                Vector2 correctedPosition = new Vector2(worldClickPosition.x, worldClickPosition.y) ;
                return true;
            }
        });

    }

    @Override
    public void render() {
        handleInput();
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
        
        if (!isPaused) {
            update(delta);
        }
        
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        doRender();
    }

    private void handleInput() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += 10;    
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y += 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            camera.zoom -= 0.02;
        }
    }

    Vector2 clickPosition ; 
    Vector3 worldClickPosition ; 
    
    public void update(float delta) {
        
       
    }

    public void doRender() {
        batch.begin();
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
