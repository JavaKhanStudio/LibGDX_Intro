package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_D_CameraOrtho extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    OrthographicCamera camera;
    OrthographicCamera minimapCamera;

    float distanceRapide = 0;
    float distanceLent = 0;
    final int sizeImg = 200;
    boolean isPaused = true;
    
    private final int minimapSize = 150; // Example size of the minimap

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture(FVars_Texture.BDXIO_LOGO);

        // Main camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.update();
        

        // Minimap camera 
        minimapCamera = new OrthographicCamera(minimapSize, minimapSize); 
        minimapCamera.setToOrtho(false);
        minimapCamera.position.set(minimapCamera.viewportWidth / 2, Gdx.graphics.getHeight() - minimapCamera.viewportHeight / 2, 0);
        minimapCamera.update();
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
        
        renderMinimap();
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            isPaused = !isPaused;
        }
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

    private void renderMinimap() {
        Gdx.gl.glViewport(Gdx.graphics.getWidth() - minimapSize, Gdx.graphics.getHeight() - minimapSize, minimapSize, minimapSize);
        minimapCamera.update();
        batch.setProjectionMatrix(minimapCamera.combined);
        doRender() ;
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
